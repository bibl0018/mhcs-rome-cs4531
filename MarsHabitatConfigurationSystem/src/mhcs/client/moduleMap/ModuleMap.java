package mhcs.client.moduleMap;

/**
 * A visual display of the modules on a grid.
 * Can be used for any module coordinate combination (pre-configured layouts or configured layouts.
 * @author Jeremiah Wilhelmy
 */

import mhcs.client.module.ModuleList;
import mhcs.client.module.Module;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;


public abstract class ModuleMap implements EntryPoint{
	
	/**
	 * Constructs the grid and loads all modules on to the grid.
	 * @param moduleList The list of pre-sorted modules.
	 */
	public void onModuleLoad(ModuleList moduleList){
		Grid g = new Grid(50,50);
		
		//For every module in the module list
		for(int i = 1; i < 191; i++ ){
			Module module = moduleList.getModule(i);

			//If module is actually a module and not just an empty space.
			if(module != null){
				int xCoord = module.getXCoord();
				int yCoord = module.getYCoord();
				
				//Set the height and width of each cell in the grid,
				//can be changed if we need to fit it on a smaller screen.
				g.getCellFormatter().setWidth(xCoord, yCoord, "25px");
				g.getCellFormatter().setHeight(xCoord, yCoord, "25px");
				
				//Passes the module and coordinates into the setImage function, 
				//which will change the image at the correct grid coordinate then 
				//return a new grid with the image changed.
				g = setImage(g, xCoord, yCoord, module);
			}
		}
				
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
	public Grid setImage(Grid g, int row, int col, Module module){
		
		//Detects module type, changes image at coordinate accordingly.
		if (module.getType().equals("PLAIN")){
			g.setWidget(row, col, new Image("images/Plain.jpg"));
		}
		else if (module.getType().equals("DORMITORY")){
			g.setWidget(row, col, new Image("images/Dormitory.jpg"));
		}
		else if (module.getType().equals("SANITATION")){
			g.setWidget(row, col, new Image ("images/Sanitation.jpg"));
		}
		else if (module.getType().equals("FOOD_WATER")){
			g.setWidget(row, col, new Image ("images/Food.jpg"));
		}
		else if (module.getType().equals("GYM_RELAXATION")){
			g.setWidget(row, col, new Image ("images/Gym.jpg"));
		}
		else if (module.getType().equals("CANTEEN")){
			g.setWidget(row, col, new Image ("images/Canteen.jpg"));
		}
		else if (module.getType().equals("POWER")){
			g.setWidget(row, col, new Image ("images/Power.jpg"));
		}
		else if (module.getType().equals("CONTROL")){
			g.setWidget(row, col, new Image ("images/Control.jpg"));
		}
		else if (module.getType().equals("MEDICAL")){
			g.setWidget(row, col, new Image ("images/Medical.jpg"));
		}
		return g;
		
	}
	
	
}