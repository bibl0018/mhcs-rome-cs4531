package mhcs.client.module;

/**
 * Representation of a Module in the Mars Habitat Configuation System. 
 * @author Ryan Stowell
 */
public class Module {

	public static String UNDAMAGED = "UNDAMAGED";
	public static String DAMAGED = "DAMAGED";
	public static String UNCERTAIN = "UNCERTAIN";
	
	private int code;
	private int xCoord;
	private int yCoord;
	private int turns;
	private String status;
	private Type type;
	
	/**
	 * Default constructor.
	 * @param code The Module's number;   1-190
	 * @param xcoord The X coordinate;   range TBD
	 * @param ycoord The Y coordinate;   range TBD
	 * @param turns The number of turns required for the Module to be upright;   0-2
	 * @param status The status of the Module
	 * @throws IndexOutOfBoundsException if any parameters is not within the ranges stated above
	 */
	public Module(final int newCode, final int xcoord, final int ycoord, final int newTurns, final String newStatus) {

		// Checks that each parameter is within its range
		if (newCode < 1 || newCode > 190) {
			throw new IndexOutOfBoundsException("Invalid module code");
		} else if (newTurns < 0 || newTurns > 2) {
			throw new IndexOutOfBoundsException("Invalid turns value");
		}

		this.code = newCode;
		this.xCoord = xcoord;
		this.yCoord = ycoord;
		this.turns = newTurns;
		this.status = newStatus;

		// Sets the type based on the code
		if (this.code > 0 && this.code <= 40) {
			this.type = Type.PLAIN;
		} else if (this.code > 60 && this.code <= 80) {
			this.type = Type.DORMITORY;
		} else if (this.code > 90 && this.code <= 100) {
			this.type = Type.SANITATION;
		} else if (this.code > 110 && this.code <= 120) {
			this.type = Type.FOOD_WATER;
		} else if (this.code > 130 && this.code <= 134) {
			this.type = Type.GYM_RELAXATION;
		} else if (this.code > 140 && this.code <= 144) {
			this.type = Type.CANTEEN;
		} else if (this.code > 150 && this.code <= 154) {
			this.type = Type.POWER;
		} else if (this.code > 160 && this.code <= 164) {
			this.type = Type.CONTROL;
		} else if (this.code > 170 && this.code <= 174) {
			this.type = Type.AIRLOCK;
		} else if (this.code > 180 && this.code <= 184) {
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
}
