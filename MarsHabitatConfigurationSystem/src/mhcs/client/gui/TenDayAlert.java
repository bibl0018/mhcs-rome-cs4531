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
				
				t.schedule(10000);
				// Uncomment below for 10 days
				//t.schedule(864000000);
			}
		};
		
		ClickHandler cancelHandler = new ClickHandler() {
			public void onClick(final ClickEvent event) {
				hide();
			}
		};
		
		final HorizontalPanel buttonPanel = new HorizontalPanel();
		final Button calibrateButton = new Button("Alert in 10 days", calibrateHandler);
		final Button cancelButton = new Button("Cancel", cancelHandler);
		buttonPanel.add(calibrateButton);
		buttonPanel.add(cancelButton);
		buttonPanel.setSpacing(10);
		
		VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		mainPanel.add(title);
		mainPanel.add(buttonPanel);
		mainPanel.setSpacing(5);
		
		setWidget(mainPanel);
		setAutoHideEnabled(false);
		addStyleName("tenDayAlertPanel");
	}
}
