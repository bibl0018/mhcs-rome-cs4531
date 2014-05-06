package mhcs.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Creates the 10 day alert popup which will continue to popup every 10 days.
 * @author Logan Dawson
 *
 */
public class TenDayAlert extends PopupPanel {

	/**
	 * 864000000 milliseconds == 10 days.
	 */
	protected static final int ALERT_TIME = 864000000;
	private static final int MAGIC_NUMBER_10 = 5;
	private static final int MAGIC_NUMBER_5 = 5;

	/**
	 * Function responsible for putting together the 10 day alert popup.
	 */
	public TenDayAlert() {
		super(true);

		// Title of the popup.
		final Label title = new Label("Recalibrate Milometer Device");
		
		// Click handler for "Alert in 10 days" button.
		ClickHandler calibrateHandler = new ClickHandler() {
			public void onClick(final ClickEvent event) {
				hide();
				Timer t = new Timer() {
					public void run() {
						show();
					}
				};
				
				t.schedule(ALERT_TIME);
			}
		};
		
		// The panel for the button.
		final HorizontalPanel buttonPanel = new HorizontalPanel();
		final Button calibrateButton = new Button("Alert in 10 days", calibrateHandler);
		buttonPanel.add(calibrateButton);
		buttonPanel.setSpacing(MAGIC_NUMBER_10);
		
		// The panel for the title and button panel.
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.add(title);
		mainPanel.add(buttonPanel);
		mainPanel.setSpacing(MAGIC_NUMBER_5);
		
		setWidget(mainPanel);
		
		// Makes sure the user doesn't click out of the popup on accident.
		setAutoHideEnabled(false);
		
		// For CSS purposes.
		addStyleName("tenDayAlertPanel");
	}
}
