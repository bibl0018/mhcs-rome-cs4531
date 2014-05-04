package mhcs.client.gui;

import mhcs.client.AddEvent;
import mhcs.client.GPSDataTransfer;
import mhcs.client.MarsHabitatConfigurationSystem;
import mhcs.client.module.Module;
import mhcs.client.module.ModuleList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This is the GUI for the GPS Data Transfer (aka test cases)
 * @author Jeremiah Wilhelmy
 */
public class GPSDataPopup extends PopupPanel{

	private static final int MAIN_SPACE = 5;
	private static final int BUTTON_SPACE = 10;
	private static final String WIDTH= "10em";
	private static final String LIST_BOX_WIDTH = "11em";
	private static final String TC1 = "Test Case 1";
	private static final String TC2 = "Test Case 2";
	private static final String TC3 = "Test Case 3";
	private static final String TC4 = "Test Case 4";
	private static final String TC5 = "Test Case 5";
	private static final String TC6 = "Test Case 6";
	private static final String TC7 = "Test Case 7";
	private static final String TC8 = "Test Case 8";
	private static final String TC9 = "Test Case 9";
	private static final String TC10 = "Test Case 10";
	
	/**
	 * Creates a horizontal panel for the test case selector and adds it to a main panel
	 */
	public GPSDataPopup(final ModuleList modList, final SimpleEventBus bus){
		super(true);
		
		final Label title = new Label("Select Test Case");
		
		//Creates a horizontal panel used to select the test case.
		final HorizontalPanel testCasePanel = new HorizontalPanel();
		final ListBox testCaseBox = new ListBox();
		final Label testCaseLabel = new Label("Test Case:");
		testCaseBox.addItem("Test Case 1");
		testCaseBox.addItem("Test Case 2");
		testCaseBox.addItem("Test Case 3");
		testCaseBox.addItem("Test Case 4");
		testCaseBox.addItem("Test Case 5");
		testCaseBox.addItem("Test Case 6");
		testCaseBox.addItem("Test Case 7");
		testCaseBox.addItem("Test Case 8");
		testCaseBox.addItem("Test Case 9");
		testCaseBox.addItem("Test Case 10");
		testCaseBox.setWidth(LIST_BOX_WIDTH);
		testCasePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		testCasePanel.add(testCaseLabel);
		testCasePanel.add(testCaseBox);
		
		//Creates a panel for the submut and cancel buttons.
		final HorizontalPanel buttonPanel = new HorizontalPanel();
		final Button submitButton = new Button("Submit Test");
		final Button cancelButton = new Button("Cancel");
		buttonPanel.add(submitButton);
		buttonPanel.add(cancelButton);
		buttonPanel.setSpacing(BUTTON_SPACE);
		
		//Click handler for the submit button
		submitButton.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				boolean valid = true;
				
				try {
				
					String URL = "http://www.d.umn.edu/~abrooks/SomeTests.php?q=";
					
					if (testCaseBox.getItemText(testCaseBox.getSelectedIndex()).equals(TC1)) {
						URL = URL + "1";
					} else if (testCaseBox.getItemText(testCaseBox.getSelectedIndex()).equals(TC2)) {
						URL = URL + "2";
					} else if (testCaseBox.getItemText(testCaseBox.getSelectedIndex()).equals(TC3)) {
						URL = URL + "3";
					} else if (testCaseBox.getItemText(testCaseBox.getSelectedIndex()).equals(TC4)) {
						URL = URL + "4";
					} else if (testCaseBox.getItemText(testCaseBox.getSelectedIndex()).equals(TC5)) {
						URL = URL + "5";
					} else if (testCaseBox.getItemText(testCaseBox.getSelectedIndex()).equals(TC6)) {
						URL = URL + "6";
					} else if (testCaseBox.getItemText(testCaseBox.getSelectedIndex()).equals(TC7)) {
						URL = URL + "7";
					} else if (testCaseBox.getItemText(testCaseBox.getSelectedIndex()).equals(TC8)) {
						URL = URL + "8";
					} else if (testCaseBox.getItemText(testCaseBox.getSelectedIndex()).equals(TC9)) {
						URL = URL + "9";
					} else if (testCaseBox.getItemText(testCaseBox.getSelectedIndex()).equals(TC10)) {
						URL = URL + "10";
					} 
				
					//Creates a new GPS Data Transfer object
					GPSDataTransfer gps = new GPSDataTransfer(modList, URL);
					
					
				}
				catch (IndexOutOfBoundsException e) {
					MarsHabitatConfigurationSystem.errorSound.play();
					Window.alert(e.getMessage());
					valid = false;
				}
				catch (IllegalArgumentException e) {
					MarsHabitatConfigurationSystem.errorSound.play();
					Window.alert(e.getMessage());
					valid = false;
				}
				
				if (valid) {
					bus.fireEvent(new AddEvent());
					hide();
				}
			}			
		});
		
		
		//Hides the popup when the cancel button is pressed.
		cancelButton.addClickHandler(new ClickHandler () {
			public void onClick(final ClickEvent event) {
				hide();
			}
		});
		
		//Adds the two panels to the vertical panel.
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.add(title);
		
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		mainPanel.add(testCasePanel);
		
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.add(buttonPanel);
		mainPanel.setSpacing(MAIN_SPACE);
		
		setWidget(mainPanel);
	}
}
