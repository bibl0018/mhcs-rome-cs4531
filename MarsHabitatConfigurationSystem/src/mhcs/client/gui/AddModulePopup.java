package mhcs.client.gui;

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

	/**
	 * Constructor for Popup. Creates a horizontal panel for each set of
	 * labels and textboxes.
	 */
	public AddModulePopup() {
		super(true);
		
		// Creates a title for the popup.
		Label title = new Label("Enter Module Details");
		
		// Sets the panel for entering the Module code.
		HorizontalPanel codePanel = new HorizontalPanel();
		Label codeLabel = new Label("Module Code:");
		TextBox codeBox = new TextBox();
		codeBox.setWidth(WIDTH);
		codePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		codePanel.add(codeLabel);
		codePanel.add(codeBox);
		setWidget(codePanel);
		
		// Set the panels for entering the X and Y coordinates.
		HorizontalPanel xCoordPanel = new HorizontalPanel();
		HorizontalPanel yCoordPanel = new HorizontalPanel();
		Label xLabel = new Label("X:");
		Label yLabel = new Label("Y:");
		TextBox xBox = new TextBox();
		TextBox yBox = new TextBox();
		xBox.setWidth(WIDTH);
		yBox.setWidth(WIDTH);
		xCoordPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		yCoordPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		xCoordPanel.add(xLabel);
		xCoordPanel.add(xBox);
		yCoordPanel.add(yLabel);
		yCoordPanel.add(yBox);
		
		// Creates the panel for the status of the module.
		HorizontalPanel statusPanel = new HorizontalPanel();
		Label statusLabel = new Label("Status:");
		final ListBox statusBox = new ListBox();
		statusBox.addItem("UNDAMAGED");
		statusBox.addItem("DAMAGED");
		statusBox.addItem("UNCERTAIN");
		statusBox.setWidth(listBoxWidth);
		statusPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		statusPanel.add(statusLabel);
		statusPanel.add(statusBox);
		
		// Creates the panel for the number of turns needed for the module.
		HorizontalPanel turnPanel = new HorizontalPanel();
		Label turnLabel = new Label("Orientation:");
		final ListBox turnBox = new ListBox();
		turnBox.addItem("0 TURNS NEEDED");
		turnBox.addItem("1 TURN NEEDED");
		turnBox.addItem("2 TURNS NEEDED");
		turnBox.setWidth(listBoxWidth);
		turnPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		turnPanel.add(turnLabel);
		turnPanel.add(turnBox);
		
		// Creates the panel for buttons.
		HorizontalPanel buttonPanel = new HorizontalPanel();
		Button addButton = new Button("Add Module");
		Button cancelButton = new Button("Cancel");
		buttonPanel.add(addButton);
		buttonPanel.add(cancelButton);
		
		// still need clickHandlers for buttons
		
		
		
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
		
		
		setWidget(mainPanel);
	}
    
    
    private static String WIDTH= "10em";
    private static String listBoxWidth = "11em";
}
