package minimumConfigurations;

import java.util.Iterator;

import mhcs.client.module.Module.Type;
import mhcs.client.module.ModuleList;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class ConfigurationMap extends Panel {

	
	
	@Override
	public Iterator<Widget> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Widget child) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	//Function for constructing a configuration. This will probably not be in this class.
	public void calculateConfiguration(final ModuleList modules) {
		
		Type[][] config = new Type[25][25];
		int plain = modules.getNumOfPlain();
		int airlock = modules.getNumOfAirlock();
		int power = modules.getNumOfPower();
		int water = modules.getNumOfWater();
		int santitation = modules.getNumOfSanitation();
		int canteen = modules.getNumOfCanteen();
		int dormitory = modules.getNumOfDormitory();
		int gym = modules.getNumOfGym();
		int medical = modules.getNumOfMedical();
		int control = modules.getNumOfControl();
		
		
		// Places the first plain module in the center of the map.
		config[12][12] = Type.PLAIN;
		plain -= 1;
		
		// Places the remaining plain modules in four wings in a cross formation.
		for (int wings = 4; wings > 0; wings -= 1) {
			int i = 0;
			switch (wings) {
			case 4: 
				for (i = 0; i < (plain/wings); i += 1) {
					config[12][12 + i + 1] = Type.PLAIN;
				}
			case 3:
				for (i = 0; i < (plain/wings); i += 1) {
					config[12 + i + 1][12] = Type.PLAIN;
				}
			case 2:
				for (i = 0; i < (plain/wings); i += 1) {
					config[12][12 - i - 1] = Type.PLAIN;
				}
			case 1:
				for (i = 0; i < (plain/wings); i += 1) {
					config[12 - i - 1][12] = Type.PLAIN;
				}
			}
			plain -= i;
		}
		
		// Builds two dormitory wings
		// Dormitory to Sanitation 2:1
		// Sanitation not next to Canteen or Food/Water
		// Sanitation next to Gym
		// Airlock not next to dormitory
		
	}
	
}
