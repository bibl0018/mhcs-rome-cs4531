package mhcs.client.module;

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
	 * @param status The status of the Module
	 * @throws IndexOutOfBoundsException if any parameters is not within the ranges stated above
	 */
	public Module(final int code, final int xcoord, final int ycoord, final int turns, final String status) throws IndexOutOfBoundsException {

		// Checks that each parameter is within its range
		if (code < 1 || code > 190) {
			throw new IndexOutOfBoundsException("Invalid module code");
		} else if (turns < 0 || turns > 2) {
			throw new IndexOutOfBoundsException("Invalid turns value");
		}

		this.code = code;
		this.xCoord = xcoord;
		this.yCoord = ycoord;
		this.turns = turns;
		this.status = status;

		// Sets the type based on the code
		if (code > 0 && code <= 40) {
			this.type = Type.PLAIN;
		} else if (code > 60 && code <= 80) {
			this.type = Type.DORMITORY;
		} else if (code > 90 && code <= 100) {
			this.type = Type.SANITATION;
		} else if (code > 110 && code <= 120) {
			this.type = Type.FOOD_WATER;
		} else if (code > 130 && code <= 134) {
			this.type = Type.GYM_RELAXATION;
		} else if (code > 140 && code <= 144) {
			this.type = Type.CANTEEN;
		} else if (code > 150 && code <= 154) {
			this.type = Type.POWER;
		} else if (code > 160 && code <= 164) {
			this.type = Type.CONTROL;
		} else if (code > 170 && code <= 174) {
			this.type = Type.AIRLOCK;
		} else if (code > 180 && code <= 184) {
			this.type = Type.MEDICAL;
		} else {
			throw new IndexOutOfBoundsException("Invalid module code; Type not yet determined");
		}
	}

	public int getCode() {
		return this.code;
	}

	public int getXCoord() {
		return this.xCoord;
	}

	public int getYCoord() {
		return this.yCoord;
	}

	public int getTurns() {
		return this.turns;
	}

	public String getStatus() {
		return this.status;
	}

	public Type getType() {
		return this.type;
	}

	// Instance fields

	public static String UNDAMAGED = "UNDAMAGED";
	public static String DAMAGED = "DAMAGED";
	public static String UNCERTAIN = "UNCERTAIN";

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
	private int code,
	xCoord,
	yCoord,
	turns;
	private String status;
	private Type type;
}
