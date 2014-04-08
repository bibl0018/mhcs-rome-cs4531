package mhcs.client;

/** 
 * Representation of a Module in the Mars Habitat Configuation System. 
 * @author Ryan Stowell
 */
public class Module {

	/**
	 * Default constructor.
	 * @param code The Module's number;   1-190
	 * @param xcoord The X coordinate;   range TBD
	 * @param ycoord The Y coordinate;   range TBD
	 * @param turns The number of turns required for the Module to be upright;   0-2
	 * @param status The status of the Module;   0-No Repairs Needed, 1-Repairs Needed, 2-Not Repairable
	 * @throws IndexOutOfBoundsException if any parameters is not within the ranges stated above
	 */
	public Module(int code, int xcoord, int ycoord, int turns, Status status) throws IndexOutOfBoundsException {
		
		// Checks that each parameter is within its range
		if (code < 1 || code > 190
			|| turns < 0 || turns > 2)
			throw new IndexOutOfBoundsException("Parameter out of bounds in Module constructor");
	 
		this.code = code;
		this.xCoord = xcoord;
		this.yCoord = ycoord;
		this.turns = turns;
		this.status = status;
		
		// Sets the type based on the code
		if (code > 0 && code <= 40) {
			type = Type.PLAIN;
		} else if (code > 60 && code <= 80) {
			type = Type.DORMITORY;
		} else if (code > 90 && code <= 100) {
			type = Type.SANITATION;
		} else if (code > 110 && code <= 120) {
			type = Type.FOOD_WATER;
		} else if (code > 130 && code <= 134) {
			type = Type.GYM_RELAXATION;
		} else if (code > 140 && code <= 144) {
			type = Type.CANTEEN;
		} else if (code > 150 && code <= 154) {
			type = Type.POWER;
		} else if (code > 160 && code <= 164) {
			type = Type.CONTROL;
		} else if (code > 170 && code <= 174) {
			type = Type.AIRLOCK;
		} else if (code > 180 && code <= 184) {
			type = Type.MEDICAL;
		} else {
			throw new IndexOutOfBoundsException("Type not yet determined");
		}
	}
	
	public int getCode() {
		return code;
	}
	
	public int getXCoord() {
		return xCoord;
	}
	
	public int getYCoord() {
		return yCoord;
	}
	
	public int getTurns() {
		return turns;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public Type getType() {
		return type;
	}
	
	// Instance fields
	private int code,
				xCoord,
				yCoord,
				turns;
	private Status status;
	private Type type;
	
	public enum Status {
		UNDAMAGED,
		DAMAGED,
		UNUSABLE
	};
	
	public enum Type {
		PLAIN,
		DORMITORY,
		SANITATION,
		FOOD_WATER,
		GYM_RELAXATION,
		CANTEEN,
		POWER,
		CONTROL,
		AIRLOCK,
		MEDICAL
	};
	
	//test
}
