package mhcs.client;

import mhcs.client.gui.AddModulePopup;
import mhcs.client.module.ModuleList;
import mhcs.client.moduleMap.ModuleMap;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
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
		final ModuleList modList = new ModuleList();
		
		RootPanel rootPanel = RootPanel.get();
		rootPanel.setSize("995px", "650px");

		Command cmd = new Command() {
			public void execute() {
				Window.alert("You selected a menu item!");
			}
		};
		Command addModulePopupCmd = new Command() {
			public void execute() {
				final AddModulePopup popup = new AddModulePopup(modList);
				popup.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
					public void setPosition(int offsetWidth, int offsetHeight) {
						int left = (Window.getClientWidth() - offsetWidth) / 3;
						int top = (Window.getClientHeight() - offsetHeight) / 3;
						popup.setPopupPosition(left, top);
					}
				});
			}
		};
		
		ModuleMap modMap = new ModuleMap();
		//modMap.onModuleLoad(modList);

		MenuBar theMenu = new MenuBar(true);
		theMenu.setAnimationEnabled(true);
		theMenu.addItem("Add module", addModulePopupCmd);
		theMenu.addItem("Minumum resource path", cmd);
		theMenu.addItem("Calculate habitats", cmd);
		theMenu.addSeparator();
		theMenu.addItem("Log off", cmd);

		MenuBar menu = new MenuBar();
		menu.setAnimationEnabled(true);
		menu.addItem("Menu", theMenu);

		TabLayoutPanel configTabs = new TabLayoutPanel(2, Unit.EM);
		configTabs.add(new Image("images/MarsModuleLandingArea.jpg"), "1");
		configTabs.add(new Image("images/MarsModuleLandingArea.jpg"), "2");
		configTabs.add(new Image("images/MarsModuleLandingArea.jpg"), "3");
		configTabs.add(new Image("images/MarsModuleLandingArea.jpg"), "4");
		configTabs.insert(, 0);
		configTabs.setHeight("650px");

		rootPanel.add(menu);
		rootPanel.add(configTabs);
	}

}
