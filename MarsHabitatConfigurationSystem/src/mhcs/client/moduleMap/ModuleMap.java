package mhcs.client.moduleMap;

/**
 * A visual display of the modules on a grid.
 * Can be used for any module coordinate combination (pre-configured layouts or configured layouts.
 * @author Jeremiah Wilhelmy
 */

import mhcs.client.module.ModuleList;
import mhcs.client.module.Module;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;


public class ModuleMap implements IsWidget{
	
	public static final int ROWS = 50;
	public static final int COLUMNS = 100;
	public static final int HOR_IMAGE_SIZE = 25;
	public static final int VER_IMAGE_SIZE = 25;
	public static final String STYLE = "tableCell";
	
	private ModuleList modList;
	
	/**
	 * Default constructor of a module map.
	 */
	public ModuleMap(final ModuleList moduleList){
		this.modList = moduleList;
	}
	
	/**
	 * Constructs the grid and loads all modules on to the grid.
	 * @param moduleList The list of pre-sorted modules.
	 * @return 
	 */
	public Widget asWidget(){
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
		
		//For every module in the module list
		for(int i = 1; i < Module.SIZE; i += 1 ){
			Module module = this.modList.getModule(i);
			
			//If module is actually a module and not just an empty space.
			if(module != null){
				xCoord = module.getXCoord();
				yCoord = module.getYCoord();
			
				//Passes the module and coordinates into the setImage function, 
				//which will change the image at the correct grid coordinate then 
				//return a new grid with the image changed.
				g = setImage(g, (ROWS - 1) - (yCoord - 1), xCoord - 1, module);
				
				//Resizes the cell after the image has been placed.
				//g.getCellFormatter().setStyleName(yCoord, xCoord, STYLE);						
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
	public Grid setImage(final Grid g, final int row, final int col, final Module module){
		
		//Detects module type, changes image and image size at coordinate accordingly.
		if (module.getType().equals(Module.Type.PLAIN)){
			Image image = new Image("images/Plain.jpg");
			image.setPixelSize(HOR_IMAGE_SIZE, VER_IMAGE_SIZE);
			//image.setStyleName(STYLE);
			g.setWidget(row, col, image);
		}
		else if (module.getType().equals(Module.Type.DORMITORY)){
			Image image = new Image("images/Dormitory.jpg");
			image.setPixelSize(HOR_IMAGE_SIZE, VER_IMAGE_SIZE);
			g.setWidget(row, col, image);
		}
		else if (module.getType().equals(Module.Type.SANITATION)){
			Image image = new Image("images/Sanitation.jpg");
			image.setPixelSize(HOR_IMAGE_SIZE, VER_IMAGE_SIZE);
			g.setWidget(row, col, image);
		}
		else if (module.getType().equals(Module.Type.FOOD_WATER)){
			Image image = new Image("images/Food.jpg");
			image.setPixelSize(HOR_IMAGE_SIZE, VER_IMAGE_SIZE);
			g.setWidget(row, col, image);
		}
		else if (module.getType().equals(Module.Type.GYM_RELAXATION)){
			Image image = new Image("images/Gym.jpg");
			image.setPixelSize(HOR_IMAGE_SIZE, VER_IMAGE_SIZE);
			g.setWidget(row, col, image);
		}
		else if (module.getType().equals(Module.Type.CANTEEN)){
			Image image = new Image("images/Canteen.jpg");
			image.setPixelSize(HOR_IMAGE_SIZE, VER_IMAGE_SIZE);
			g.setWidget(row, col, image);
		}
		else if (module.getType().equals(Module.Type.POWER)){
			Image image = new Image("images/Power.jpg");
			image.setPixelSize(HOR_IMAGE_SIZE, VER_IMAGE_SIZE);
			g.setWidget(row, col, image);
		}
		else if (module.getType().equals(Module.Type.CONTROL)){
			Image image = new Image("images/Control.jpg");
			image.setPixelSize(HOR_IMAGE_SIZE, VER_IMAGE_SIZE);
			g.setWidget(row, col, image);
		}
		else if (module.getType().equals(Module.Type.MEDICAL)){
			Image image = new Image("images/Medical.jpg");
			image.setPixelSize(HOR_IMAGE_SIZE, VER_IMAGE_SIZE);
			g.setWidget(row, col, image);
		}
		else if (module.getType().equals(Module.Type.AIRLOCK)){
			Image image = new Image("images/Airlock.jpg");
			image.setPixelSize(HOR_IMAGE_SIZE, VER_IMAGE_SIZE);
			g.setWidget(row, col, image);
		}
		return g;
		
	}
	
}
