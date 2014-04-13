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

	/**
	 * Default constructor. Initializes each Module in list to null.
	 */
	public ModuleList() {
		modules = new Module[SIZE];
		
		for (int i = 0; i < SIZE; i++) {
			modules[i] = null;
		}
		loadModules();
	}
	
	/**
	 * Getter for a Module in the ModuleList.
	 * @param code The Module to get.  1-190
	 * @return The Module at index code, null if the Module is not found
	 * @throws IndexOutOfBoundsException if code is not between 1-190
	 */
	public Module getModule(int code) throws IndexOutOfBoundsException {
		if (code < 1 || code > 190) {
			throw new IndexOutOfBoundsException("Invalid code parameter");
		}
		
		return modules[code];
	}
	
	/**
	 * Add a Module to the ModuleList.
	 * @param module The Module to be added.
	 * @throws IllegalArgumentException if a Module with the same code already exists in the list
	 */
	public void addModule(Module module) throws IllegalArgumentException {
		if (modules[module.getCode()] != null) {
			throw new IllegalArgumentException("Module already exists");
		}
		
		modules[module.getCode()] = module;
		saveModule(module);
	}
	
	/**
	 * Deletes the Module with number "code" in ModuleList.
	 * @param code The Module to delete.
	 * @throws IllegalArgumentException if there is no Module with code number "code"
	 * @throws IndexOutOfBoundsException if code is not between 1-190
	 */
	public void deleteModule(int code) throws IllegalArgumentException, IndexOutOfBoundsException {
		if (code < 1 || code > 190) {
			throw new IndexOutOfBoundsException("Invalid code parameter");
		} else if (modules[code] == null) {
			throw new IllegalArgumentException("Module does not exists");
		}
		
		modules[code] = null;
		
		Storage store = Storage.getLocalStorageIfSupported();
		String key = "MHCS.Module." + Integer.toString(code);
		
		if (store != null) {
			store.removeItem(key);
		}
	}
	
	/**
	 * Loads all Modules from HTML5 local storage into this module list.
	 */
	private void loadModules() {
		Storage store = null;
		store = Storage.getLocalStorageIfSupported();
		
		if (store != null) {
			for (int i = 1; i <= 190; i++) {
				String key = "MHCS.Module." + Integer.toString(i);
				String value = store.getItem(key);
				
				if (value != null); {
					JSONArray array = (JSONArray) JSONParser.parseLenient(value);
					JSONNumber number;
					JSONString string;
					int code, xCoord, yCoord, turns;
					String status;
					
					for (int k = 0; k < array.size(); k++) {
						JSONObject object = (JSONObject) array.get(i);
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
						
						addModule(new Module(code, xCoord, yCoord, turns, status));
					}
				}
			}
		}
	}
	
	/**
	 * Stores a Module from the ModuleList to the HTML5 local storage.
	 * @param Module the Module to be saved.
	 */
	private void saveModule(Module module) {
		Storage store = null;
		store = Storage.getLocalStorageIfSupported();
		
		final String value = "[{code:" + Integer.toString(module.getCode()) +
							 ",status:\"" + module.getStatus() + "\",turns:" +
							 Integer.toString(module.getTurns()) + ",X:" +
							 Integer.toString(module.getXCoord()) + ",Y:" +
							 Integer.toString(module.getYCoord()) + "}]";
		final String key = "MHCS.Module." + Integer.toString(module.getCode());
		
		if (store != null) {
			store.setItem(key, value);
		}
	}
	
	private Module[] modules;
	private static int SIZE = 190;
}
