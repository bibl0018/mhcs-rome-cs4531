package mhcs.client;

import com.google.gwt.core.client.EntryPoint;
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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class GPSDataTransfer implements EntryPoint{

	
	public void onModuleLoad() {
		String proxy = "http://www.d.umn.edu/~wilh0137/Proxy.php?url=";
		String url = proxy+"http://www.d.umn.edu/~abrooks/SomeTests.php?q=1";
		url = URL.encode(url);
		
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
		
		try {
			Request request = builder.sendRequest(null, new RequestCallback() {
				public void onError(Request request,Throwable exception){
					Window.alert("onError: Couldn't retrieve JSON");
				}
				
				public void onResponseReceived(Request request, Response response) {
					if (200 == response.getStatusCode()){
						String rt = response.getText();
						//update(rt);
					} else {
						Window.alert("Couldn't retrieve JSON (" + response.getStatusText() + ")");
					}
				}
				
				
			});
		} catch (RequestException e){
			Window.alert("RequestException: Couldn't retrieve JSON");
		}
		
		
	}

	public void update(String rt){
		VerticalPanel vp = new VerticalPanel();
		vp.add(new Label(rt));
		RootLayoutPanel.get().add(vp);
		
		String sAll = rt;
		JSONArray jA = 
				(JSONArray) JSONParser.parseLenient(sAll);

		
		
		JSONNumber jN;
		JSONString jS;
		double d;
		String s;
		
		for (int i = 0; i < jA.size(); i++){
			JSONObject jO = (JSONObject) jA.get(i);
			jN = (JSONNumber) jO.get("code");
			d = jN.doubleValue();
			vp.add(new Label(Double.toString(d)));
			
			jS = (JSONString) jO.get("status");
			s = jS.stringValue();
			vp.add(new Label(s));
			
			jN = (JSONNumber) jO.get("turns");
			d = jN.doubleValue();
			vp.add(new Label(Double.toString(d)));
			
			jN = (JSONNumber) jO.get("X");
			d = jN.doubleValue();
			vp.add(new Label(Double.toString(d)));
			
			jN = (JSONNumber) jO.get("Y");
			d = jN.doubleValue();
			vp.add(new Label(Double.toString(d)));
			vp.add(new HTML("<hr />"));
		}
		
		RootLayoutPanel.get().add(vp);
	}
	
}
