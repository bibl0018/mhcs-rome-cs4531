package mhcs.client.module;

/**
 * Representation of a Module in the Mars Habitat Configuation System. 
 * @author Ryan Stowell
 */
public class Module {

	public static final String UNDAMAGED = "UNDAMAGED";
	public static final String REPAIRABLE = "REPAIRABLE";
	public static final String DAMAGED = "DAMAGED";
	public static final int SIZE = 190;
	public static final int PLAIN_UPPER = 40;
	public static final int DORM_LOWER = 60;
	public static final int DORM_UPPER = 80;
	public static final int SAN_LOWER = 90;
	public static final int SAN_UPPER = 100;
	public static final int FOOD_LOWER = 110;
	public static final int FOOD_UPPER = 120;
	public static final int GYM_LOWER = 130;
	public static final int GYM_UPPER = 134;
	public static final int CANTEEN_LOWER = 140;
	public static final int CANTEEN_UPPER = 144;
	public static final int POWER_LOWER = 150;
	public static final int POWER_UPPER = 154;
	public static final int CONTROL_LOWER = 160;
	public static final int CONTROL_UPPER = 164;
	public static final int AIRLOCK_LOWER = 170;
	public static final int AIRLOCK_UPPER = 174;
	public static final int MEDICAL_LOWER = 180;
	public static final int MEDICAL_UPPER = 184;
	
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
		if (newCode < 1 || newCode > SIZE) {
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
		if (this.code > 0 && this.code <= PLAIN_UPPER) {
			this.type = Type.PLAIN;
		} else if (this.code > DORM_LOWER && this.code <= DORM_UPPER) {
			this.type = Type.DORMITORY;
		} else if (this.code > SAN_LOWER && this.code <= SAN_UPPER) {
			this.type = Type.SANITATION;
		} else if (this.code > FOOD_LOWER && this.code <= FOOD_UPPER) {
			this.type = Type.FOOD_WATER;
		} else if (this.code > GYM_LOWER && this.code <= GYM_UPPER) {
			this.type = Type.GYM_RELAXATION;
		} else if (this.code > CANTEEN_LOWER && this.code <= CANTEEN_UPPER) {
			this.type = Type.CANTEEN;
		} else if (this.code > POWER_LOWER && this.code <= POWER_UPPER) {
			this.type = Type.POWER;
		} else if (this.code > CONTROL_LOWER && this.code <= CONTROL_UPPER) {
			this.type = Type.CONTROL;
		} else if (this.code > AIRLOCK_LOWER && this.code <= AIRLOCK_UPPER) {
			this.type = Type.AIRLOCK;
		} else if (this.code > MEDICAL_LOWER && this.code <= MEDICAL_UPPER) {
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
