package mhcs.client.moduleConfigurations;

/**
 * A class that stores coordinates of each module type in a configuration.
 * @author Jeremiah Wilhelmy
 */

import java.util.ArrayList;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.storage.client.Storage;


public class ModuleConfiguration{
	
	private static final String KEY = "MHCS.Configuration.";
	private static final String COMMA_Y = ",Y:";
	private static final String END_BRACKET = "},";
	
	/**
	 * An array list for each module type. This allows Array List 
	 * methods to be called on each list, such as .isEmpty() or .add().
	 */
	public ArrayList<Coordinates> plainModules;
	public ArrayList<Coordinates> dormitoryModules;
	public ArrayList<Coordinates> sanitationModules;
	public ArrayList<Coordinates> controlModules;
	public ArrayList<Coordinates> foodAndWaterModules;
	public ArrayList<Coordinates> gymAndRelaxationModules;
	public ArrayList<Coordinates> canteenModules;
	public ArrayList<Coordinates> powerModules;
	public ArrayList<Coordinates> airlockModules;
	public ArrayList<Coordinates> medicalModules;

	/**
	 * Default constructor.
	 */
	public ModuleConfiguration(){
		this.plainModules = new ArrayList<Coordinates>();
		this.dormitoryModules = new ArrayList<Coordinates>();
		this.sanitationModules = new ArrayList<Coordinates>();
		this.controlModules = new ArrayList<Coordinates>();
		this.foodAndWaterModules = new ArrayList<Coordinates>();
		this.gymAndRelaxationModules = new ArrayList<Coordinates>();
		this.canteenModules = new ArrayList<Coordinates>();
		this.powerModules = new ArrayList<Coordinates>();
		this.airlockModules = new ArrayList<Coordinates>();
		this.medicalModules = new ArrayList<Coordinates>();
	}
	
	/**
	 * Adds a set of coordinates to the corresponding list of modules.
	 * @param x The X coordinate.
	 * @param y The Y coordinate.
	 */
	public void addPlain(final int x, final int y){
		Coordinates coord = new Coordinates(x,y);
		this.plainModules.add(coord);
	}
	
	public void addDormitory(final int x, final int y){
		Coordinates coord = new Coordinates(x,y);
		this.dormitoryModules.add(coord);
	}
	
	public void addSanitation(final int x, final int y){
		Coordinates coord = new Coordinates(x,y);
		this.sanitationModules.add(coord);
	}
	
	public void addControl(final int x, final int y){
		Coordinates coord = new Coordinates(x,y);
		this.controlModules.add(coord);
	}
	
	public void addFoodAndWater(final int x, final int y){
		Coordinates coord = new Coordinates(x,y);
		this.foodAndWaterModules.add(coord);
	}
	
	public void addGymAndRelaxation(final int x, final int y){
		Coordinates coord = new Coordinates(x,y);
		this.gymAndRelaxationModules.add(coord);
	}
	
	public void addCanteen(final int x, final int y){
		Coordinates coord = new Coordinates(x,y);
		this.canteenModules.add(coord);
	}
	
	public void addPower(final int x, final int y){
		Coordinates coord = new Coordinates(x,y);
		this.powerModules.add(coord);
	}
	
	public void addAirlock(final int x, final int y){
		Coordinates coord = new Coordinates(x,y);
		this.airlockModules.add(coord);
	}
	
	public void addMedical(final int x, final int y){
		Coordinates coord = new Coordinates(x,y);
		this.medicalModules.add(coord);
	}
	
	/**
	 * A small class that is simply a collection of two 
	 * integers: the X coordinate and the Y coordinate.
	 * @author Jeremiah Wilhelmy
	 */
	public class Coordinates{
		int xCoord;
		int yCoord;
		
		public Coordinates(final int x, final int y){
			this.xCoord = x;
			this.yCoord = y;
		}
	}	
	
	
	/**
	 * Saves this module configuration onto HTML5 local storage.
	 * @param name The name of this configuration (i.e. "Minimum Configuration 1").
	 */
	public void saveConfiguration(final String name) {
		Storage store = null;
		store = Storage.getLocalStorageIfSupported();
		
		String value = "[";
		final String key = KEY + name;
		
		for (int i = 0; i < this.airlockModules.size(); i += 1) {
			value = value + "{type:\"airlock\",X:" + Integer.toString(this.airlockModules.get(i).xCoord) +
							COMMA_Y + Integer.toString(this.airlockModules.get(i).yCoord) + END_BRACKET;
	    }
	    for (int i = 0; i < this.canteenModules.size(); i += 1) {
	    	value = value + "{type:\"canteen\",X:" + Integer.toString(this.canteenModules.get(i).xCoord) +
					COMMA_Y + Integer.toString(this.canteenModules.get(i).yCoord) + END_BRACKET;
	    }
	    for (int i = 0; i < this.controlModules.size(); i += 1) {	
	    	value = value + "{type:\"control\",X:" + Integer.toString(this.controlModules.get(i).xCoord) +
					COMMA_Y + Integer.toString(this.controlModules.get(i).yCoord) + END_BRACKET;
	    }
	    for (int i = 0; i < this.dormitoryModules.size(); i += 1) {
	    	value = value + "{type:\"dormitory\",X:" + Integer.toString(this.dormitoryModules.get(i).xCoord) +
					COMMA_Y + Integer.toString(this.dormitoryModules.get(i).yCoord) + END_BRACKET;
	    }
	    for (int i = 0; i < this.foodAndWaterModules.size(); i += 1) {
	    	value = value + "{type:\"foodAndWater\",X:" + Integer.toString(this.foodAndWaterModules.get(i).xCoord) +
					COMMA_Y + Integer.toString(this.foodAndWaterModules.get(i).yCoord) + END_BRACKET;
	    }
	    for (int i = 0; i < this.gymAndRelaxationModules.size(); i += 1) {
	    	value = value + "{type:\"gymAndRelaxation\",X:" + Integer.toString(this.gymAndRelaxationModules.get(i).xCoord) +
					COMMA_Y + Integer.toString(this.gymAndRelaxationModules.get(i).yCoord) + END_BRACKET;
	    }
	    for (int i = 0; i < this.medicalModules.size(); i += 1) {
	    	value = value + "{type:\"medical\",X:" + Integer.toString(this.medicalModules.get(i).xCoord) +
					COMMA_Y + Integer.toString(this.medicalModules.get(i).yCoord) + END_BRACKET;
	    }
	    for (int i = 0; i < this.plainModules.size(); i += 1) {
	    	value = value + "{type:\"plain\",X:" + Integer.toString(this.plainModules.get(i).xCoord) +
					COMMA_Y + Integer.toString(this.plainModules.get(i).yCoord) + END_BRACKET;
	    }
	    for (int i = 0; i < this.powerModules.size(); i += 1) {
	    	value = value + "{type:\"power\",X:" + Integer.toString(this.powerModules.get(i).xCoord) +
					COMMA_Y + Integer.toString(this.powerModules.get(i).yCoord) + END_BRACKET;
	    }
	    for (int i = 0; i < this.sanitationModules.size(); i += 1) {
	    	value = value + "{type:\"sanitation\",X:" + Integer.toString(this.sanitationModules.get(i).xCoord) +
					COMMA_Y + Integer.toString(this.sanitationModules.get(i).yCoord) + END_BRACKET;
	    }
		
	    // Removes the final ',' placed by the loops.
	    value = value.substring(0, value.length() - 2);
	    value = value + "]";
	    
		if (store != null) {
			store.setItem(key, value);
		}
	}
	
	/**
	 * Loads a configuration from local storage into this ModuleConfiguration object.
	 * @param name The name of the configuration to load.
	 */
	public void loadConfiguration(final String name) {
		Storage store = null;
		store = Storage.getLocalStorageIfSupported();
		
		if (store == null) {
			return;
		}
		
		String key = KEY + name;
		String value = store.getItem(key);
		
		if (value != null) {
			JSONArray array = (JSONArray) JSONParser.parseLenient(value);
			JSONNumber number;
			JSONString string;
			String type;
			int xCoord;
			int yCoord;
			
			for (int k = 0; k < array.size(); k += 1) {
				JSONObject object = (JSONObject) array.get(k);
				string = (JSONString) object.get("code");
				type = string.stringValue();
				
				number = (JSONNumber) object.get("X");
				xCoord = (int) number.doubleValue();
				
				number = (JSONNumber) object.get("Y");
				yCoord = (int) number.doubleValue();
				
				if ("airlock".equals(type)) {
					this.airlockModules.add(new Coordinates(xCoord, yCoord));
				} else if ("canteen".equals(type)) {
					this.canteenModules.add(new Coordinates(xCoord, yCoord));
				} else if ("control".equals(type)) {
					this.controlModules.add(new Coordinates(xCoord, yCoord));
				} else if ("dormitory".equals(type)) {
					this.dormitoryModules.add(new Coordinates(xCoord, yCoord));
				} else if ("foodAndWater".equals(type)) {
					this.foodAndWaterModules.add(new Coordinates(xCoord, yCoord));
				} else if ("gymAndRelaxation".equals(type)) {
					this.gymAndRelaxationModules.add(new Coordinates(xCoord, yCoord));
				} else if ("medical".equals(type)) {
					this.medicalModules.add(new Coordinates(xCoord, yCoord));
				} else if ("plain".equals(type)) {
					this.plainModules.add(new Coordinates(xCoord, yCoord));
				} else if ("power".equals(type)) {
					this.powerModules.add(new Coordinates(xCoord, yCoord));
				} else if ("sanitation".equals(type)) {
					this.sanitationModules.add(new Coordinates(xCoord, yCoord));
				}
			}
		}
	}
}
