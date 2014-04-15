package mhcs.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * GUI popup for the login.
 * @author Logan Dawson
 *
 */
public class Login extends PopupPanel {
	
	/**
	 * Constructor for login popup.
	 */
	public Login() {
		super(true);
		
		final Label title = new Label("Mars Habitat Configuration System Login");
		title.addStyleName("title");
		final Label username = new Label("Username:");
		final Label password = new Label("Password:");
		
		final PasswordTextBox ptb = new PasswordTextBox();
		final TextBox tb = new TextBox();
		
		ClickHandler handler = new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (ptb.getText().equals(pass) &&
					tb.getText().equals(user))
				hide();
			}
		};
		final Button loginButton = new Button("Login", handler);
		
		VerticalPanel loginPanel = new VerticalPanel();
		loginPanel.add(username);
		loginPanel.add(tb);
		loginPanel.add(password);
		loginPanel.add(ptb);
		loginPanel.add(loginButton);
		loginPanel.setSpacing(1);
		loginPanel.addStyleName("loginPanel");
		
		VerticalPanel panel = new VerticalPanel();
		panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		panel.add(title);
		panel.add(loginPanel);
		panel.setSpacing(2);
		panel.setSize("995px", "750px");
		panel.addStyleName("panel");
		
		setWidget(panel);
		setAutoHideEnabled(false);
	}

	/**
	 * Default password for the MHCS.
	 */
	private String pass = "CS4531";
	
	/**
	 * Default user for the MHCS.
	 */
	private String user = "Rome";
}
