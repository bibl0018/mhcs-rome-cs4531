package mhcs.client.moduleMap;

/**
 * A visual display of the modules on a grid.
 * Can be used for any module coordinate combination (pre-configured layouts or configured layouts.
 * @author Jeremiah Wilhelmy
 */

import mhcs.client.module.ModuleList;
import mhcs.client.module.Module;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;


public class ModuleMap implements IsWidget{
	ModuleList modList;
	
	/**
	 * Default constructor of a module map.
	 */
	public ModuleMap(ModuleList moduleList){
		modList = moduleList;
	}
	
	/**
	 * Constructs the grid and loads all modules on to the grid.
	 * @param moduleList The list of pre-sorted modules.
	 * @return 
	 */
	public Widget asWidget(){
		Grid g = new Grid(50,50);
		
		//For every module in the module list
		for(int i = 1; i < 191; i++ ){
			Module module = modList.getModule(i);
			int xCoord = module.getXCoord();
			int yCoord = module.getYCoord();
			
			//If module is actually a module and not just an empty space.
			if(module != null){

			
				//Passes the module and coordinates into the setImage function, 
				//which will change the image at the correct grid coordinate then 
				//return a new grid with the image changed.
				g = setImage(g, xCoord, yCoord, module);
				
				//g.getElement().getStyle().setHeight(13, Style.Unit.PX);
				//g.getElement().getStyle().setWidth(19, Style.Unit.PX);
				
				
			}
			
			//Set the height and width of each cell in the grid,
			//can be changed if we need to fit it on a smaller screen.
			g.getCellFormatter().setWidth(xCoord, yCoord, "19px");
			g.getCellFormatter().setHeight(xCoord, yCoord, "13px");
			
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
	public Grid setImage(Grid g, int row, int col, Module module){
		
		//Detects module type, changes image at coordinate accordingly.
		if (module.getType().equals(Module.Type.PLAIN)){
			g.setWidget(row, col, new Image("images/Plain.jpg"));
		}
		else if (module.getType().equals(Module.Type.DORMITORY)){
			g.setWidget(row, col, new Image("images/Dormitory.jpg"));
		}
		else if (module.getType().equals(Module.Type.SANITATION)){
			g.setWidget(row, col, new Image ("images/Sanitation.jpg"));
		}
		else if (module.getType().equals(Module.Type.FOOD_WATER)){
			g.setWidget(row, col, new Image ("images/Food.jpg"));
		}
		else if (module.getType().equals(Module.Type.GYM_RELAXATION)){
			g.setWidget(row, col, new Image ("images/Gym.jpg"));
		}
		else if (module.getType().equals(Module.Type.CANTEEN)){
			g.setWidget(row, col, new Image ("images/Canteen.jpg"));
		}
		else if (module.getType().equals(Module.Type.POWER)){
			g.setWidget(row, col, new Image ("images/Power.jpg"));
		}
		else if (module.getType().equals(Module.Type.CONTROL)){
			g.setWidget(row, col, new Image ("images/Control.jpg"));
		}
		else if (module.getType().equals(Module.Type.MEDICAL)){
			g.setWidget(row, col, new Image ("images/Medical.jpg"));
		}
		return g;
		
	}
	
	
}