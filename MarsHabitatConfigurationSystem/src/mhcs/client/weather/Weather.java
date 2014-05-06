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
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Creates a horizontal panel to store weather data.
 * @author Logan Dawson
 *
 */
public class Weather implements IsWidget {

	private static final int STATUS_CODE_OK = 200;
	private static final HorizontalPanel HP = new HorizontalPanel();
	private static final String JSON_ERROR_MESSAGE = "onError: Couldn't retrieve JSON";
	private static final String JSON_ERROR_MESSAGE2 = "RequestException: Couldn't retrieve JSON";
	private static final String JSON_ALERT_FIRST_HALF = "Couldn't retrieve JSON (";
	private static final String JSON_ALERT_SECOND_HALF = ")";
	private static final String QUOTATION = "\"";
	private static final String VERTICAL_BAR = "|";

	/**
	 * Default constructor, does nothing.
	 */
	public Weather() {
	}

	/**
	 * Method responsible for creating the horizontal panel and requesting the data.
	 */
	@Override
	public Widget asWidget() {

		// Centers the text
		HP.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		// Gets the logo ready and adds it to the panel.
		final Image logo = new Image("images/wundergroundLogo_4c_horz.jpg");
		logo.setWidth("90px");
		logo.setHeight("21px");
		logo.addStyleName("wundergroundLogo");
		HP.add(logo);

		// URLS for getting weather data.
		// Uncomment below for testing on my website.
		//String proxy = "http://d.umn.edu/~dawso172/war/Weather.php?url=";
		String proxy = "http://www.d.umn.edu/~stowe063/war/Weather.php?url="; 
		String url = proxy+"http://api.wunderground.com/api/0a4dcfa0929f5d9b/conditions/q/55812.json";
		String url2 = proxy+"http://api.wunderground.com/api/0a4dcfa0929f5d9b/astronomy/q/55812.json";

		url = URL.encode(url); 
		url2 = URL.encode(url2);

		// Gets the temperature and visibility.
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url); 

		try { 
			Request request = builder.sendRequest(null, new RequestCallback() { 

				public void onError(final Request request, final Throwable exception) { 
					Window.alert(JSON_ERROR_MESSAGE); 
				} 

				public void onResponseReceived(final Request request, final Response response) { 
					if (STATUS_CODE_OK == response.getStatusCode()) { 
						String rt = response.getText(); 
						this.update(rt);
					}
					else { 
						Window.alert(JSON_ALERT_FIRST_HALF + response.getStatusCode() + JSON_ALERT_SECOND_HALF); 
					} 
				}

				private void update(final String rt) {
					String sAll = rt; 
					JSONObject jA = (JSONObject)JSONParser.parseLenient(sAll); 
					JSONValue jTry = jA.get("current_observation"); 

					JSONObject jB = (JSONObject)JSONParser.parseLenient(jTry.toString()); 
					JSONValue temp = jB.get("temp_c");
					JSONValue visibility = jB.get("visibility_km"); 

					String sTemp = temp.toString(); 
					String sVisibility = visibility.toString(); 

					sVisibility = sVisibility.replaceAll(QUOTATION, "");

					HP.add(new Label(sTemp + " C" + "|Visibility: " + sVisibility + " km"));
					
				} 
			}); 
		}
		catch (RequestException e) { 
			Window.alert(JSON_ERROR_MESSAGE2); 
		}
		
		// Gets the time of sunset.
		RequestBuilder builder2 = new RequestBuilder(RequestBuilder.GET, url2); 

		try { 
			Request request2 = builder2.sendRequest(null, new RequestCallback() { 

				public void onError(final Request request, final Throwable exception) { 
					Window.alert(JSON_ERROR_MESSAGE); 
				} 

				public void onResponseReceived(final Request request, final Response response) { 
					if (STATUS_CODE_OK == response.getStatusCode()) { 
						String rt = response.getText(); 
						this.update(rt);
					}
					else { 
						Window.alert(JSON_ALERT_FIRST_HALF + response.getStatusCode() + JSON_ALERT_SECOND_HALF); 
					} 
				}

				private void update(final String rt) {
					String sAll = rt; 
					JSONObject jA = (JSONObject)JSONParser.parseLenient(sAll); 
					JSONValue jTry = jA.get("sun_phase"); 

					JSONObject jB = (JSONObject)JSONParser.parseLenient(jTry.toString()); 
					JSONValue sunset = jB.get("sunset"); 

					JSONObject jC = (JSONObject)JSONParser.parseLenient(sunset.toString());
					JSONValue hour = jC.get("hour");
					JSONValue minute = jC.get("minute");

					String sHour = hour.toString(); 
					String sMinute = minute.toString(); 

					sHour = sHour.replaceAll(QUOTATION, "");
					sMinute = sMinute.replaceAll(QUOTATION, "");

					HP.add(new Label(VERTICAL_BAR + "Time of Sunset: " + sHour + ":" + sMinute + VERTICAL_BAR));

				} 
			}); 
		}
		catch (RequestException e) { 
			Window.alert(JSON_ERROR_MESSAGE2); 
		}

		// For CSS purposes.
		HP.addStyleName("weatherPanel");
		
		// Returns the horizontal panel with weather data.
		return HP;
	}

}
