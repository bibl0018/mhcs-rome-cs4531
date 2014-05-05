package mhcs.client;

import mhcs.client.gui.AddModulePopup;
import mhcs.client.gui.GPSDataPopup;
import mhcs.client.gui.GravityPopup;
import mhcs.client.gui.Login;
import mhcs.client.gui.MovingTaskPopup;
import mhcs.client.gui.TenDayAlert;
import mhcs.client.module.ModuleList;
import mhcs.client.moduleConfigurations.ConfigurationMap;
import mhcs.client.moduleConfigurations.Coordinates;
import mhcs.client.moduleConfigurations.ModuleConfiguration;
import mhcs.client.moduleMap.ModuleMap;
import mhcs.client.weather.Weather;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
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
	
	/**
	 * Change to 864000000 for 10 days.
	 */
	protected static final int ALERT_TIME = 120000;

	private static final int MAGIC_NUMBER = 3;
	private static final SimpleEventBus BUS = new SimpleEventBus();
	private static final String MODULE_MAP_STRING = "Module Map";
	private static final String FULL_CONFIG = "Full Configuration";
	private static final String MIN1_CONFIG = "Minimum Configuration 1";
	private static final String MIN2_CONFIG = "Minimum Configuration 2";
	private static boolean MINIMUM_CONFIG_REACHED;
	private static final int FULL_INDEX = 3;
	private static final int MIN1_INDEX = 1;
	private static final int MAX_TABS = 4;
	private static final GravityPopup gravityPopup = new GravityPopup(BUS);

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
		//final ModuleMap modMap = new ModuleMap(modList);

		// Creates the weather feed.
		final Weather weather = new Weather();

		// Creates configuration map.
		final ModuleConfiguration fullConfig = new ModuleConfiguration();
		final ModuleConfiguration min1Config = new ModuleConfiguration();
		final ModuleConfiguration min2Config = new ModuleConfiguration();
		min1Config.setMinimumConfigOne(modList);
		min2Config.setMinimumConfigTwo(modList);

		// GPS Data object.
		//final GPSDataTransfer dataTransfer = new GPSDataTransfer(modList);

		// Creates the root panel and sizes it.
		RootPanel.get().setSize(this.width, this.height);

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

		//Command to show the GPS Data Transfer popup.
		Command gpsDataPopupCmd = new Command() {
			public void execute(){
				final GPSDataPopup gpsPopup = new GPSDataPopup(modList, BUS);
					gpsPopup.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
					public void setPosition(final int offsetWidth, final int offsetHeight) {
						int left = (Window.getClientWidth() - offsetWidth) / MAGIC_NUMBER;
						int top = (Window.getClientHeight() - offsetHeight) / MAGIC_NUMBER;
						gpsPopup.setPopupPosition(left, top);
					}
				});
				popSound.play();
			}
		};
		
		// Command to calculate full configurations and add to new tabs.
		Command configurationCmd = new Command() {
			public void execute() {
				Coordinates center = fullConfig.findBestCenterOfGravity(modList);
				
				if (fullConfig.calculateConfiguration(modList, center.getX(), center.getY())) {
					MarsHabitatConfigurationSystem.successSound.play();	
					
					// Remove the current full configuration if it exists.
					if (configTabs.getWidgetCount() == MAX_TABS) {
						configTabs.remove(FULL_INDEX);
					}
					
					configTabs.add(ConfigurationMap.getConfigurationGrid(fullConfig), FULL_CONFIG);
					configTabs.selectTab(FULL_INDEX);
					
					Window.alert("Full Configuration Available!");
				} else {
					MarsHabitatConfigurationSystem.errorSound.play();
					Window.alert("Full Configuration not Available!");
				}
			}
		};
		
		// Command for clearing the module list and configurations
		Command clearCmd = new Command() {
			public void execute() {
				modList.clearList();
				fullConfig.clearConfig();
				
				configTabs.clear();
				configTabs.add(new ModuleMap(modList), MODULE_MAP_STRING);
				configTabs.selectTab(0);
				MINIMUM_CONFIG_REACHED = false;
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

		// Command for loading the full configuration.
		Command loadConfigCmd = new Command() {
			public void execute() {
				if (fullConfig.loadConfiguration(FULL_CONFIG)) {
					configTabs.add(ConfigurationMap.getConfigurationGrid(fullConfig), FULL_CONFIG);
					successSound.play();
					Window.alert("Full Configuration loaded!");
				} else {
					errorSound.play();
					Window.alert("No full configuration found in storage!");
				}
			}
		};

		// Command for GPS data transfer.
//		Command gpsDataCmd = new Command() {
//			public void execute() {
//				dataTransfer.getData();
//				BUS.fireEvent(new AddEvent());
//			}
//		};
		
		// Command for changing the center of gravity.
		Command gravityCmd = new Command() {
			public void execute() {
				gravityPopup.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
					public void setPosition(final int offsetWidth, final int offsetHeight) {
						int left = (Window.getClientWidth() - offsetWidth) / MAGIC_NUMBER;
						int top = (Window.getClientHeight() - offsetHeight) / MAGIC_NUMBER;
						gravityPopup.setPopupPosition(left, top);
					}
				});
				popSound.play();
			}
		};
		
		// Command for getting the size of moving task.
		Command movingTaskCmd = new Command() {
			public void execute() {
				// DELETE THIS
				modList.populateList();
				
				
				final MovingTaskPopup popup = new MovingTaskPopup(
						modList.getSizeOfMovingTask(fullConfig.getCenterColumn(), fullConfig.getCenterRow()));
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

		// Creates the menu for the menu bar.
		MenuBar theMenu = new MenuBar(true);
		theMenu.setAnimationEnabled(true);
		theMenu.addItem("Add Module", addModulePopupCmd);
		theMenu.addItem("GPS Data Transfer", gpsDataPopupCmd);
		theMenu.addSeparator();
		theMenu.addItem("Calculate Full Configuration", configurationCmd);
		theMenu.addItem("Change Center of Gravity", gravityCmd);
		theMenu.addItem("Save Full Configuration", saveConfigCmd);
		theMenu.addItem("Load Full Configuration", loadConfigCmd);
		theMenu.addSeparator();
		theMenu.addItem("Size of Moving Task", movingTaskCmd);
		theMenu.addSeparator();
		theMenu.addItem("Clear Modules and Configuration", clearCmd);
		theMenu.addSeparator();
		theMenu.addItem("Log off", loginCmd);

		// Creates the menu bar and adds the menu to itself.
		MenuBar menu = new MenuBar();
		menu.setAnimationEnabled(true);
		menu.addItem("Menu", theMenu);
		menu.setWidth(this.width);

		// Creates the tabs for the various configurations and module map.
		configTabs.add(new ModuleMap(modList), MODULE_MAP_STRING);
		configTabs.setHeight(this.height);
		configTabs.setWidth(this.width);

		// Adds everything to the root panel.
		RootPanel.get().add(menu);
		RootPanel.get().add(weather);
		RootPanel.get().add(configTabs);

		// Show login after module has loaded.
		final Login initialLogin = new Login();
		initialLogin.show();

		// Start timer to show initial 10 day alert.
		Timer t = new Timer() {
			public void run() {
				final TenDayAlert initialTenDayAlert = new TenDayAlert();
				initialTenDayAlert.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
					public void setPosition(final int offsetWidth, final int offsetHeight) {
						int left = (Window.getClientWidth() - offsetWidth) / MAGIC_NUMBER;
						int top = (Window.getClientHeight() - offsetHeight) / MAGIC_NUMBER;
						initialTenDayAlert.setPopupPosition(left, top);
					}
				});
				popSound.play();
			}
		};

		t.schedule(ALERT_TIME);

		// Set handler for EventBus when new modules are added.
		BUS.addHandler(AddEvent.TYPE, new AddEventHandler() {
			public void onEvent(final AddEvent event) {

				// "Refreshes" the module map by removing and re-adding the tab
				configTabs.remove(0);
				configTabs.insert(new ModuleMap(modList).asWidget(), MODULE_MAP_STRING, 0);
				configTabs.selectTab(0);

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
						configTabs.add(ConfigurationMap.getConfigurationGrid(min2Config), MIN2_CONFIG);
						
						successSound.play();
						Window.alert("Minimum Configurations Available!");
					}
				}
			}
		});
		
		// Set handler for when the center of gravity is changed.
		BUS.addHandler(GravityEvent.TYPE, new GravityEventHandler() {
			public void onEvent(final GravityEvent event) {
				ModuleConfiguration newFullConfig = new ModuleConfiguration();
				
				// If the center of gravity can be changed to specified coordinates.
				if (newFullConfig.calculateConfiguration(modList, event.xcoord, event.ycoord)) {
					MarsHabitatConfigurationSystem.successSound.play();	
					gravityPopup.hide();
					
					// Remove the current full configuration if it exists.
					if (configTabs.getWidgetCount() == MAX_TABS) {
						configTabs.remove(FULL_INDEX);
					}
					
					fullConfig.calculateConfiguration(modList, event.xcoord, event.ycoord);
					configTabs.add(ConfigurationMap.getConfigurationGrid(fullConfig), FULL_CONFIG);
					configTabs.selectTab(FULL_INDEX);
					
					Window.alert("Center of Gravity Changed!");
				} else {
					MarsHabitatConfigurationSystem.errorSound.play();
					Window.alert("Configuration can not move to that location!");
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

			// Adds minimum configuration two to the tab.
			configTabs.add(ConfigurationMap.getConfigurationGrid(min2Config), MIN2_CONFIG);
		}

		RootPanel.get().addStyleName("rootPanel");
	}
}
