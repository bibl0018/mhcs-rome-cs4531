package mhcs.client.moduleConfigurations;

import mhcs.client.module.Module.Type;
import mhcs.client.moduleConfigurations.Coordinates;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;

/**
 * Generates a Grid of any given ModuleConfiguration.
 * @author Ryan Stowell
 */
public class ConfigurationMap {
	
	public static final int ROWS = 50;
	public static final int COLUMNS = 100;
	public static final int IMAGE_SIZE = 25;
	public static final String STYLE = "tableCell";
	
	/**
	 * Default constructor of a module map.
	 */
	public ConfigurationMap(){

	}

	/**
	 * Constructs the grid and loads all modules on to the grid.
	 * @param config A module configuration that will be loaded to the grid.
	 * @return The module configuration as a Grid.
	 */
	public static Grid getConfigurationGrid(final ModuleConfiguration config) {
		if (config == null) {
	    	return null;
	    }
		
		Grid g = new Grid(ROWS, COLUMNS);
		int xCoord;
		int yCoord;
		
		//Set the height and width of each cell in the grid,
		//can be changed if we need to fit it on a smaller screen.
	    for(xCoord = 0; xCoord < ROWS; ++xCoord){
	    	for(yCoord = 0; yCoord < COLUMNS; ++yCoord){
	    		
	    		//Sets the default size for all cells.
	    		g.getCellFormatter().setStyleName(xCoord, yCoord, STYLE);
	    	}
	    }
		
	    
	    for (int i = 0; i < config.airlockModules.size(); i += 1) {
	    	Coordinates coord = config.airlockModules.get(i);
	    	g = setImage(g, (ROWS - 1) - (coord.yCoord - 1), coord.xCoord - 1, Type.AIRLOCK);	
	    }
	    for (int i = 0; i < config.canteenModules.size(); i += 1) {
	    	Coordinates coord = config.canteenModules.get(i);
	    	g = setImage(g, (ROWS - 1) - (coord.yCoord - 1), coord.xCoord - 1, Type.CANTEEN);		
	    }
	    for (int i = 0; i < config.controlModules.size(); i += 1) {
	    	Coordinates coord = config.controlModules.get(i);
	    	g = setImage(g, (ROWS - 1) - (coord.yCoord - 1), coord.xCoord - 1, Type.CONTROL);	
	    }
	    for (int i = 0; i < config.dormitoryModules.size(); i += 1) {
	    	Coordinates coord = config.dormitoryModules.get(i);
	    	g = setImage(g, (ROWS - 1) - (coord.yCoord - 1), coord.xCoord - 1, Type.DORMITORY);	
	    }
	    for (int i = 0; i < config.foodAndWaterModules.size(); i += 1) {
	    	Coordinates coord = config.foodAndWaterModules.get(i);
	    	g = setImage(g, (ROWS - 1) - (coord.yCoord - 1), coord.xCoord - 1, Type.FOOD_WATER);	
	    }
	    for (int i = 0; i < config.gymAndRelaxationModules.size(); i += 1) {
	    	Coordinates coord = config.gymAndRelaxationModules.get(i);
	    	g = setImage(g, (ROWS - 1) - (coord.yCoord - 1), coord.xCoord - 1, Type.GYM_RELAXATION);	
	    }
	    for (int i = 0; i < config.medicalModules.size(); i += 1) {
	    	Coordinates coord = config.medicalModules.get(i);
	    	g = setImage(g, (ROWS - 1) - (coord.yCoord - 1), coord.xCoord - 1, Type.MEDICAL);	
	    }
	    for (int i = 0; i < config.plainModules.size(); i += 1) {
	    	Coordinates coord = config.plainModules.get(i);
	    	g = setImage(g, (ROWS - 1) - (coord.yCoord - 1), coord.xCoord - 1, Type.PLAIN);	
	    }
	    for (int i = 0; i < config.powerModules.size(); i += 1) {
	    	Coordinates coord = config.powerModules.get(i);
	    	g = setImage(g, (ROWS - 1) - (coord.yCoord - 1), coord.xCoord - 1, Type.POWER);	
	    }
	    for (int i = 0; i < config.sanitationModules.size(); i += 1) {
	    	Coordinates coord = config.sanitationModules.get(i);
	    	g = setImage(g, (ROWS - 1) - (coord.yCoord - 1), coord.xCoord - 1, Type.SANITATION);	
	    }
	    
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
	private static Grid setImage(final Grid g, final int row, final int col, final Type type){
		
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
