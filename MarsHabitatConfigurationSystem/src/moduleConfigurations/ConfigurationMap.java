package moduleConfigurations;

import java.util.Iterator;

import mhcs.client.module.Module.Type;
import mhcs.client.module.ModuleList;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class ConfigurationMap extends Panel {

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
	
	@Override
	public Iterator<Widget> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(final Widget child) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	//Function for constructing a configuration. This will probably not be in this class.
	public void calculateConfiguration(final ModuleList modules) {
		
		Type[][] config = new Type[SIZE][SIZE];
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
		int northTip = 0;
		int southTip = 0;
		int eastTip = 0;
		int westTip = 0;
		
		// Places the first plain module in the center of the map.
		config[CENTER][CENTER] = Type.PLAIN;
		plain -= 1;
		
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
			} else if (i == 2){
				config[southTip - 1][CENTER] = Type.AIRLOCK;
			} else if (i == THREE) {
				config[CENTER][westTip - 1] = Type.AIRLOCK;
			}
		}
		
		// Place medical modules by the airlocks.
		for (int i = 0; i < medical; i += 1) {
			if (i == 0) {
				config[northTip][CENTER - 1] = Type.MEDICAL;
			} else if (i == 1){
				config[CENTER + 1][eastTip] = Type.MEDICAL;
			} else if (i == 2){
				config[southTip][CENTER + 1] = Type.MEDICAL;
			} else if (i == THREE) {
				config[CENTER - 1][westTip] = Type.MEDICAL;
			}
		}
		
		// Place control modules across from by the airlocks and across from medical.
		for (int i = 0; i < control; i += 1) {
			if (i == 0) {
				config[northTip][CENTER + 1] = Type.CONTROL;
			} else if (i == 1){
				config[CENTER - 1][eastTip] = Type.CONTROL;
			} else if (i == 2){
				config[southTip][CENTER - 1] = Type.CONTROL;
			} else if (i == THREE) {
				config[CENTER + 1][westTip] = Type.CONTROL;
			}
		}
		
		// Place power modules in east and west wings by control and medical
		for (int i = 0; i < power; i += 1) {
			if (i == 0 && (westTip + 1) < WEST_LIMIT) {
				config[CENTER + 1][westTip + 1] = Type.POWER;
			} else if (i == 1 && (eastTip - 1) > EAST_LIMIT){
				config[CENTER + 1][eastTip - 1] = Type.POWER;
			} else if (i == 2 && (westTip + 2) < WEST_LIMIT){
				config[CENTER - 1][westTip + 1] = Type.POWER;
			} else if (i == THREE && (eastTip - 1) > EAST_LIMIT) {
				config[CENTER - 1][eastTip - 1] = Type.POWER;
			}
		}
		
		// Place water modules in east and west wings by power modules
		for (int i = 0; i < water; i += 1) {
			if (i == 0 && (westTip + 2) < WEST_LIMIT) {
				config[CENTER + 1][westTip + 2] = Type.FOOD_WATER;
			} else if (i == 1 && (eastTip - 2) > EAST_LIMIT){
				config[CENTER + 1][eastTip - 2] = Type.FOOD_WATER;
			} else if (i == 2 && (westTip + 2) < WEST_LIMIT){
				config[CENTER - 1][westTip + 2] = Type.FOOD_WATER;
			} else if (i == THREE && (eastTip - 2) > EAST_LIMIT) {
				config[CENTER - 1][eastTip - 2] = Type.FOOD_WATER;
			} else if (i == FOUR && (westTip + THREE) < WEST_LIMIT) {
				config[CENTER + 1][westTip + THREE] = Type.FOOD_WATER;
			} else if (i == FIVE && (eastTip - THREE) > EAST_LIMIT){
				config[CENTER + 1][eastTip - THREE] = Type.FOOD_WATER;
			} else if (i == SIX && (westTip + THREE) < WEST_LIMIT){
				config[CENTER - 1][westTip + THREE] = Type.FOOD_WATER;
			} else if (i == SEVEN && (eastTip - THREE) > EAST_LIMIT) {
				config[CENTER - 1][eastTip - THREE] = Type.FOOD_WATER;
			} else if (i == EIGHT && (westTip + FOUR) < WEST_LIMIT) {
				config[CENTER + 1][westTip + FOUR] = Type.FOOD_WATER;
			} else if (i == NINE && (eastTip - FOUR) > EAST_LIMIT){
				config[CENTER + 1][eastTip - FOUR] = Type.FOOD_WATER;
			}
		}
	}
	
}
