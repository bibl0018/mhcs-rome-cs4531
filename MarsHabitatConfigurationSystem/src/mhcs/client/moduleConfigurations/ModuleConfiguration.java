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
	public static final int UPPER_BOUNDS = 50;
	public static final int LOWER_BOUNDS = 40;
	public static final int DEFAULT_ROW = 20;
	public static final int DEFAULT_COLUMN = 20;
	
	// Annoying magic numbers
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
	private static int centerRow = TWENTYTWO;
	private static int centerColumn = TWENTYTWO;
	
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
	
	private int plain;
	private int airlock;
	private int power;
	private int water;
	private int sanitation;
	private int canteen;
	private int dormitory;
	private int gym;
	private int medical;
	private int control;
	private int northLength;
	private int southLength;
	private int eastLength;
	private int westLength;
	
	

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
	public boolean calculateConfiguration(final ModuleList modules, final int xCoord, final int yCoord) {
		this.clearConfig();
		
		this.plain = modules.getNumOfPlain();
		this.airlock = modules.getNumOfAirlock();
		this.power = modules.getNumOfPower();
		this.water = modules.getNumOfWater();
		this.sanitation = modules.getNumOfSanitation();
		this.canteen = modules.getNumOfCanteen();
		this.dormitory = modules.getNumOfDormitory();
		this.gym = modules.getNumOfGym();
		this.medical = modules.getNumOfMedical();
		this.control = modules.getNumOfControl();
		this.northLength = 0;
		this.southLength = 0;
		this.eastLength = 0;
		this.westLength = 0;
		boolean value = true;
		
		
		// Places the first plain module in the center of the map.
		if (this.plain > 0) {
			placeModule(Type.PLAIN, xCoord, yCoord);
			this.plain -= 1;
		}
		
		// Places the remaining plain modules in four wings in a cross formation.
		for (int wings = FOUR; wings > 0; wings -= 1) {
			int i = 0;
			if (wings == FOUR) {
				for (i = 0; i < (this.plain/wings); i += 1) {
					placeModule(Type.PLAIN, xCoord, yCoord + i + 1);
				}
				this.northLength = i;
			} else if (wings == THREE) {
				for (i = 0; i < (this.plain/wings); i += 1) {
					placeModule(Type.PLAIN, xCoord, yCoord - i - 1);
					}
				this.southLength = i;
			} else if (wings == TWO) {
				for (i = 0; i < (this.plain/wings); i += 1) {
					placeModule(Type.PLAIN, xCoord + i + 1, yCoord);
				}
				this.eastLength = i;
			} else if (wings == ONE) {
				for (i = 0; i < (this.plain/wings); i += 1) {
					placeModule(Type.PLAIN, xCoord - i - 1, yCoord);
				}
				this.westLength = i;
			}
			this.plain -= i;
		}
		
		// Lists of modules for each wing.
		Type[] eastWing = new Type[this.eastLength * 2 + 1];
		Type[] westWing = new Type[this.westLength * 2 + 1];
		Type[] northWing;
		Type[] southWing;
		
		if (this.northLength > 2) {
			northWing = new Type[(this.northLength - 2) * 2 + 1];
		} else {
			northWing = new Type[1];
		}
		
		if (this.southLength > 2) {
			southWing = new Type[(this.southLength - 2) * 2 + 1];
		} else {
			southWing = new Type[1];
		}
		
		// Place initial minimum configuration modules, then generate the rest.
		westWing[0] = Type.SANITATION;
		westWing[westWing.length / 2] = Type.AIRLOCK;
		westWing[westWing.length - 1] = Type.CONTROL;
		this.sanitation -= 1;
		this.airlock -= 1;
		this.control -= 1;
		
		eastWing[0] = Type.CANTEEN;
		eastWing[eastWing.length / 2] = Type.DORMITORY;
		eastWing[eastWing.length - 1] = Type.FOOD_WATER;
		this.canteen -= 1;
		this.dormitory -= 1;
		this.water -= 1;
		
		southWing[southWing.length / 2] = Type.POWER;
		this.power -= 1;
		
		westWing = this.generateWing(westWing, 0);
		eastWing = this.generateWing(eastWing, 0);
		if (this.southLength > 2) {
			southWing = this.generateWing(southWing, 1);
		}
		northWing = this.generateWing(northWing, 1);
		
		
		// Places modules from each wing list.
		for (int i = 1; i <= this.westLength; i += 1) {
			Type type1 = westWing[i - 1];
			Type type2 = westWing[westWing.length - i];
			
			placeModule(type1, xCoord - i, yCoord - 1);
			placeModule(type2, xCoord - i, yCoord + 1);
		}
		placeModule(westWing[westWing.length / 2], xCoord - this.westLength - 1, yCoord);
		
		for (int i = 1; i <= this.eastLength; i += 1) {
			Type type1 = eastWing[i - 1];
			Type type2 = eastWing[eastWing.length - i];
			
			placeModule(type1, xCoord + i, yCoord - 1);
			placeModule(type2, xCoord + i, yCoord + 1);
		}
		placeModule(eastWing[eastWing.length / 2], xCoord + this.eastLength + 1, yCoord);
		
		if (this.northLength > 2) {
			for (int i = 1; i <= this.northLength - 2; i += 1) {
				Type type1 = northWing[i - 1];
				Type type2 = northWing[northWing.length - i];
				
				placeModule(type1, xCoord - 1, yCoord + i + 2);
				placeModule(type2, xCoord + 1, yCoord + i + 2);
			}
			placeModule(northWing[northWing.length / 2], xCoord, yCoord + this.northLength + 1);
		} else {
			placeModule(northWing[0], xCoord, yCoord + this.northLength + 1);
		}
		
		if (this.southLength > 2) {
			for (int i = 1; i <= this.southLength - 2; i += 1) {
				Type type1 = southWing[i - 1];
				Type type2 = southWing[southWing.length - i];
				
				placeModule(type1, xCoord - 1, yCoord - i - 2);
				placeModule(type2, xCoord + 1, yCoord - i - 2);
			}
			placeModule(southWing[southWing.length / 2], xCoord, yCoord - this.southLength - 1);
		} else {
			placeModule(southWing[0], xCoord, yCoord + this.southLength + 1);
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
		
		if (!checkBounds()) {
			value = false;
		}
		
		centerRow = yCoord;
		centerColumn = xCoord;
		
		return value;
	}
	
	
	/**
	 * Generates a wing and adheres to most layout rules
	 * @param wing The wing to be added to
	 * @param direction North/South = 1; East/West = 0;
	 * @return The full wing.
	 */
	private Type[] generateWing(final Type[] wing, final int direction) {
		int dorm = 0;
		int san = 0;
		int air = 0;
		int pow = 0;
		int cont = 0;
		int med = 0;
		int food = 0;
		int can = 0;
		int dormLimit = wing.length / 2;
		
		if (dormLimit % 2 == 1) {
			dormLimit += 1;
		}
		
		for (int i = 0; i < wing.length; i += 1) {
			if (Type.AIRLOCK.equals(wing[i])) {
				air += 1;
			} else if (Type.CONTROL.equals(wing[i])) {
				cont += 1;
			} else if (Type.DORMITORY.equals(wing[i])) {
				dorm += 1;
			} else if (Type.FOOD_WATER.equals(wing[i])) {
				food += 1;
			} else if (Type.MEDICAL.equals(wing[i])) {
				med += 1;
			} else if (Type.POWER.equals(wing[i])) {
				pow += 1;
			} else if (Type.SANITATION.equals(wing[i])) {
				san += 1;
			}
		}
		
		
		for (int i = 1 - direction; i < wing.length - 1 + direction; i += 1) {
			Type before = wing[i - 1];
			Type after = wing[i + 1];
			
			if (i != wing.length / 2) {
			
				if ( (Type.AIRLOCK.equals(before) || Type.AIRLOCK.equals(after) ||
						 i + 1 == wing.length - 1 + direction) && this.medical > 0 && med < 1) {
					wing[i] = Type.MEDICAL;
					this.medical -= 1;
					med += 1;
				} else if ( (Type.SANITATION.equals(before) || Type.SANITATION.equals(after)) && this.gym > 0) {
					wing[i] = Type.GYM_RELAXATION;
					this.gym -= 1;
				} else if ( (!Type.CANTEEN.equals(before) && !Type.FOOD_WATER.equals(before)) && 
					    (!Type.CANTEEN.equals(after) && !Type.FOOD_WATER.equals(after)) &&
					    this.sanitation > 0 && ((double) dorm / san) > 2) {
					wing[i] = Type.SANITATION;
					san += 1;
					this.sanitation -= 1;
				} else if ( !Type.AIRLOCK.equals(before) && !Type.AIRLOCK.equals(after) && 
						this.dormitory > 0 && (san == 0 || dorm / (san * 2) <= 1) && dorm < dormLimit) {
					wing[i] = Type.DORMITORY;
					dorm += 1;
					this.dormitory -= 1;
				} else if ( !Type.DORMITORY.equals(before) && !Type.DORMITORY.equals(after) && this.airlock > 0 && air < 1) {
					wing[i] = Type.AIRLOCK;
					this.airlock -= 1;
					air += 1;
				} else if (this.control > 0 && cont < 1) {
					wing[i] = Type.CONTROL;
					this.control -= 1;
					cont += 1;
				} else if (this.power > 0 && pow < 1) {
					wing[i] = Type.POWER;
					this.power -= 1;
					pow += 1;
				} else if (this.canteen > 0 && food > 0 && ((double) can / food) < ((double) food / this.water) ) {
					wing[i] = Type.CANTEEN;
					this.canteen -= 1;
					can += 1;
				} else if (this.water > 0) {
					wing[i] = Type.FOOD_WATER;
					this.water -= 1;
					food += 1;
				}
				
			}
		}
		
		return wing;
	}
	
	/**
	 * Places a modules in the configuration.
	 * @param type The type of the module.
	 * @param xCoord The x position of the module.
	 * @param yCoord The y position of the module.
	 */
	private void placeModule(final Type type, final int xCoord, final int yCoord) {
		if (Type.AIRLOCK.equals(type)) {
			this.airlockModules.add(new Coordinates(xCoord, yCoord));
		} else if (Type.CANTEEN.equals(type)) {
			this.canteenModules.add(new Coordinates(xCoord, yCoord));
		} else if (Type.CONTROL.equals(type)) {
			this.controlModules.add(new Coordinates(xCoord, yCoord));
		} else if (Type.DORMITORY.equals(type)) {
			this.dormitoryModules.add(new Coordinates(xCoord, yCoord));
		} else if (Type.FOOD_WATER.equals(type)) {
			this.foodAndWaterModules.add(new Coordinates(xCoord, yCoord));
		} else if (Type.GYM_RELAXATION.equals(type)) {
			this.gymAndRelaxationModules.add(new Coordinates(xCoord, yCoord));
		} else if (Type.MEDICAL.equals(type)) {
			this.medicalModules.add(new Coordinates(xCoord, yCoord));
		} else if (Type.PLAIN.equals(type)) {
			this.plainModules.add(new Coordinates(xCoord, yCoord));
		} else if (Type.POWER.equals(type)) {
			this.powerModules.add(new Coordinates(xCoord, yCoord));
		} else if (Type.SANITATION.equals(type)) {
			this.sanitationModules.add(new Coordinates(xCoord, yCoord));
		}
	}
	
	/**
	 * Checks that each module is placed within the appropriate bounds on the map.
	 * @return true if all modules are within bounds; else false.
	 */
	private boolean checkBounds() {
		boolean valid = true;
		
		for (int i = 0; i < this.airlockModules.size() && valid; i += 1) {
			if (!checkSingleBound(this.airlockModules.get(i))) {
	    		valid = false;
	    	}
	    }
	    for (int i = 0; i < this.canteenModules.size() && valid; i += 1) {
	    	if (!checkSingleBound(this.canteenModules.get(i))) {
	    		valid = false;
	    	}
	    }
	    for (int i = 0; i < this.controlModules.size() && valid; i += 1) {
	    	if (!checkSingleBound(this.controlModules.get(i))) {
	    		valid = false;
	    	}
	    }
	    for (int i = 0; i < this.dormitoryModules.size() && valid; i += 1) {
	    	if (!checkSingleBound(this.dormitoryModules.get(i))) {
	    		valid = false;
	    	}
	    }
	    for (int i = 0; i < this.foodAndWaterModules.size() && valid; i += 1) {
	    	if (!checkSingleBound(this.foodAndWaterModules.get(i))) {
	    		valid = false;
	    	}
	    }
	    for (int i = 0; i < this.gymAndRelaxationModules.size() && valid; i += 1) {
	    	if (!checkSingleBound(this.gymAndRelaxationModules.get(i))) {
	    		valid = false;
	    	}
	    }
	    for (int i = 0; i < this.medicalModules.size() && valid; i += 1) {
	    	if (!checkSingleBound(this.medicalModules.get(i))) {
	    		valid = false;
	    	}
	    }
	    for (int i = 0; i < this.plainModules.size() && valid; i += 1) {
	    	if (!checkSingleBound(this.plainModules.get(i))) {
	    		valid = false;
	    	}
	    }
	    for (int i = 0; i < this.powerModules.size() && valid; i += 1) {
	    	if (!checkSingleBound(this.powerModules.get(i))) {
	    		valid = false;
	    	}
	    }
	    for (int i = 0; i < this.sanitationModules.size() && valid; i += 1) {
	    	if (!checkSingleBound(this.sanitationModules.get(i))) {
	    		valid = false;
	    	}
	    }
		
	    return valid;
	}
	
	/**
	 * Checks the bounds of a single coordinate.
	 * @param coords The coordinate to check.
	 * @return true if within the bounds of the map and not within the uninhabitable area.
	 */
	private boolean checkSingleBound(final Coordinates coords) {
    	if (coords.getX() < 1 || coords.getX() > COLUMN_SIZE ||
			coords.getY() < 1 || coords.getY() > ROW_SIZE ||
			(coords.getX() >= LOWER_BOUNDS && coords.getX() <= UPPER_BOUNDS &&
			 coords.getY() >= LOWER_BOUNDS && coords.getY() <= UPPER_BOUNDS) ) {
			return false;
		}	
    	return true;
	}
	
	/**
	 * Configures the first minimum configuration.
	 * @param centerColumn The center of gravity coordinates
	 * @param centerRow
	 */
	public void setMinimumConfigOne(final int column, final int row) {
		int bestColumn = column;
		int bestRow = row;
		int west = 2;
		int north = 1;
		int south = 1;
		int east = 1;
		
		// Move x and y position if out of bounds
		while (bestColumn < west + 1) {
			bestColumn += 1;
		}
		while (COLUMN_SIZE - bestColumn < east + 1) {
			bestColumn -= 1;
		}
		while (bestRow < south + 1) {
			bestRow += 1;
		}
		while (ROW_SIZE - bestRow < north + 1) {
			bestRow -= 1;
		}
		
		// Move x and y position out of unusable terrain area.
		if (bestColumn <= UPPER_BOUNDS + 1 && bestColumn >= LOWER_BOUNDS - 1 &&
			bestRow + north + 1 >= LOWER_BOUNDS) {
			int left = bestColumn - LOWER_BOUNDS + 2;
			int right = UPPER_BOUNDS - bestColumn + 2;
			int down = (bestRow + north + 1) - LOWER_BOUNDS;
			
			if (left <= right && left <= down) {
				while (left > 0) {
					bestColumn -= 1;
					left -= 1;
				}
			} else if (right <= left && right <= down) {
				while (right > 0) {
					bestColumn += 1;
					right -= 1;
				}
			} else {
				while (down > 0) {
					bestRow -= 1;
					down -= 1;
				}
			}
		}
		
		this.placeModule(Type.PLAIN, bestColumn, bestRow);
		this.placeModule(Type.PLAIN, bestColumn - 1, bestRow);
		this.placeModule(Type.PLAIN, bestColumn + 1, bestRow);
		this.placeModule(Type.AIRLOCK, bestColumn - 2, bestRow);
		this.placeModule(Type.CANTEEN, bestColumn, bestRow + 1);
		this.placeModule(Type.CONTROL, bestColumn - 1, bestRow + 1);
		this.placeModule(Type.SANITATION, bestColumn - 1, bestRow - 1);
		this.placeModule(Type.POWER, bestColumn, bestRow - 1);
		this.placeModule(Type.FOOD_WATER, bestColumn + 1, bestRow + 1);
		this.placeModule(Type.DORMITORY, bestColumn + 1, bestRow - 1);
	}
	
	/**
	 * Sets the values of this ModuleConfiguration to match that of the first minimum configuration.
	 * @param modList The module list to find the best center of gravity.
	 */
	public void setMinimumConfigOne(final ModuleList modList) {
		this.clearConfig();
		Coordinates coords = modList.getCenterOfGravity();
		this.setMinimumConfigOne(coords.getX(), coords.getY());
	}
	
	/**
	 * Configures the second minimum configuration.
	 * @param centerColumn The center of gravity coordinates
	 * @param centerRow
	 */
	public void setMinimumConfigTwo(final int column, final int row) {
		int bestColumn = column;
		int bestRow = row;
		int west = 1;
		int north = 1;
		int south = 2;
		int east = 2;
		
		// Move x and y position if out of bounds
		while (bestColumn < west+ 1) {
			bestColumn += 1;
		}
		while (COLUMN_SIZE - bestColumn < east + 1) {
			bestColumn -= 1;
		}
		while (bestRow < south + 1) {
			bestRow += 1;
		}
		while (ROW_SIZE - bestRow < north + 1) {
			bestRow -= 1;
		}
		
		// Move x and y position out of unusable terrain area.
		if (bestColumn <= UPPER_BOUNDS + 1 && bestColumn >= LOWER_BOUNDS - 1 &&
			bestRow + north + 1 >= LOWER_BOUNDS) {
			int left = bestColumn - LOWER_BOUNDS + 2;
			int right = UPPER_BOUNDS - bestColumn + 2;
			int down = (bestRow + north + 1) - LOWER_BOUNDS;
			
			if (left <= right && left <= down) {
				while (left > 0) {
					bestColumn -= 1;
					left -= 1;
				}
			} else if (right <= left && right <= down) {
				while (right > 0) {
					bestColumn += 1;
					right -= 1;
				}
			} else {
				while (down > 0) {
					bestRow -= 1;
					down -= 1;
				}
			}
		}
		
		this.placeModule(Type.PLAIN, bestColumn, bestRow);
		this.placeModule(Type.PLAIN, bestColumn, bestRow - 1);
		this.placeModule(Type.PLAIN, bestColumn + 1, bestRow);
		this.placeModule(Type.AIRLOCK, bestColumn, bestRow - 2);
		this.placeModule(Type.CANTEEN, bestColumn, bestRow + 1);
		this.placeModule(Type.CONTROL, bestColumn - 1, bestRow);
		this.placeModule(Type.SANITATION, bestColumn - 1, bestRow - 1);
		this.placeModule(Type.POWER, bestColumn + 1, bestRow - 1);
		this.placeModule(Type.FOOD_WATER, bestColumn + 1, bestRow + 1);
		this.placeModule(Type.DORMITORY, bestColumn + 2, bestRow);
	}
	
	/**
	 * Sets the values of this ModuleConfiguration to match that of the second minimum configuration.
	 */
	public void setMinimumConfigTwo(final ModuleList modList) {
		this.clearConfig();
		Coordinates coords = modList.getCenterOfGravity();
		this.setMinimumConfigTwo(coords.getX(), coords.getY());
	}
	
	/**
	 * Finds the optimal center of gravity for the mars habitat.
	 * @param modList The list of all modules.
	 * @return the coordinates of the center of gravity.
	 */
	public Coordinates findBestCenterOfGravity(final ModuleList modList) {
		Coordinates coords = modList.getCenterOfGravity();
		int initialColumn = coords.getX();
		int initialRow = coords.getY();
		int bestColumn = initialColumn;
		int bestRow = initialRow;
		
		this.calculateConfiguration(modList, DEFAULT_COLUMN, DEFAULT_ROW);
		
		// Move x and y position if out of bounds
		while (bestColumn <= this.westLength + 1) {
			bestColumn += 1;
		}
		while (COLUMN_SIZE - bestColumn < this.eastLength + 1) {
			bestColumn -= 1;
		}
		while (bestRow <= this.southLength + 1) {
			bestRow += 1;
		}
		while (ROW_SIZE - bestRow < this.northLength + 1) {
			bestRow -= 1;
		}
		
		// Move x and y position out of unusable terrain area.
		if (bestColumn <= UPPER_BOUNDS + 1 && bestColumn >= LOWER_BOUNDS - 1 &&
			bestRow + this.northLength + 1 >= LOWER_BOUNDS) {
			int left = bestColumn - LOWER_BOUNDS + 2;
			int right = UPPER_BOUNDS - bestColumn + 2;
			int down = (bestRow + this.northLength + 1) - LOWER_BOUNDS;
			
			if (left <= right && left <= down) {
				while (left > 0) {
					bestColumn -= 1;
					left -= 1;
				}
			} else if (right <= left && right <= down) {
				while (right > 0) {
					bestColumn += 1;
					right -= 1;
				}
			} else {
				while (down > 0) {
					bestRow -= 1;
					down -= 1;
				}
			}
		}
		
		ModuleConfiguration.centerColumn = bestColumn;
		ModuleConfiguration.centerRow = bestRow;
		
		return new Coordinates(bestColumn, bestRow);
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
	
	public int getCenterRow() {
		return ModuleConfiguration.centerRow;
	}
	
	public int getCenterColumn() {
		return ModuleConfiguration.centerColumn;
	}
}

