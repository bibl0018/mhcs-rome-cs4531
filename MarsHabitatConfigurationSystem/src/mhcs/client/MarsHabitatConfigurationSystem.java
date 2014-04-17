package mhcs.client;

import mhcs.client.gui.AddModulePopup;
import mhcs.client.gui.Login;
import mhcs.client.module.ModuleList;
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

	@Override
	public void onModuleLoad() {

		// Creates the module list.
		final ModuleList modList = new ModuleList();

		// Creates the root panel and sizes it.
		RootPanel rootPanel = RootPanel.get();
		rootPanel.setSize(width, height);

		// Default command for menu items.
		Command cmd = new Command() {
			public void execute() {
				Window.alert("You selected a menu item!");
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
				final AddModulePopup popup = new AddModulePopup(modList, bus);
				popup.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
					public void setPosition(int offsetWidth, int offsetHeight) {
						int left = (Window.getClientWidth() - offsetWidth) / 3;
						int top = (Window.getClientHeight() - offsetHeight) / 3;
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
		theMenu.addItem("Add module", addModulePopupCmd);
		theMenu.addItem("Minumum resource path", cmd);
		theMenu.addItem("Calculate habitats", cmd);
		theMenu.addSeparator();
		theMenu.addItem("Log off", loginCmd);

		// Creates the menu bar and adds the menu to itself.
		MenuBar menu = new MenuBar();
		menu.setAnimationEnabled(true);
		menu.addItem("Menu", theMenu);
		menu.setWidth(width);

		// Creates the tabs for the various configurations and module map.
		final TabLayoutPanel configTabs = new TabLayoutPanel(2, Unit.EM);
		configTabs.add(modMap, "Module Map");
		configTabs.add(new HTML(""), "1");
		configTabs.add(new HTML(""), "2");
		configTabs.add(new HTML(""), "3");
		configTabs.add(new HTML(""), "4");
		configTabs.setHeight(height);
		configTabs.setWidth(width);
		
		// Adds everything to the root panel.
		rootPanel.add(menu);
		rootPanel.add(configTabs);
		
		// Show login after module has loaded.
		final Login initialLogin = new Login();
		initialLogin.show();
		
		// Set handler for EventBus.
		bus.addHandler(AddEvent.TYPE, new AddEventHandler() {
			public void onEvent(AddEvent event) {
				
				// "Refreshes" the module map by removing and re-adding the tab
				configTabs.remove(0);
				configTabs.insert(modMap, "Module Map", 0);
				configTabs.selectTab(0);
			}
		});
		
		rootPanel.addStyleName("rootPanel");
	}

	private final SimpleEventBus bus = new SimpleEventBus();
	private String width = "995px";
	private String height = "650px";
}
