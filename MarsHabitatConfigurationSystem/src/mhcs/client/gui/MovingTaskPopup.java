package mhcs.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Builds a popup for the size of the moving task.
 * @author Ryan Stowell
 */
public class MovingTaskPopup extends PopupPanel {

	private static final int MAIN_SPACE = 5;
	
	/**
	 * Constructs the popup.
	 * @param size The size to be output in the label.
	 */
	public MovingTaskPopup(final double size) {
		
		// Creates a title for the popup.
		final Label title = new Label("Center of Gravity: " + Double.toString(size));
		
		// Creates a button for hiding the panel.
		final Button okButton = new Button("OK");
		
		// Popup is hidden when cancel button is clicked.
		okButton.addClickHandler(new ClickHandler () {
			public void onClick(final ClickEvent event) {
				hide();
			}
		});
		
		// Set main panel.
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.add(title);
		mainPanel.add(okButton);
		mainPanel.setSpacing(MAIN_SPACE);
		
		setWidget(mainPanel);
	}
}
