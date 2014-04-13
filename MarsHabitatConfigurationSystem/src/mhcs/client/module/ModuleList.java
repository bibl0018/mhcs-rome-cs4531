package mhcs.client.module;

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
			throw new IndexOutOfBoundsException("Invalid code parameter for ModuleList::getModule()");
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
			throw new IllegalArgumentException("Module already exists in ModuleList during ModuleList::addModule()");
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
			throw new IndexOutOfBoundsException("Invalid code parameter for ModuleList::deleteModule()");
		} else if (modules[code] == null) {
			throw new IllegalArgumentException("Module does not exists in ModuleList during ModuleList::deleteModule()");
		}
		
		modules[code] = null;
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
