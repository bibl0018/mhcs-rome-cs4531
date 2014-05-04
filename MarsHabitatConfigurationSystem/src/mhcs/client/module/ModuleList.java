package mhcs.client.module;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.storage.client.Storage;

/**
 * A container for all Modules in the MHCS.
 * @author Ryan Stowell
 */
public class ModuleList {

	public static final int SIZE = 191;
	public static final String invalid = "Invalid code parameter";
	public static final String KEY = "MHCS.Module.";
	public static final int LOWER_BOUND = 40;
	public static final int UPPER_BOUND = 50;
	
	private Module[] modules;
	private int plain;
	private int dormitory;
	private int canteen;
	private int control;
	private int sanitation;
	private int water;
	private int gym;
	private int power;
	private int airlock;
	private int medical;
	
    /**
	 * Default constructor. Initializes each Module in list to null.
	 */
	public ModuleList() {
		this.modules = new Module[SIZE];
		
		for (int i = 0; i < SIZE; i += 1) {
			this.modules[i] = null;
		}
		loadModules();
	}
	
	/**
	 * Getter for a Module in the ModuleList.
	 * @param code The Module to get.  1-190
	 * @return The Module at index code, null if the Module is not found
	 * @throws IndexOutOfBoundsException if code is not between 1-190
	 */
	public Module getModule(final int code) {
		if (code < 1 || code >= SIZE) {
			throw new IndexOutOfBoundsException(invalid);
		}
		
		return this.modules[code];
	}
	
	/**
	 * Add a Module to the ModuleList.
	 * @param module The Module to be added.
	 * @throws IllegalArgumentException if a Module with the same code already exists in the list
	 */
	public void addModule(final Module module) {
		if (this.modules[module.getCode()] != null) {
			throw new IllegalArgumentException("Module with code:" + Integer.toString(module.getCode()) + " already exists");
		}
		
		for (int i = 1; i < SIZE; i += 1) {
			if (this.modules[i] != null && 
				this.modules[i].getXCoord() == module.getXCoord() &&
				this.modules[i].getYCoord() == module.getYCoord() ) {
				throw new IllegalArgumentException("Module already exists at that location");
			}
		}
		
		// If Module is not damaged and does not lie out of the landing zone the number of
		// usable modules is incremented.
		if (!module.getStatus().equals(Module.DAMAGED) || 
		    (module.getXCoord() >= LOWER_BOUND && module.getXCoord() <= UPPER_BOUND && 
		     module.getYCoord() >= LOWER_BOUND && module.getYCoord() <= UPPER_BOUND) ) {
			if (module.getType().equals(Module.Type.PLAIN)){
				this.plain += 1;
			}
			else if (module.getType().equals(Module.Type.DORMITORY)){
				this.dormitory += 1;
			}
			else if (module.getType().equals(Module.Type.SANITATION)){
				this.sanitation += 1;
			}
			else if (module.getType().equals(Module.Type.FOOD_WATER)){
				this.water += 1;
			}
			else if (module.getType().equals(Module.Type.GYM_RELAXATION)){
				this.gym += 1;
			}
			else if (module.getType().equals(Module.Type.CANTEEN)){
				this.canteen += 1;
			}
			else if (module.getType().equals(Module.Type.POWER)){
				this.power += 1;
			}
			else if (module.getType().equals(Module.Type.CONTROL)){
				this.control += 1;
			}
			else if (module.getType().equals(Module.Type.MEDICAL)){
				this.medical += 1;
			}
			else if (module.getType().equals(Module.Type.AIRLOCK)) {
				this.airlock += 1;
			}
		}
		
		this.modules[module.getCode()] = module;
		saveModule(module);
	}
	
	/**
	 * Deletes the Module with number "code" in ModuleList.
	 * @param code The Module to delete.
	 * @throws IllegalArgumentException if there is no Module with code number "code"
	 * @throws IndexOutOfBoundsException if code is not between 1-190
	 */
	public void deleteModule(final int code) {
		if (code < 1 || code >= SIZE) {
			throw new IndexOutOfBoundsException(invalid);
		}
		
		this.modules[code] = null;
		
		Storage store = Storage.getLocalStorageIfSupported();
		final String key = KEY + Integer.toString(code);
		
		if (store != null) {
			store.removeItem(key);
		}
	}
	
	public int getNumOfPlain() {
		return this.plain;
	}
	
	public int getNumOfControl() {
		return this.control;
	}
	
	public int getNumOfAirlock() {
		return this.airlock;
	}
	
	public int getNumOfDormitory() {
		return this.dormitory;
	}
	
	public int getNumOfSanitation() {
		return this.sanitation;
	}
	
	public int getNumOfCanteen() {
		return this.canteen;
	}
	
	public int getNumOfPower() {
		return this.power;
	}
	
	public int getNumOfMedical() {
		return this.medical;
	}
	
	public int getNumOfGym() {
		return this.gym;
	}
	
	public int getNumOfWater() {
		return this.water;
	}
	
	
	/**
	 * Loads all Modules from HTML5 local storage into this module list.
	 */
	private void loadModules() {
		Storage store = null;
		store = Storage.getLocalStorageIfSupported();
		
		if (store != null) {
			for (int i = 1; i < SIZE; i += 1) {
				String key = KEY + Integer.toString(i);
				String value = store.getItem(key);
				
				if (value != null) {
					JSONArray array = (JSONArray) JSONParser.parseLenient(value);
					JSONNumber number;
					JSONString string;
					int code;
					int xCoord;
					int yCoord;
					int turns;
					String status;
					
					for (int k = 0; k < array.size(); k += 1) {
						JSONObject object = (JSONObject) array.get(k);
						number = (JSONNumber) object.get("code");
						code = (int) number.doubleValue();
						
						string = (JSONString) object.get("status");
						status = string.stringValue();
						
						number = (JSONNumber) object.get("turns");
						turns = (int) number.doubleValue();
						
						number = (JSONNumber) object.get("X");
						xCoord = (int) number.doubleValue();
						
						number = (JSONNumber) object.get("Y");
						yCoord = (int) number.doubleValue();
						
						this.addModule(new Module(code, xCoord, yCoord, turns, status));
					}
				}
			}
		}
	}
	
	/**
	 * Stores a Module from the ModuleList to the HTML5 local storage.
	 * @param Module the Module to be saved.
	 */
	private void saveModule(final Module module) {
		Storage store = null;
		store = Storage.getLocalStorageIfSupported();
		
		final String value = "[{code:" + Integer.toString(module.getCode()) +
							 ",status:\"" + module.getStatus() + "\",turns:" +
							 Integer.toString(module.getTurns()) + ",X:" +
							 Integer.toString(module.getXCoord()) + ",Y:" +
							 Integer.toString(module.getYCoord()) + "}]";
		final String key = KEY + Integer.toString(module.getCode());
		
		if (store != null) {
			store.setItem(key, value);
		}
	}
	
	/**
	 * Clears the module list and local storage.
	 */
	public void clearList() {
		
		for (int i = 1; i < SIZE; i += 1) {
			this.deleteModule(i);
		}
		
		this.airlock = 0;
		this.canteen = 0;
		this.control = 0;
		this.dormitory = 0;
		this.gym = 0;
		this.medical = 0;
		this.plain = 0;
		this.power = 0;
		this.sanitation = 0;
		
		Storage store = Storage.getLocalStorageIfSupported();
		
		if (store != null) {
			store.clear();
		}
		
	}
	
	/**
	 * Fills the module list with every module. For testing the configuration map.
	 */
	public void populateList() {
		this.clearList();
		for (int i = 1; i <= 40; i++) {
			addModule(new Module(i, i, 1, 0, Module.UNDAMAGED));
		}
		for (int i = 1; i <= 20; i++) {
			addModule(new Module(60 + i, i, 2, 0, Module.UNDAMAGED));
		}
		for (int i = 1; i <= 10; i++) {
			addModule(new Module(90 + i, i, 3, 0, Module.UNDAMAGED));
		}
		for (int i = 1; i <= 10; i++) {
			addModule(new Module(110 + i, i, 4, 0, Module.UNDAMAGED));
		}
		for (int i = 1; i <= 4; i++) {
			addModule(new Module(130 + i, i, 5, 0, Module.UNDAMAGED));
		}
		for (int i = 1; i <= 4; i++) {
			addModule(new Module(140 + i, i, 6, 0, Module.UNDAMAGED));
		}
		for (int i = 1; i <= 4; i++) {
			addModule(new Module(150 + i, i, 7, 0, Module.UNDAMAGED));
		}
		for (int i = 1; i <= 4; i++) {
			addModule(new Module(160 + i, i, 8, 0, Module.UNDAMAGED));
		}
		for (int i = 1; i <= 4; i++) {
			addModule(new Module(170 + i, i, 9, 0, Module.UNDAMAGED));
		}
		for (int i = 1; i <= 4; i++) {
			addModule(new Module(180 + i, i, 10, 0, Module.UNDAMAGED));
		}
		
	}
} 
