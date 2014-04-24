package mhcs.client;

import mhcs.client.gui.AddModulePopup;
import mhcs.client.gui.Login;
import mhcs.client.gui.TenDayAlert;
import mhcs.client.module.ModuleList;
import mhcs.client.moduleConfigurations.ConfigurationMap;
import mhcs.client.moduleMap.ModuleMap;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

/**
 * Creates the GUI for the MHCS.
 */
public class MarsHabitatConfigurationSystem implements EntryPoint {

	private static final int MAGIC_NUMBER = 3;
	private static final SimpleEventBus BUS = new SimpleEventBus();
	private static final String MODULE_MAP_STRING = "Module Map";

	private String width = "1200px";
	private String height = "720px";

	public MarsHabitatConfigurationSystem() {

	}

	@Override
	public void onModuleLoad() {

		// Creates the module list.
		final ModuleList modList = new ModuleList();

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
			}
		};

		// Creates the module map.
		final ModuleMap modMap = new ModuleMap(modList);

		// Creates the menu for the menu bar.
		MenuBar theMenu = new MenuBar(true);
		theMenu.setAnimationEnabled(true);
		theMenu.addItem("Add Module", addModulePopupCmd);
		theMenu.addItem("Minumum Resource Path", cmd);
		theMenu.addItem("Calculate Habitats", cmd);
		theMenu.addItem("Milometer Device Calibration Alert", tenDayAlertCmd);
		theMenu.addSeparator();
		theMenu.addItem("Log off", loginCmd);

		// Creates the menu bar and adds the menu to itself.
		MenuBar menu = new MenuBar();
		menu.setAnimationEnabled(true);
		menu.addItem("Menu", theMenu);
		menu.setWidth(this.width);

		/**
		 * Added a configuration map to the first tab.
		 */
		final ConfigurationMap configMap = new ConfigurationMap(modList);
		
		// Creates the tabs for the various configurations and module map.
		final TabLayoutPanel configTabs = new TabLayoutPanel(2, Unit.EM);
		configTabs.add(modMap, MODULE_MAP_STRING);
		configTabs.add(configMap, "1");
		configTabs.add(new HTML(""), "2");
		configTabs.add(new HTML(""), "3");
		configTabs.add(new HTML(""), "4");
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
			}
		});

		rootPanel.addStyleName("rootPanel");
	}

}
