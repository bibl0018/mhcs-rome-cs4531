package mhcs.client.gui;

import mhcs.client.module.Module;
import mhcs.client.module.ModuleList;
import mhcs.client.AddEvent;
import mhcs.client.MarsHabitatConfigurationSystem;

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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * The GUI Popup for adding Modules to the MHCS.
 * @author Ryan Stowell
 */
public class AddModulePopup extends PopupPanel {

	private static final int MAIN_SPACE = 5;
	private static final int BUTTON_SPACE = 10;
	private static final String WIDTH= "10em";
	private static final String LIST_BOX_WIDTH = "11em";
	private static final String ZERO_TURNS = "0 TURNS NEEDED";
	private static final String ONE_TURN = "1 TURN NEEDED";
	private static final String TWO_TURNS = "2 TURNS NEEDED";
	
	/**
	 * Constructor for Popup. Creates a horizontal panel for each set of
	 * labels and textboxes.
	 */
	public AddModulePopup(final ModuleList modules, final SimpleEventBus bus) {
		super(true);
		
		// Creates a title for the popup.
		final Label title = new Label("Enter Module Details");
		
		// Sets the panel for entering the Module code.
		final HorizontalPanel codePanel = new HorizontalPanel();
		final Label codeLabel = new Label("Module Code:");
		final TextBox codeBox = new TextBox();
		codeBox.setWidth(WIDTH);
		codePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		codePanel.add(codeLabel);
		codePanel.add(codeBox);
		setWidget(codePanel);
		
		// Set the panels for entering the X and Y coordinates.
		final HorizontalPanel xCoordPanel = new HorizontalPanel();
		final HorizontalPanel yCoordPanel = new HorizontalPanel();
		final Label xLabel = new Label("X:");
		final Label yLabel = new Label("Y:");
		final TextBox xBox = new TextBox();
		final TextBox yBox = new TextBox();
		xBox.setWidth(WIDTH);
		yBox.setWidth(WIDTH);
		xCoordPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		yCoordPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		xCoordPanel.add(xLabel);
		xCoordPanel.add(xBox);
		yCoordPanel.add(yLabel);
		yCoordPanel.add(yBox);
		
		// Creates the panel for the status of the module.
		final HorizontalPanel statusPanel = new HorizontalPanel();
		final Label statusLabel = new Label("Status:");
		final ListBox statusBox = new ListBox();
		statusBox.addItem("undamaged");
		statusBox.addItem("repairable");
		statusBox.addItem("damaged");
		statusBox.setWidth(LIST_BOX_WIDTH);
		statusPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		statusPanel.add(statusLabel);
		statusPanel.add(statusBox);
		
		// Creates the panel for the number of turns needed for the module.
		final HorizontalPanel turnPanel = new HorizontalPanel();
		final Label turnLabel = new Label("Orientation:");
		final ListBox turnBox = new ListBox();
		turnBox.addItem(ZERO_TURNS);
		turnBox.addItem(ONE_TURN);
		turnBox.addItem(TWO_TURNS);
		turnBox.setWidth(LIST_BOX_WIDTH);
		turnPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		turnPanel.add(turnLabel);
		turnPanel.add(turnBox);
		
		// Creates the panel for buttons.
		final HorizontalPanel buttonPanel = new HorizontalPanel();
		final Button addButton = new Button("Add Module");
		final Button cancelButton = new Button("Cancel");
		buttonPanel.add(addButton);
		buttonPanel.add(cancelButton);
		buttonPanel.setSpacing(BUTTON_SPACE);
		
		// Add button click handler. 
		addButton.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				boolean valid = true;
				
				try {
				
					int code = Integer.parseInt(codeBox.getText());
					int xCoord = Integer.parseInt(xBox.getText());
					int yCoord = Integer.parseInt(yBox.getText());
					int turns;
				
				
					if (turnBox.getItemText(turnBox.getSelectedIndex()).equals(ZERO_TURNS)) {
						turns = 0;
					} else if (turnBox.getItemText(turnBox.getSelectedIndex()).equals(ONE_TURN)) {
						turns = 1;
					} else {
						turns = 2;
					}
				
					// Tries creating a module from the input given by the user. A alert window appears if an exception is thrown.
				
					Module module = new Module(code, xCoord, yCoord, turns, statusBox.getItemText(statusBox.getSelectedIndex()));			
					modules.addModule(module);
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
		
		// Popup is hidden when cancel button is clicked.
		cancelButton.addClickHandler(new ClickHandler () {
			public void onClick(final ClickEvent event) {
				hide();
			}
		});
		
		// Puts each panel into a main vertical panel.
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.add(title);
		
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		mainPanel.add(codePanel);
		mainPanel.add(xCoordPanel);
		mainPanel.add(yCoordPanel);
		mainPanel.add(statusPanel);
		mainPanel.add(turnPanel);
		
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.add(buttonPanel);
		mainPanel.setSpacing(MAIN_SPACE);
		
		
		setWidget(mainPanel);
	}
}
