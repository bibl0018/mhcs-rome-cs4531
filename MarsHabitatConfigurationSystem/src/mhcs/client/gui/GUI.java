// Just a rough start to the GUI

package mhcs.client.gui;

//import com.google.gwt.core.client.EntryPoint;
//import com.google.gwt.dom.client.Style.Unit;
//import com.google.gwt.user.client.Command;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.ui.HTML;
//import com.google.gwt.user.client.ui.MenuBar;
//import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.TabLayoutPanel;
//
//
//public class GUI implements EntryPoint {
//	public void onModuleLoad() {
//		RootPanel rootPanel = RootPanel.get();
//		rootPanel.setSize("995px", "650px");
//		
//		Command cmd = new Command() {
//			public void execute() {
//				Window.alert("You selected a menu item!");
//			}
//		};
//		Command addModulePopupCmd = new Command() {
//			public void execute() {
//				//new AddModulePopup(null).show();
//			}
//		};
//		
//		MenuBar theMenu = new MenuBar(true);
//		theMenu.setAnimationEnabled(true);
//		theMenu.addItem("Add module", addModulePopupCmd);
//		theMenu.addItem("Minumum resource path", cmd);
//		theMenu.addItem("Calculate habitats", cmd);
//		theMenu.addSeparator();
//		theMenu.addItem("Log off", cmd);
//		
//		MenuBar menu = new MenuBar();
//		menu.setAnimationEnabled(true);
//		menu.addItem("Menu", theMenu);
//		
//		TabLayoutPanel configTabs = new TabLayoutPanel(2, Unit.EM);
//	    configTabs.add(new HTML(""), "1");
//	    configTabs.add(new HTML(""), "2");
//	    configTabs.add(new HTML(""), "3");
//	    configTabs.add(new HTML(""), "4");
//	    configTabs.setHeight("650px");
//	    
//		rootPanel.add(menu);
//		rootPanel.add(configTabs);
//	}
//}
