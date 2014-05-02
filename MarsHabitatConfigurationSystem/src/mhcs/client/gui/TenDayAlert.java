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
 * 
 * @author Logan Dawson
 *
 */
public class TenDayAlert extends PopupPanel {

	/**
	 * Change to 864000000 for 10 days.
	 */
	protected static final int ALERT_TIME = 120000;
	
	/**
	 * 
	 */
	private static final int MAGIC_NUMBER_10 = 5;
	
	/**
	 * 
	 */
	private static final int MAGIC_NUMBER_5 = 5;

	public TenDayAlert() {
		super(true);

		final Label title = new Label("Recalibrate Milometer Device");
		
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
		
//		ClickHandler cancelHandler = new ClickHandler() {
//			public void onClick(final ClickEvent event) {
//				hide();
//			}
//		};
		
		final HorizontalPanel buttonPanel = new HorizontalPanel();
		final Button calibrateButton = new Button("Alert in 10 days", calibrateHandler);
		//final Button cancelButton = new Button("Cancel", cancelHandler);
		buttonPanel.add(calibrateButton);
		//buttonPanel.add(cancelButton);
		buttonPanel.setSpacing(MAGIC_NUMBER_10);
		
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.add(title);
		mainPanel.add(buttonPanel);
		mainPanel.setSpacing(MAGIC_NUMBER_5);
		
		setWidget(mainPanel);
		setAutoHideEnabled(false);
		addStyleName("tenDayAlertPanel");
	}
}
