package mhcs.client;

import mhcs.client.gui.AddModulePopup;
import mhcs.client.gui.Login;
import mhcs.client.gui.TenDayAlert;
import mhcs.client.module.ModuleList;
import mhcs.client.moduleConfigurations.ConfigurationMap;
import mhcs.client.moduleConfigurations.ModuleConfiguration;
import mhcs.client.moduleMap.ModuleMap;
import mhcs.client.weather.Weather;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

/**
 * Creates the GUI for the MHCS.
 */
public class MarsHabitatConfigurationSystem implements EntryPoint {

	// Creates sound controller and sounds
	public static final SoundController soundController = new SoundController();
	public static final Sound errorSound = soundController.createSound(Sound.MIME_TYPE_AUDIO_MPEG_MP3,
			"sounds/metallic_error.mp3");
	public static final Sound successSound = soundController.createSound(Sound.MIME_TYPE_AUDIO_MPEG_MP3,
			"sounds/success.mp3");
	public static final Sound popSound = soundController.createSound(Sound.MIME_TYPE_AUDIO_MPEG_MP3,
			"sounds/popup.mp3");
			
	private static final int MAGIC_NUMBER = 3;
	private static final SimpleEventBus BUS = new SimpleEventBus();
	private static final String MODULE_MAP_STRING = "Module Map";
	private static final String WEATHER_STRING = "Weather";
	private static final String FULL_CONFIG = "Full Configuration";
	private static final String MIN1_CONFIG = "Minimum Configuration 1";
	private static final String MIN2_CONFIG = "Minimum Configuration 2";
	private static boolean MINIMUM_CONFIG_REACHED;
	private static final int FULL_INDEX = 4;
	private static final int MIN1_INDEX = 2;
	private static final int MIN2_INDEX = 3;
	private static final int MAX_TABS = 5;
	
	private String width = "3120px";
	private String height = "1610px";
	
	
	public MarsHabitatConfigurationSystem() {

	}

	@Override
	public void onModuleLoad() {

		// Creates the module list.
		final ModuleList modList = new ModuleList();
		final TabLayoutPanel configTabs = new TabLayoutPanel(2, Unit.EM);

		// Creates the module map.
		final ModuleMap modMap = new ModuleMap(modList);
		
		// Creates the weather feed.
		final Weather weather = new Weather();

		// Creates configuration map.
		final ModuleConfiguration fullConfig = new ModuleConfiguration();
		final ModuleConfiguration min1Config = new ModuleConfiguration();
		final ModuleConfiguration min2Config = new ModuleConfiguration();
		min1Config.setMinimumConfigOne();
		min2Config.setMinimumConfigTwo();
		
		// Creates the root panel and sizes it.
		RootPanel rootPanel = RootPanel.get();
		rootPanel.setSize(this.width, this.height);

		// Default command for menu items.
		Command cmd = new Command() {
			public void execute() {
				Window.alert("You selected a menu item!");
			}
		};

		// Command to show ten day alert.
		Command tenDayAlertCmd = new Command() {
			public void execute() {
				final TenDayAlert tenDayAlert = new TenDayAlert();
				tenDayAlert.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
					public void setPosition(final int offsetWidth, final int offsetHeight) {
						int left = (Window.getClientWidth() - offsetWidth) / MAGIC_NUMBER;
						int top = (Window.getClientHeight() - offsetHeight) / MAGIC_NUMBER;
						tenDayAlert.setPopupPosition(left, top);
					}
				});
				popSound.play();
			}
		};

		// Command to show login.
		Command loginCmd = new Command() {
			public void execute() {
				final Login login = new Login();
				login.show();
			}
		};


		// Command to show the add module popup.
		Command addModulePopupCmd = new Command() {
			public void execute() {
				final AddModulePopup popup = new AddModulePopup(modList, BUS);
				popup.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
					public void setPosition(final int offsetWidth, final int offsetHeight) {
						int left = (Window.getClientWidth() - offsetWidth) / MAGIC_NUMBER;
						int top = (Window.getClientHeight() - offsetHeight) / MAGIC_NUMBER;
						popup.setPopupPosition(left, top);
					}
				});
				popSound.play();
			}
		};
		
		// Command to calculate full configurations and add to new tabs.
		Command configurationCmd = new Command() {
			public void execute() {
				if (fullConfig.calculateConfiguration(modList)) {	
					final Grid map = ConfigurationMap.getConfigurationGrid(fullConfig);						
						
					// Remove the current full configuration if it exists.
					if (configTabs.getWidgetCount() == MAX_TABS) {
						configTabs.remove(FULL_INDEX);
					}
					configTabs.add(map, FULL_CONFIG);
					configTabs.selectTab(FULL_INDEX);
				} else {
					errorSound.play();
					Window.alert("Unable to calculate full configuration!");
				}
			}
		};
		
		// Command for clearing the module list and configurations
		Command clearCmd = new Command() {
			public void execute() {
				modList.clearList();
				
				configTabs.clear();
				configTabs.add(modMap, MODULE_MAP_STRING);
				configTabs.add(weather, WEATHER_STRING);
				configTabs.selectTab(0);
			}
		};
		
		// Command for saving the full configuration.
		Command saveConfigCmd = new Command() {
			public void execute() {
				if (fullConfig.saveConfiguration(FULL_CONFIG)) {
					successSound.play();
					Window.alert("Full Configuration Saved!");
				} else {
					errorSound.play();
					Window.alert("No Full Configuration Exists!");
				}
			}
		};
		
		
		Command loadConfigCmd = new Command() {
			public void execute() {
				if (fullConfig.loadConfiguration(FULL_CONFIG)) {
					successSound.play();
					Window.alert("Full Configuration loaded!");
				} else {
					errorSound.play();
					Window.alert("No full configuration found in storage!");
				}
			}
		};

		// Command for GPS data transfer.
		Command gpsDataCmd = new Command() {
			public void execute() {
				final GPSDataTransfer data = new GPSDataTransfer(modList);
				BUS.fireEvent(new AddEvent());
			}
		};
		
		// Creates the menu for the menu bar.
		MenuBar theMenu = new MenuBar(true);
		theMenu.setAnimationEnabled(true);
		theMenu.addItem("Add Module", addModulePopupCmd);
		theMenu.addItem("Minumum Resource Path", cmd);
		theMenu.addItem("Calculate Full Configuration", configurationCmd);
		theMenu.addItem("Save Full Configuration", saveConfigCmd);
		theMenu.addItem("Load Full Configuration", loadConfigCmd);
		theMenu.addItem("Milometer Device Calibration Alert", tenDayAlertCmd);
		theMenu.addItem("GPS Data Transfer", gpsDataCmd);
		theMenu.addItem("Clear Modules and Configuration", clearCmd);
		theMenu.addSeparator();
		theMenu.addItem("Log off", loginCmd);

		// Creates the menu bar and adds the menu to itself.
		MenuBar menu = new MenuBar();
		menu.setAnimationEnabled(true);
		menu.addItem("Menu", theMenu);
		menu.setWidth(this.width);

		// Creates the tabs for the various configurations and module map.
		configTabs.add(modMap, MODULE_MAP_STRING);
		configTabs.add(weather, WEATHER_STRING);
		configTabs.setHeight(this.height);
		configTabs.setWidth(this.width);

		// Adds everything to the root panel.
		rootPanel.add(menu);
		rootPanel.add(configTabs);

		// Show login after module has loaded.
		final Login initialLogin = new Login();
		initialLogin.show();

		// Set handler for EventBus.
		BUS.addHandler(AddEvent.TYPE, new AddEventHandler() {
			public void onEvent(final AddEvent event) {

				// "Refreshes" the module map by removing and re-adding the tab
				configTabs.remove(0);
				configTabs.insert(modMap, MODULE_MAP_STRING, 0);
				configTabs.selectTab(0);
				popSound.play();
				
				// Checks to see if requirements are met for the minimum configuration.
				// If they are met, the minimum configuration tabs are created and an alert is issued.
				if (!MINIMUM_CONFIG_REACHED) {
					if (modList.getNumOfAirlock() > 0 && modList.getNumOfCanteen() > 0 && modList.getNumOfControl() > 0 &&
						modList.getNumOfDormitory() > 0 && modList.getNumOfPlain() > 2 && modList.getNumOfPower() > 0 &&
						modList.getNumOfSanitation() > 0 && modList.getNumOfWater() > 0) {
						MINIMUM_CONFIG_REACHED = true;
						
						// Adds minimum configuration one to the tab.
						configTabs.add(ConfigurationMap.getConfigurationGrid(min1Config), MIN1_CONFIG);
						configTabs.selectTab(MIN1_INDEX);
						
						// Adds minimum configuration two to the tab.
						configTabs.add(ConfigurationMap.getConfigurationGrid(min1Config), MIN2_CONFIG);
						configTabs.selectTab(MIN2_INDEX);
					}
				}
			}
		});
		
		// Checks for minimum configs on load.
		if (modList.getNumOfAirlock() > 0 && modList.getNumOfCanteen() > 0 && modList.getNumOfControl() > 0 &&
				modList.getNumOfDormitory() > 0 && modList.getNumOfPlain() > 2 && modList.getNumOfPower() > 0 &&
				modList.getNumOfSanitation() > 0 && modList.getNumOfWater() > 0) {
				MINIMUM_CONFIG_REACHED = true;
				
				// Adds minimum configuration one to the tab.
				configTabs.add(ConfigurationMap.getConfigurationGrid(min1Config), MIN1_CONFIG);
				configTabs.selectTab(MIN1_INDEX);
				
				// Adds minimum configuration two to the tab.
				configTabs.add(ConfigurationMap.getConfigurationGrid(min1Config), MIN2_CONFIG);
				configTabs.selectTab(MIN2_INDEX);
		}

		rootPanel.addStyleName("rootPanel");
	}

}
