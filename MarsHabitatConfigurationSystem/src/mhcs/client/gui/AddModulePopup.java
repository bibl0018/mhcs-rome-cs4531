package mhcs.client.gui;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
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
		xCoordPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		yCoordPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		xCoordPanel.add(xLabel);
		xCoordPanel.add(xBox);
		yCoordPanel.add(yLabel);
		yCoordPanel.add(yBox);
		
		// Creates the panel for the status of the module.
		HorizontalPanel statusPanel = new HorizontalPanel();
		Label statusLabel = new Label("Status");
		
		
		// Puts each panel into a main vertical panel.
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.add(title);
		
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		mainPanel.add(codePanel);
		mainPanel.add(xCoordPanel);
		mainPanel.add(yCoordPanel);
		
		setWidget(mainPanel);
	}
}
