package mhcs.client.moduleConfigurations;

import mhcs.client.module.Module.Type;
import mhcs.client.module.ModuleList;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

public class ConfigurationMap implements IsWidget {

	public static final int CENTER = 12;
	public static final int SIZE = 25;
	public static final int WEST_LIMIT = 10;
	public static final int EAST_LIMIT = 14;
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
	
	//Function for constructing a configuration. This will probably not be in this class.
	public Type[][] calculateConfiguration(final ModuleList modules) {
		
		Type[][] config = new Type[SIZE][SIZE];
		modules.populateList();
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
		int northTip = 0;
		int southTip = 0;
		int eastTip = 0;
		int westTip = 0;
		
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
		
		
		// Places the first plain module in the center of the map.
		if (plain > 0) {
			config[CENTER][CENTER] = Type.PLAIN;
			plain -= 1;
		}
		
		// Places the remaining plain modules in four wings in a cross formation.
		for (int wings = FOUR; wings > 0; wings -= 1) {
			int i = 0;
			if (wings == FOUR) {
				for (i = 0; i < (plain/wings); i += 1) {
					config[CENTER + i + 1][CENTER] = Type.PLAIN;
				}
				northTip = CENTER + i;
			} else if (wings == THREE) {
				for (i = 0; i < (plain/wings); i += 1) {
					config[CENTER][CENTER + i + 1] = Type.PLAIN;
				}
				eastTip = CENTER + i;
			} else if (wings == TWO) {
				for (i = 0; i < (plain/wings); i += 1) {
					config[CENTER - i - 1][CENTER] = Type.PLAIN;
					}
				southTip = CENTER - i;
			} else if (wings == ONE) {
				for (i = 0; i < (plain/wings); i += 1) {
					config[CENTER][CENTER - i - 1] = Type.PLAIN;
				}
				westTip = CENTER - i;
			}
			plain -= i;
		}
		
		// Place airlocks at tips of wings.
		for (int i = 0; i < airlock; i += 1) {
			if (i == 0) {
				config[northTip + 1][CENTER] = Type.AIRLOCK;
			} else if (i == 1){
				config[CENTER][eastTip + 1] = Type.AIRLOCK;
			} else if (i == TWO){
				config[southTip - 1][CENTER] = Type.AIRLOCK;
			} else if (i == THREE) {
				config[CENTER][westTip - 1] = Type.AIRLOCK;
			}
		}
		
		// Place medical modules by the airlocks.
		for (int i = 0; i < medical; i += 1) {
			if (i == 0) {
				config[northTip][CENTER - 1] = Type.MEDICAL;
			} else if (i == 1 && eastTip > EAST_LIMIT){
				config[CENTER + 1][eastTip] = Type.MEDICAL;
			} else if (i == TWO){
				config[southTip][CENTER + 1] = Type.MEDICAL;
			} else if (i == THREE && westTip < WEST_LIMIT) {
				config[CENTER - 1][westTip] = Type.MEDICAL;
			}
		}
		
		// Place control modules by the airlocks and across from medical.
		for (int i = 0; i < control; i += 1) {
			if (i == 0) {
				config[northTip][CENTER + 1] = Type.CONTROL;
			} else if (i == 1 && eastTip > EAST_LIMIT){
				config[CENTER - 1][eastTip] = Type.CONTROL;
			} else if (i == TWO){
				config[southTip][CENTER - 1] = Type.CONTROL;
			} else if (i == THREE && westTip < WEST_LIMIT) {
				config[CENTER + 1][westTip] = Type.CONTROL;
			}
		}
		
		// Place power modules in each wing next to medical.
		for (int i = 0; i < power; i += 1) {
			if (i == 0 && (northTip - 1) > CENTER) {
				config[northTip - 1][CENTER - 1] = Type.POWER;
			} else if (i == 1 && (eastTip - 1) > EAST_LIMIT){
				config[CENTER + 1][eastTip - 1] = Type.POWER;
			} else if (i == TWO && (southTip + 1) < CENTER){
				config[southTip + 1][CENTER + 1] = Type.POWER;
			} else if (i == THREE && (westTip + 1) > WEST_LIMIT) {
				config[CENTER + 1][westTip + 1] = Type.POWER;
			}
		}
		
		// Place canteen modules in east and west wings.
		for (int i = 0; i < canteen; i += 1) {
			if (i == 0 && (eastTip - 1) > EAST_LIMIT) {
				config[CENTER - 1][eastTip - 1] = Type.CANTEEN;
			} else if (i == 1 && (westTip + 1) < WEST_LIMIT){
				config[CENTER + 1][westTip + 1] = Type.CANTEEN;
			} else if (i == TWO && (eastTip - THREE) > EAST_LIMIT){
				config[CENTER - 1][eastTip - THREE] = Type.CANTEEN;
			} else if (i == THREE && (westTip + THREE) < WEST_LIMIT) {
				config[CENTER + 1][westTip + THREE] = Type.CANTEEN;
			}
		}
		
		// Place food and water modules in east and west wings
		for (int i = 0; i < water; i += 1) {
			if (i == 0 && (westTip + TWO) < WEST_LIMIT) {
				config[CENTER + 1][westTip + TWO] = Type.FOOD_WATER;
			} else if (i == 1 && (eastTip - TWO) > EAST_LIMIT){
				config[CENTER + 1][eastTip - TWO] = Type.FOOD_WATER;
			} else if (i == TWO && (westTip + TWO) < WEST_LIMIT){
				config[CENTER - 1][westTip + TWO] = Type.FOOD_WATER;
			} else if (i == THREE && (eastTip - TWO) > EAST_LIMIT) {
				config[CENTER - 1][eastTip - TWO] = Type.FOOD_WATER;
			} else if (i == FOUR && (westTip + THREE) < WEST_LIMIT) {
				config[CENTER - 1][westTip + THREE] = Type.FOOD_WATER;
			} else if (i == FIVE && (eastTip - THREE) > EAST_LIMIT){
				config[CENTER + 1][eastTip - THREE] = Type.FOOD_WATER;
			} else if (i == SIX && (westTip + FOUR) < WEST_LIMIT){
				config[CENTER - 1][westTip + FOUR] = Type.FOOD_WATER;
			} else if (i == SEVEN && (eastTip - FOUR) > EAST_LIMIT) {
				config[CENTER - 1][eastTip - FOUR] = Type.FOOD_WATER;
			} else if (i == EIGHT && (westTip + FOUR) < WEST_LIMIT) {
				config[CENTER + 1][westTip + FOUR] = Type.FOOD_WATER;
			} else if (i == NINE && (eastTip - FOUR) > EAST_LIMIT){
				config[CENTER + 1][eastTip - FOUR] = Type.FOOD_WATER;
			}
		}
		
		// Place gym modules in each wing
		for (int i = 0; i < gym; i += 1) {
			if (i == 0 && (northTip - 2) > CENTER) {
				config[northTip - 2][CENTER + 1] = Type.GYM_RELAXATION;
			} else if (i == 1 && (eastTip - SIX) > EAST_LIMIT){
				config[CENTER - 1][eastTip - SIX] = Type.GYM_RELAXATION;
			} else if (i == TWO && (southTip + 2) < CENTER){
				config[southTip + 2][CENTER - 1] = Type.GYM_RELAXATION;
			} else if (i == THREE && (westTip + SIX) < WEST_LIMIT) {
				config[CENTER - 1][westTip + SIX] = Type.GYM_RELAXATION;
			}
		}
		
		// Place sanitation modules. 1 in east and west, 4 in north and south
		for (int i = 0; i < sanitation; i += 1) {
			if (i == 0 && (northTip - 1) > CENTER) {
				config[northTip - 1][CENTER + 1] = Type.SANITATION;
			} else if (i == 1 && (southTip + 1) < CENTER){
				config[southTip + 1][CENTER - 1] = Type.SANITATION;
			} else if (i == TWO && (northTip - THREE) > CENTER){
				config[northTip - THREE][CENTER + 1] = Type.SANITATION;
			} else if (i == THREE && (southTip + THREE) < CENTER) {
				config[southTip + THREE][CENTER - 1] = Type.SANITATION;
			} else if (i == FOUR && (northTip - FIVE) > CENTER){
				config[northTip - FIVE][CENTER + 1] = Type.SANITATION;
			} else if (i == FIVE && (southTip + FIVE) < CENTER) {
				config[southTip + FIVE][CENTER - 1] = Type.SANITATION;
			} else if (i == SIX && (westTip + SIX) < WEST_LIMIT){
				config[CENTER - 1][westTip + SIX] = Type.SANITATION;
			} else if (i == SEVEN && (eastTip - SIX) > EAST_LIMIT) {
				config[CENTER - 1][eastTip - SIX] = Type.SANITATION;
			} else if (i == EIGHT && (northTip - SIX) > CENTER){
				config[northTip - SIX][CENTER + 1] = Type.SANITATION;
			} else if (i == NINE && (southTip + SIX) < CENTER) {
				config[southTip + SIX][CENTER - 1] = Type.SANITATION;
			}
		}
		
		// Place dormitory modules. 2 in east and west, 8 in north and south.
		for (int i = 0; i < dormitory; i += 1) {
			if (i == 0 && (northTip - TWO) > CENTER) {
				config[northTip - TWO][CENTER - 1] = Type.DORMITORY;
			} else if (i == 1 && (southTip + TWO) < CENTER){
				config[southTip + TWO][CENTER + 1] = Type.DORMITORY;
			} else if (i == TWO && (northTip - THREE) > CENTER){
				config[northTip - THREE][CENTER - 1] = Type.DORMITORY;
			} else if (i == THREE && (southTip + THREE) < CENTER) {
				config[southTip + THREE][CENTER + 1] = Type.DORMITORY;
			} else if (i == FOUR && (northTip - FOUR) > CENTER){
				config[northTip - FOUR][CENTER + 1] = Type.DORMITORY;
			} else if (i == FIVE && (southTip + FOUR) < CENTER) {
				config[southTip + FOUR][CENTER - 1] = Type.DORMITORY;
			} else if (i == SIX && (northTip - FOUR) > CENTER){
				config[northTip - FOUR][CENTER - 1] = Type.DORMITORY;
			} else if (i == SEVEN && (southTip + FOUR) < CENTER) {
				config[southTip + FOUR][CENTER + 1] = Type.DORMITORY;
			} else if (i == EIGHT && (westTip + FIVE) < WEST_LIMIT){
				config[CENTER - 1][westTip + FIVE] = Type.DORMITORY;
			} else if (i == NINE && (eastTip - FIVE) > EAST_LIMIT) {
				config[CENTER + 1][eastTip - FIVE] = Type.DORMITORY;
			} else if (i == TEN && (westTip + FIVE) < WEST_LIMIT){
				config[CENTER + 1][westTip + FIVE] = Type.DORMITORY;
			} else if (i == ELEVEN && (eastTip - FIVE) > EAST_LIMIT) {
				config[CENTER - 1][eastTip - FIVE] = Type.DORMITORY;
			} else if (i == TWELVE && (northTip - FIVE) > CENTER){
				config[northTip - FIVE][CENTER - 1] = Type.DORMITORY;
			} else if (i == THIRTEEN && (southTip + FIVE) < CENTER) {
				config[southTip + FIVE][CENTER - 1] = Type.DORMITORY;
			} else if (i == FOURTEEN && (northTip - SIX) > CENTER){
				config[northTip - SIX][CENTER - 1] = Type.DORMITORY;
			} else if (i == FIFTEEN && (southTip + SIX) < CENTER) {
				config[southTip + SIX][CENTER + 1] = Type.DORMITORY;
			} else if (i == SIXTEEN && (northTip - SEVEN) > CENTER){
				config[northTip - SEVEN][CENTER - 1] = Type.DORMITORY;
			} else if (i == SEVENTEEN && (southTip + SEVEN) < CENTER) {
				config[southTip + SEVEN][CENTER + 1] = Type.DORMITORY;
			} else if (i == EIGHTEEN && (northTip - SEVEN) > CENTER){
				config[northTip - SEVEN][CENTER + 1] = Type.DORMITORY;
			} else if (i == NINETEEN && (southTip + SEVEN) < CENTER) {
				config[southTip + SEVEN][CENTER - 1] = Type.DORMITORY;
			}
		}	
		
		return config;
	}

	
	private final ModuleList modList;
	
	public static final int ROWS = 50;
	public static final int COLUMNS = 100;
	public static final int IMAGE_SIZE = 12;
	
	/**
	 * Default constructor of a module map.
	 */
	public ConfigurationMap(ModuleList moduleList){
		modList = moduleList;
	}
	
	/**
	 * Constructs the grid and loads all modules on to the grid.
	 * @param moduleList The list of pre-sorted modules.
	 * @return 
	 */
	public Widget asWidget() {
		Grid g = new Grid(ROWS, COLUMNS);
		int xCoord;
		int yCoord;
		
		//Set the height and width of each cell in the grid,
		//can be changed if we need to fit it on a smaller screen.
	    for(xCoord = 0; xCoord < ROWS; ++xCoord){
	    	for(yCoord = 0; yCoord < COLUMNS; ++yCoord){
	    		
	    		//Sets the default size for all cells.
	    		g.getCellFormatter().setStyleName(xCoord,yCoord,"tableCell");
	    	}
	    }
		
	    Type[][] config = calculateConfiguration(modList);
		//For every type in the configuration
		for(int i = 0; i < 25; i++ ) {
			for (int k = 0; k < 25; k++) {
				
				
				//Passes the module and coordinates into the setImage function, 
				//which will change the image at the correct grid coordinate then 
				//return a new grid with the image changed.
				g = setImage(g, i, k, config[i][k]);
				
				//Resizes the cell after the image has been placed.
				g.getCellFormatter().setStyleName(i, k, "tableCell");						
			}
		}
		
		//RootPanel.get().add(g);
		return g;		
	}

	/**
	 * Takes in a module and coordinates, finds the type of that module, and changes the image of 
	 * the correct cell on the grid to the image of the type of the passed in module
	 * @param g The grid without the image changed.
	 * @param row The Y-coordinate of the module, will also be the same coordinate on the grid.
	 * @param col The X-coordinate of the module, will also be the same coordinate on the grid.
	 * @param module The module to be added to the grid.
	 * @return g A copy of the previous grid with the new module added.
	 */
	public Grid setImage(Grid g, int row, int col, Type type){
		
		//Detects module type, changes image and image size at coordinate accordingly.
		if (type != null) {
			if (type.equals(Type.PLAIN)){
				Image image = new Image("images/Plain.jpg");
				image.setPixelSize(IMAGE_SIZE, IMAGE_SIZE);
				g.setWidget(row, col, image);
			}
			else if (type.equals(Type.DORMITORY)){
				Image image = new Image("images/Dormitory.jpg");
				image.setPixelSize(IMAGE_SIZE, IMAGE_SIZE);
				g.setWidget(row, col, image);
			}
			else if (type.equals(Type.SANITATION)){
				Image image = new Image("images/Sanitation.jpg");
				image.setPixelSize(IMAGE_SIZE, IMAGE_SIZE);
				g.setWidget(row, col, image);
			}
			else if (type.equals(Type.FOOD_WATER)){
				Image image = new Image("images/Food.jpg");
				image.setPixelSize(IMAGE_SIZE, IMAGE_SIZE);
				g.setWidget(row, col, image);
			}
			else if (type.equals(Type.GYM_RELAXATION)){
				Image image = new Image("images/Gym.jpg");
				image.setPixelSize(IMAGE_SIZE, IMAGE_SIZE);
				g.setWidget(row, col, image);
			}
			else if (type.equals(Type.CANTEEN)){
				Image image = new Image("images/Canteen.jpg");
				image.setPixelSize(IMAGE_SIZE, IMAGE_SIZE);
				g.setWidget(row, col, image);
			}
			else if (type.equals(Type.POWER)){
				Image image = new Image("images/Power.jpg");
				image.setPixelSize(IMAGE_SIZE, IMAGE_SIZE);
				g.setWidget(row, col, image);
			}
			else if (type.equals(Type.CONTROL)){
				Image image = new Image("images/Control.jpg");
				image.setPixelSize(IMAGE_SIZE, IMAGE_SIZE);
				g.setWidget(row, col, image);
			}
			else if (type.equals(Type.MEDICAL)){
				Image image = new Image("images/Medical.jpg");
				image.setPixelSize(IMAGE_SIZE, IMAGE_SIZE);
				g.setWidget(row, col, image);
			}
			else if (type.equals(Type.AIRLOCK)){
				Image image = new Image("images/Airlock.jpg");
				image.setPixelSize(IMAGE_SIZE, IMAGE_SIZE);
				g.setWidget(row, col, image);
			}
		}
		return g;
		
	}
}
