package mhcs.client.moduleConfigurations;

/**
 * A class that stores coordinates of each module type in a configuration.
 * @author Jeremiah Wilhelmy
 */

import java.util.ArrayList;


public class ModuleConfiguration{
	
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
		plainModules = new ArrayList();
		dormitoryModules = new ArrayList();
		sanitationModules = new ArrayList();
		controlModules = new ArrayList();
		foodAndWaterModules = new ArrayList();
		gymAndRelaxationModules = new ArrayList();
		canteenModules = new ArrayList();
		powerModules = new ArrayList();
		airlockModules = new ArrayList();
		medicalModules = new ArrayList();
	}
	
	/**
	 * Adds a set of coordinates to the corresponding list of modules.
	 * @param x The X coordinate.
	 * @param y The Y coordinate.
	 */
	public void addPlain(int x, int y){
		Coordinates coord = new Coordinates(x,y);
		this.plainModules.add(coord);
	}
	
	public void addDormitory(int x, int y){
		Coordinates coord = new Coordinates(x,y);
		this.dormitoryModules.add(coord);
	}
	
	public void addSanitation(int x, int y){
		Coordinates coord = new Coordinates(x,y);
		this.sanitationModules.add(coord);
	}
	
	public void addControl(int x, int y){
		Coordinates coord = new Coordinates(x,y);
		this.controlModules.add(coord);
	}
	
	public void addFoodAndWater(int x, int y){
		Coordinates coord = new Coordinates(x,y);
		this.foodAndWaterModules.add(coord);
	}
	
	public void addGymAndRelaxation(int x, int y){
		Coordinates coord = new Coordinates(x,y);
		this.gymAndRelaxationModules.add(coord);
	}
	
	public void addCanteen(int x, int y){
		Coordinates coord = new Coordinates(x,y);
		this.canteenModules.add(coord);
	}
	
	public void addPower(int x, int y){
		Coordinates coord = new Coordinates(x,y);
		this.powerModules.add(coord);
	}
	
	public void addAirlock(int x, int y){
		Coordinates coord = new Coordinates(x,y);
		this.airlockModules.add(coord);
	}
	
	public void addMedical(int x, int y){
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
		
		public Coordinates(int x, int y){
			xCoord = x;
			yCoord = y;
		}
	}	
}
