package mhcs.client.moduleMap;


import mhcs.client.module.ModuleList;
import mhcs.client.module.Module;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;


public class ModuleMap{
	
	public void onModuleLoad(ModuleList moduleList){
		Grid g = new Grid(50,50);
		
		for(int i = 1; i < 191; i++ ){
			Module module = moduleList.getModule(i);
			
			int xCoord = module.getXCoord();
			int yCoord = module.getYCoord();

			if(module != null){
				g.getCellFormatter().setWidth(xCoord, yCoord, "25px");
				g.getCellFormatter().setHeight(xCoord, yCoord, "25px");
				g = setImage(g, xCoord, yCoord, module);
			}
		}
				
	}

	
	public Grid setImage(Grid g, int row, int col, Module module){
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