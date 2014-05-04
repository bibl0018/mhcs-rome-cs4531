package mhcs.client.moduleConfigurations;

/**
 * A class that stores coordinates of each module type in a configuration.
 * @author Jeremiah Wilhelmy
 */

import java.util.ArrayList;

import mhcs.client.module.ModuleList;
import mhcs.client.module.Module.Type;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.storage.client.Storage;


public class ModuleConfiguration{
	
	// Instance fields for the calculateConfiguration method.
		public static final int ROW_SIZE = 50;
		public static final int COLUMN_SIZE = 100;
		public static final int ONE = 1;
		public static final int TWO = 2;
		public static final int THREE = 3;
		public static final int FOUR = 4;
		public static final int FIVE = 5;
		public static final int SIX = 6;
		public static final int SEVEN = 7;
		public static final int EIGHT = 8;
		public static final int NINE = 9;
		public static final int TEN = 10;
		public static final int ELEVEN = 11;
		public static final int TWELVE = 12;
		public static final int THIRTEEN = 13;
		public static final int FOURTEEN = 14;
		public static final int FIFTEEN = 15;
		public static final int SIXTEEN = 16;
		public static final int SEVENTEEN = 17;
		public static final int EIGHTEEN = 18;
		public static final int NINETEEN = 19;
		public static final int TWENTYTWO = 22;
		public static final int TWENTYTHREE = 23;
		public static final int TWENTYFOUR = 24;
		public static final int TWENTYFIVE = 25;
		
		
	// Instance fields for the save and load methods.
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
	public boolean saveConfiguration(final String name) {
		Storage store = null;
		store = Storage.getLocalStorageIfSupported();
		boolean valid;
		
		// Check that the configuration meets minimum requirements.
		if (this.airlockModules.size() < 1 || this.controlModules.size() < 1 || this.powerModules.size() < 1 || 
				this.foodAndWaterModules.size() < 1 || this.dormitoryModules.size() < 1 || this.canteenModules.size() < 1 ||
				this.sanitationModules.size() < 1 || this.plainModules.size() < THREE) {
				return false;
		}
		
		
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
	    value = value.substring(0, value.length() - 1);
	    value = value + "]";
	    
		if (store != null) {
			store.setItem(key, value);
			valid = true;
		} else {
			valid = false;
		}
		
		return valid;
	}
	
	/**
	 * Loads a configuration from local storage into this ModuleConfiguration object.
	 * @param name The name of the configuration to load.
	 * @return true if the configuration was loaded, else false
	 */
	public boolean loadConfiguration(final String name) {
		Storage store = null;
		store = Storage.getLocalStorageIfSupported();
		boolean loaded;
		
		// If storage was not found.
		if (store == null) {
			return false;
		}
		
		String key = KEY + name;
		String value = store.getItem(key);
		
		// If the configuration was not found in storage.
		if (value == null) {
			loaded = false;
		} else {
			JSONArray array = (JSONArray) JSONParser.parseLenient(value);
			JSONNumber number;
			JSONString string;
			String type;
			int xCoord;
			int yCoord;
			
			for (int k = 0; k < array.size(); k += 1) {
				JSONObject object = (JSONObject) array.get(k);
				string = (JSONString) object.get("type");
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
			loaded = true;
		}
		return loaded;
	}
	
	/**
	 * Function for constructing a configuration given a module list. If successful,
	 * the object that called this method will contain the full configuration.
	 * @param modules The module list
	 * @param centerRow The middle piece of the configuration will reside in this row.
	 * @param centerColumn The middle piece of the configuration will reside in this column.
	 * @return true if the configuration was calculated 
	 */
	public boolean calculateConfiguration(final ModuleList modules, final int centerColumn, final int centerRow) {
		
		Type[][] config = new Type[COLUMN_SIZE][ROW_SIZE];
		int plain = modules.getNumOfPlain();
		int airlock = modules.getNumOfAirlock();
		int power = modules.getNumOfPower();
		int water = modules.getNumOfWater();
		int sanitation = modules.getNumOfSanitation();
		int canteen = modules.getNumOfCanteen();
		int dormitory = modules.getNumOfDormitory();
		int gym = modules.getNumOfGym();
		int medical = modules.getNumOfMedical();
		int control = modules.getNumOfControl();
		int eastLimit = centerColumn + 2;
		int westLimit = centerColumn - 2;
		int northTip = 0;
		int southTip = 0;
		int eastTip = 0;
		int westTip = 0;
		boolean value = false;
		
		
		/*
		 * Places all modules in this formation:
		 * 
		 *            A
		 *           MPC
		 *           WPS
		 *           DPG
		 *           DPS
		 *           DPD
		 *           DPS
		 *           DPS
		 *           DPD
		 *  CNFNFDS   P   GDFFFWM
		 * APPPPPPPPPPPPPPPPPPPPPA
		 *  MWFFFDG   P   SDFNFNC
		 *            P
		 *           DPD
		 *           SPD
		 *           SPD
		 *           DPD
		 *           SPD
		 *           GPD
		 *           SPW
		 *           CPM
		 *            A
		 */
		
		try { 
			// Places the first plain module in the center of the map.
			if (plain > 0) {
				config[centerColumn][centerRow] = Type.PLAIN;
				plain -= 1;
			}
			
			// Places the remaining plain modules in four wings in a cross formation.
			for (int wings = FOUR; wings > 0; wings -= 1) {
				int i = 0;
				if (wings == FOUR) {
					for (i = 0; i < (plain/wings); i += 1) {
						config[centerColumn][centerRow + i + 1] = Type.PLAIN;
					}
					northTip = centerRow + i;
				} else if (wings == THREE) {
					for (i = 0; i < (plain/wings); i += 1) {
						config[centerColumn + i + 1][centerRow] = Type.PLAIN;
					}
					eastTip = centerColumn + i;
				} else if (wings == TWO) {
					for (i = 0; i < (plain/wings); i += 1) {
						config[centerColumn][centerRow - i - 1] = Type.PLAIN;
						}
					southTip = centerRow - i;
				} else if (wings == ONE) {
					for (i = 0; i < (plain/wings); i += 1) {
						config[centerColumn - i - 1][centerRow] = Type.PLAIN;
					}
					westTip = centerColumn - i;
				}
				plain -= i;
			}
			
			// Place airlocks in each wing.
			for (int i = 0; i < airlock; i += 1) {
				if (i == 0) {
					config[centerColumn][westTip - 1] = Type.AIRLOCK;
				} else if (i == 1){
					config[centerColumn][eastTip + 1] = Type.AIRLOCK;
				} else if (i == TWO){
					config[southTip - 1][centerRow] = Type.AIRLOCK;
				} else if (i == THREE) {
					config[northTip + 1][centerRow] = Type.AIRLOCK;
				}
			}
			
			// Place medical modules by the airlocks.
			for (int i = 0; i < medical; i += 1) {
				if (i == 0) {
					config[northTip][centerRow - 1] = Type.MEDICAL;
				} else if (i == 1 && eastTip > eastLimit){
					config[centerColumn + 1][eastTip] = Type.MEDICAL;
				} else if (i == TWO){
					config[southTip][centerRow + 1] = Type.MEDICAL;
				} else if (i == THREE && westTip < westLimit) {
					config[centerColumn - 1][westTip] = Type.MEDICAL;
				}
			}
			
			// Place control modules by the airlocks and across from medical.
			for (int i = 0; i < control; i += 1) {
				if (i == 0) {
					config[northTip][centerRow + 1] = Type.CONTROL;
				} else if (i == 1 && eastTip > eastLimit){
					config[centerColumn - 1][eastTip] = Type.CONTROL;
				} else if (i == TWO){
					config[southTip][centerRow - 1] = Type.CONTROL;
				} else if (i == THREE && westTip < westLimit) {
					config[centerColumn + 1][westTip] = Type.CONTROL;
				}
			}
			
			// Place power modules in each wing next to medical.
			for (int i = 0; i < power; i += 1) {
				if (i == 0 && (northTip - 1) > centerRow) {
					config[northTip - 1][centerRow - 1] = Type.POWER;
				} else if (i == 1 && (eastTip - 1) > eastLimit){
					config[centerColumn + 1][eastTip - 1] = Type.POWER;
				} else if (i == TWO && (southTip + 1) < centerRow){
					config[southTip + 1][centerRow + 1] = Type.POWER;
				} else if (i == THREE && (westTip + 1) > westLimit) {
					config[centerColumn + 1][westTip + 1] = Type.POWER;
				}
			}
			
			// Place canteen modules in east and west wings.
			for (int i = 0; i < canteen; i += 1) {
				if (i == 0 && (eastTip - 1) > eastLimit) {
					config[centerColumn - 1][eastTip - 1] = Type.CANTEEN;
				} else if (i == 1 && (westTip + 1) < westLimit){
					config[centerColumn + 1][westTip + 1] = Type.CANTEEN;
				} else if (i == TWO && (eastTip - THREE) > eastLimit){
					config[centerColumn - 1][eastTip - THREE] = Type.CANTEEN;
				} else if (i == THREE && (westTip + THREE) < westLimit) {
					config[centerColumn + 1][westTip + THREE] = Type.CANTEEN;
				}
			}
			
			// Place food and water modules in east and west wings
			for (int i = 0; i < water; i += 1) {
				if (i == 0 && (westTip + TWO) < westLimit) {
					config[centerColumn + 1][westTip + TWO] = Type.FOOD_WATER;
				} else if (i == 1 && (eastTip - TWO) > eastLimit){
					config[centerColumn + 1][eastTip - TWO] = Type.FOOD_WATER;
				} else if (i == TWO && (westTip + TWO) < westLimit){
					config[centerColumn - 1][westTip + TWO] = Type.FOOD_WATER;
				} else if (i == THREE && (eastTip - TWO) > eastLimit) {
					config[centerColumn - 1][eastTip - TWO] = Type.FOOD_WATER;
				} else if (i == FOUR && (westTip + THREE) < westLimit) {
					config[centerColumn - 1][westTip + THREE] = Type.FOOD_WATER;
				} else if (i == FIVE && (eastTip - THREE) > eastLimit){
					config[centerColumn + 1][eastTip - THREE] = Type.FOOD_WATER;
				} else if (i == SIX && (westTip + FOUR) < westLimit){
					config[centerColumn - 1][westTip + FOUR] = Type.FOOD_WATER;
				} else if (i == SEVEN && (eastTip - FOUR) > eastLimit) {
					config[centerColumn - 1][eastTip - FOUR] = Type.FOOD_WATER;
				} else if (i == EIGHT && (westTip + FOUR) < westLimit) {
					config[centerColumn + 1][westTip + FOUR] = Type.FOOD_WATER;
				} else if (i == NINE && (eastTip - FOUR) > eastLimit){
					config[centerColumn + 1][eastTip - FOUR] = Type.FOOD_WATER;
				}
			}
			
			// Place gym modules in each wing
			for (int i = 0; i < gym; i += 1) {
				if (i == 0 && (northTip - 2) > centerRow) {
					config[northTip - 2][centerRow + 1] = Type.GYM_RELAXATION;
				} else if (i == 1 && (eastTip - SIX) > eastLimit){
					config[centerColumn + 1][eastTip - SIX] = Type.GYM_RELAXATION;
				} else if (i == TWO && (southTip + 2) < centerRow){
					config[southTip + 2][centerRow - 1] = Type.GYM_RELAXATION;
				} else if (i == THREE && (westTip + SIX) < westLimit) {
					config[centerColumn + 1][westTip + SIX] = Type.GYM_RELAXATION;
				}
			}
			
			// Place sanitation modules. 1 in east and west, 4 in north and south
			for (int i = 0; i < sanitation; i += 1) {
				if (i == 0 && (northTip - 1) > centerRow) {
					config[northTip - 1][centerRow + 1] = Type.SANITATION;
				} else if (i == 1 && (southTip + 1) < centerRow){
					config[southTip + 1][centerRow - 1] = Type.SANITATION;
				} else if (i == TWO && (northTip - THREE) > centerRow){
					config[northTip - THREE][centerRow + 1] = Type.SANITATION;
				} else if (i == THREE && (southTip + THREE) < centerRow) {
					config[southTip + THREE][centerRow - 1] = Type.SANITATION;
				} else if (i == FOUR && (northTip - FIVE) > centerRow){
					config[northTip - FIVE][centerRow - 1] = Type.SANITATION;
				} else if (i == FIVE && (southTip + FIVE) < centerRow) {
					config[southTip + FIVE][centerRow + 1] = Type.SANITATION;
				} else if (i == SIX && (westTip + SIX) < westLimit){
					config[centerColumn - 1][westTip + SIX] = Type.SANITATION;
				} else if (i == SEVEN && (eastTip - SIX) > eastLimit) {
					config[centerColumn - 1][eastTip - SIX] = Type.SANITATION;
				} else if (i == EIGHT && (northTip - SIX) > centerRow){
					config[northTip - SIX][centerRow + 1] = Type.SANITATION;
				} else if (i == NINE && (southTip + SIX) < centerRow) {
					config[southTip + SIX][centerRow - 1] = Type.SANITATION;
				}
			}
			
			// Place dormitory modules. 2 in east and west, 8 in north and south.
			for (int i = 0; i < dormitory; i += 1) {
				if (i == 0 && (northTip - TWO) > centerRow) {
					config[northTip - TWO][centerRow - 1] = Type.DORMITORY;
				} else if (i == 1 && (southTip + TWO) < centerRow){
					config[southTip + TWO][centerRow + 1] = Type.DORMITORY;
				} else if (i == TWO && (northTip - THREE) > centerRow){
					config[northTip - THREE][centerRow - 1] = Type.DORMITORY;
				} else if (i == THREE && (southTip + THREE) < centerRow) {
					config[southTip + THREE][centerRow + 1] = Type.DORMITORY;
				} else if (i == FOUR && (northTip - FOUR) > centerRow){
					config[northTip - FOUR][centerRow + 1] = Type.DORMITORY;
				} else if (i == FIVE && (southTip + FOUR) < centerRow) {
					config[southTip + FOUR][centerRow - 1] = Type.DORMITORY;
				} else if (i == SIX && (northTip - FOUR) > centerRow){
					config[northTip - FOUR][centerRow - 1] = Type.DORMITORY;
				} else if (i == SEVEN && (southTip + FOUR) < centerRow) {
					config[southTip + FOUR][centerRow + 1] = Type.DORMITORY;
				} else if (i == EIGHT && (westTip + FIVE) < westLimit){
					config[centerColumn - 1][westTip + FIVE] = Type.DORMITORY;
				} else if (i == NINE && (eastTip - FIVE) > eastLimit) {
					config[centerColumn + 1][eastTip - FIVE] = Type.DORMITORY;
				} else if (i == TEN && (westTip + FIVE) < westLimit){
					config[centerColumn + 1][westTip + FIVE] = Type.DORMITORY;
				} else if (i == ELEVEN && (eastTip - FIVE) > eastLimit) {
					config[centerColumn - 1][eastTip - FIVE] = Type.DORMITORY;
				} else if (i == TWELVE && (northTip - FIVE) > centerRow){
					config[northTip - FIVE][centerRow + 1] = Type.DORMITORY;
				} else if (i == THIRTEEN && (southTip + FIVE) < centerRow) {
					config[southTip + FIVE][centerRow - 1] = Type.DORMITORY;
				} else if (i == FOURTEEN && (northTip - SIX) > centerRow){
					config[northTip - SIX][centerRow - 1] = Type.DORMITORY;
				} else if (i == FIFTEEN && (southTip + SIX) < centerRow) {
					config[southTip + SIX][centerRow + 1] = Type.DORMITORY;
				} else if (i == SIXTEEN && (northTip - SEVEN) > centerRow){
					config[northTip - SEVEN][centerRow - 1] = Type.DORMITORY;
				} else if (i == SEVENTEEN && (southTip + SEVEN) < centerRow) {
					config[southTip + SEVEN][centerRow + 1] = Type.DORMITORY;
				} else if (i == EIGHTEEN && (northTip - SEVEN) > centerRow){
					config[northTip - SEVEN][centerRow + 1] = Type.DORMITORY;
				} else if (i == NINETEEN && (southTip + SEVEN) < centerRow) {
					config[southTip + SEVEN][centerRow - 1] = Type.DORMITORY;
				}
			}
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		
		
		// Places each coord in this configuration.
		for (int i = 0; i < COLUMN_SIZE; i += 1) {
			for (int k = 0; k < ROW_SIZE; k += 1) {
				Type type = config[i][k];
				
				if (type != null) {
					if (type.equals(Type.AIRLOCK)) {
						this.addAirlock(i, k);
					} else if (type.equals(Type.CANTEEN)) {
						this.addCanteen(i, k);
					} else if (type.equals(Type.CONTROL)) {
						this.addControl(i, k);
					} else if (type.equals(Type.DORMITORY)) {
						this.addDormitory(i, k);
					} else if (type.equals(Type.FOOD_WATER)) {
						this.addFoodAndWater(i, k);
					} else if (type.equals(Type.GYM_RELAXATION)) {
						this.addGymAndRelaxation(i, k);
					} else if (type.equals(Type.MEDICAL)) {
						this.addMedical(i, k);
					} else if (type.equals(Type.PLAIN)) {
						this.addPlain(i, k);
					} else if (type.equals(Type.POWER)) {
						this.addPower(i, k);
					} else if (type.equals(Type.SANITATION)) {
						this.addSanitation(i, k);
					}
				}
			}
		}
		
		// Check that the generated configuration has the minimum number of required modules.
		// 1 Airlock, 1 Control, 1 Power, 1 Food & water storage, 1 Dormitory, 1 Canteen, 1 Sanitation, and 3 Plain.
		if (this.airlockModules.size() < 1 || this.controlModules.size() < 1 || this.powerModules.size() < 1 || 
			this.foodAndWaterModules.size() < 1 || this.dormitoryModules.size() < 1 || this.canteenModules.size() < 1 ||
			this.sanitationModules.size() < 1 || this.plainModules.size() < THREE) {
			value = false;
		} else {
			value = true;
		}
		
		return value;
	}
	
	/**
	 * Sets the values of this ModuleConfiguration to match that of the first minimum configuration.
	 */
	public boolean setMinimumConfigOne(final int centerColumn, final int centerRow) {
		this.clearConfig();
		
		Type[][] config = new Type[COLUMN_SIZE][ROW_SIZE];
		
		try {
			config[centerColumn][centerRow] = Type.PLAIN;
			config[centerColumn - 1][centerRow] = Type.PLAIN;
			config[centerColumn + 1][centerRow] = Type.PLAIN;
			config[centerColumn - 2][centerRow] = Type.AIRLOCK;
			config[centerColumn][centerRow + 1] = Type.CANTEEN;
			config[centerColumn - 1][centerRow + 1] = Type.CONTROL;
			config[centerColumn - 1][centerRow - 1] = Type.SANITATION;
			config[centerColumn][centerRow - 1] = Type.POWER;
			config[centerColumn + 1][centerRow + 1] = Type.FOOD_WATER;
			config[centerColumn + 1][centerRow - 1] = Type.DORMITORY;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		
		// Places each coord in this configuration.
		for (int i = 0; i < COLUMN_SIZE; i += 1) {
			for (int k = 0; k < ROW_SIZE; k += 1) {
				Type type = config[i][k];
				
				if (type != null) {
					if (type.equals(Type.AIRLOCK)) {
						this.addAirlock(i, k);
					} else if (type.equals(Type.CANTEEN)) {
						this.addCanteen(i, k);
					} else if (type.equals(Type.CONTROL)) {
						this.addControl(i, k);
					} else if (type.equals(Type.DORMITORY)) {
						this.addDormitory(i, k);
					} else if (type.equals(Type.FOOD_WATER)) {
						this.addFoodAndWater(i, k);
					} else if (type.equals(Type.GYM_RELAXATION)) {
						this.addGymAndRelaxation(i, k);
					} else if (type.equals(Type.MEDICAL)) {
						this.addMedical(i, k);
					} else if (type.equals(Type.PLAIN)) {
						this.addPlain(i, k);
					} else if (type.equals(Type.POWER)) {
						this.addPower(i, k);
					} else if (type.equals(Type.SANITATION)) {
						this.addSanitation(i, k);
					}
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Sets the values of this ModuleConfiguration to match that of the second minimum configuration.
	 */
	public boolean setMinimumConfigTwo(final int centerColumn, final int centerRow) {
		this.clearConfig();
		
		Type[][] config = new Type[COLUMN_SIZE][ROW_SIZE];
		
		try {
			config[centerColumn][centerRow] = Type.PLAIN;
			config[centerColumn][centerRow - 1] = Type.PLAIN;
			config[centerColumn + 1][centerRow] = Type.PLAIN;
			config[centerColumn][centerRow - 2] = Type.AIRLOCK;
			config[centerColumn][centerRow + 1] = Type.CANTEEN;
			config[centerColumn - 1][centerRow] = Type.CONTROL;
			config[centerColumn - 1][centerRow - 1] = Type.SANITATION;
			config[centerColumn + 1][centerRow - 1] = Type.POWER;
			config[centerColumn + 1][centerRow + 1] = Type.FOOD_WATER;
			config[centerColumn + 2][centerRow] = Type.DORMITORY;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
		
		// Places each coord in this configuration.
		for (int i = 0; i < COLUMN_SIZE; i += 1) {
			for (int k = 0; k < ROW_SIZE; k += 1) {
				Type type = config[i][k];
				
				if (type != null) {
					if (type.equals(Type.AIRLOCK)) {
						this.addAirlock(i, k);
					} else if (type.equals(Type.CANTEEN)) {
						this.addCanteen(i, k);
					} else if (type.equals(Type.CONTROL)) {
						this.addControl(i, k);
					} else if (type.equals(Type.DORMITORY)) {
						this.addDormitory(i, k);
					} else if (type.equals(Type.FOOD_WATER)) {
						this.addFoodAndWater(i, k);
					} else if (type.equals(Type.GYM_RELAXATION)) {
						this.addGymAndRelaxation(i, k);
					} else if (type.equals(Type.MEDICAL)) {
						this.addMedical(i, k);
					} else if (type.equals(Type.PLAIN)) {
						this.addPlain(i, k);
					} else if (type.equals(Type.POWER)) {
						this.addPower(i, k);
					} else if (type.equals(Type.SANITATION)) {
						this.addSanitation(i, k);
					}
				}
			}
		}
		
		return true;
	}
	
	
	/**
	 * Clears all lists in this configuration.
	 */
	public void clearConfig() {
		// Clear all lists.
		this.airlockModules.clear();
		this.canteenModules.clear();
		this.controlModules.clear();
		this.dormitoryModules.clear();
		this.foodAndWaterModules.clear();
		this.gymAndRelaxationModules.clear();
		this.medicalModules.clear();
		this.plainModules.clear();
		this.powerModules.clear();
		this.sanitationModules.clear();
	}
	
}

