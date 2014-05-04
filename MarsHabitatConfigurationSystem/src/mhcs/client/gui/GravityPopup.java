package mhcs.client.gui;

import mhcs.client.GravityEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GravityPopup extends PopupPanel {

	private static final int MAIN_SPACE = 5;
	private static final String WIDTH= "4em";
	private static final int BUTTON_SPACE = 10;
	
	/**
	 * Constructs a popup for changing the center of gravity.
	 * @param config The full configuration.
	 */
	public GravityPopup(final SimpleEventBus bus) {
		
		
		// Creates a title for the popup.
		final Label title = new Label("Enter Center of Gravity");
				
		
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
		
		// Creates the panel for buttons.
		final HorizontalPanel buttonPanel = new HorizontalPanel();
		final Button addButton = new Button("Submit Changes");
		final Button cancelButton = new Button("Cancel");
		buttonPanel.add(addButton);
		buttonPanel.add(cancelButton);
		buttonPanel.setSpacing(BUTTON_SPACE);
		
		// Add button click handler. 
		addButton.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				int xCoord = Integer.parseInt(xBox.getText());
				int yCoord = Integer.parseInt(yBox.getText());
				
				bus.fireEvent(new GravityEvent(xCoord, yCoord));
			}			
		});
		
		// Popup is hidden when cancel button is clicked.
		cancelButton.addClickHandler(new ClickHandler () {
			public void onClick(final ClickEvent event) {
				hide();
			}
		});
				
		
		// The main panel for the subpanels.
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.add(title);
		
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		mainPanel.add(xCoordPanel);
		mainPanel.add(yCoordPanel);
		
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.add(buttonPanel);
		mainPanel.setSpacing(MAIN_SPACE);
		
		setWidget(mainPanel);
	}
}
