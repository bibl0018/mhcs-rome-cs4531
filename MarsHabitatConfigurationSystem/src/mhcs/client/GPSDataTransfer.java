package mhcs.client;

/**
 * Takes in modules from a web server using a proxy and adds them to a module 
 * list. Some code taken from Tech-Talk 4 by Andrew Brooks.
 * @author Jeremiah Wilhelmy
 */

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.user.client.Window;

import mhcs.client.module.Module;
import mhcs.client.module.ModuleList;


public class GPSDataTransfer {	
	
	public GPSDataTransfer(final ModuleList modList, final String incomingURL, final SimpleEventBus bus) {
		
		//Connects to the tests through a proxy
		String proxy = "http://www.d.umn.edu/~stowe063/war/Proxy.php?url=";

		String url = proxy + incomingURL;
		url = URL.encode(url);
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		
		try {
			Request request = builder.sendRequest(null, new RequestCallback() {
				
				public void onError(final Request request, final Throwable exception) {
					Window.alert("onError: Couldn't retrieve JSON");
				}
				
				//Displays an alert with the error code if JSON retrieval is unsuccessful.
				public void onResponseReceived(final Request request, final Response response) {
					if (200 == response.getStatusCode()) {
						modList.clearList();
						String rt = response.getText();
						update(rt, modList);
						bus.fireEvent(new AddEvent());
					} else {
						Window.alert("Couldn't retrieve JSON (" + response.getStatusText() + ")");
					}
				}	
			});
			
		} catch (RequestException e) {
			Window.alert("RequestException: Couldn't retrieve JSON");
		}
		
	}

	/**
	 * Converts the string into multiple modules and adds them to the module list.
	 * @param rt The string received from the web server.
	 */
	public void update(final String rt, final ModuleList modList) {
		String sAll = rt;
		JSONArray jA = (JSONArray) JSONParser.parseLenient(sAll);
		
		JSONNumber jN;
		JSONString jS;
		int code;
		String status;
		int turns;
		int xCoord;
		int yCoord;
		
		//Parses each JSON object for values and adds them to temporary variables.
		for (int i = 0; i < jA.size(); i++) {
			JSONObject jO = (JSONObject) jA.get(i);
			jN = (JSONNumber) jO.get("code");
			code = (int) jN.doubleValue();
			
			jS = (JSONString) jO.get("status");
			status = jS.stringValue();
			
			jN = (JSONNumber) jO.get("turns");
			turns = (int) jN.doubleValue();
			
			jN = (JSONNumber) jO.get("X");
			xCoord = (int) jN.doubleValue();
			
			jN = (JSONNumber) jO.get("Y");
			yCoord = (int) jN.doubleValue();
			
			//A new module is created with the information from the string, 
			//then added to the module list.
			Module module = new Module(code, xCoord, yCoord, turns, status);
			modList.addModule(module);
		}		
	}
	
}
