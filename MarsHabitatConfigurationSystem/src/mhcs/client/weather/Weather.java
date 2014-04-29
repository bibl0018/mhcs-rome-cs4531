package mhcs.client.weather;

import com.google.gwt.http.client.Request; 
import com.google.gwt.http.client.RequestBuilder; 
import com.google.gwt.http.client.RequestCallback; 
import com.google.gwt.http.client.RequestException; 
import com.google.gwt.http.client.Response; 
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Weather implements IsWidget {

	private static final String PANEL_WIDTH = "3120px";
	private static final String PANEL_HEIGHT = "1600px";
	private static final int STATUS_CODE_OK = 200;
	private static final VerticalPanel VP = new VerticalPanel();

	public Weather() {
	}

	@Override
	public Widget asWidget() {
		VP.setSize(PANEL_WIDTH, PANEL_HEIGHT);
		
		final Image logo = new Image("images/wundergroundLogo_4c.jpg");
		logo.setWidth("107px");
		logo.setHeight("63px");

		String proxy = "http://d.umn.edu/~stowe63/war/Weather.php?url="; 
		String url = proxy+"http://api.wunderground.com/api/0a4dcfa0929f5d9b/conditions/q/55812.json"; 

		url = URL.encode(url); 

		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url); 

		try { 
			Request request = builder.sendRequest(null, new RequestCallback() { 

				public void onError(final Request request, final Throwable exception) { 
					Window.alert("onError: Couldn't retrieve JSON"); 
				} 

				public void onResponseReceived(final Request request, final Response response) { 
					if (STATUS_CODE_OK == response.getStatusCode()) { 
						String rt = response.getText(); 
						this.update(rt);
					}
					else { 
						Window.alert("Couldn't retrieve JSON (" + response.getStatusCode() + ")"); 
					} 
				}

				private void update(final String rt) {
					//VerticalPanel VP = new VerticalPanel(); 
					//VP.add(new Label(rt)); //TO VIEW 

					String sAll = rt; 
					JSONObject jA = (JSONObject)JSONParser.parseLenient(sAll); 
					JSONValue jTry = jA.get("current_observation"); 

					JSONObject jB = (JSONObject)JSONParser.parseLenient(jTry.toString()); 
					JSONValue temp = jB.get("temp_c"); 
					JSONValue visibility = jB.get("visibility_km"); 

					String sTemp = temp.toString(); 
					String sVisibility = visibility.toString(); 

					VP.add(logo);
					VP.add(new Label("Temp in C: " + sTemp)); 
					VP.add(new Label("Visibility in Km: " + sVisibility));
					VP.addStyleName("weatherPanelText");

				} 
			}); 
		}
		catch (RequestException e) { 
			Window.alert("RequestException: Couldn't retrieve JSON"); 
		}

		VP.addStyleName("weatherPanel");
		return VP;
	}

}