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
	public Module(int code, int xcoord, int ycoord, int turns, int status) throws IndexOutOfBoundsException {
		
		// Checks that each parameter is within its range
		if (code < 1 || code > 190
			|| turns < 0 || turns > 2
			|| status < 0 || status > 2)
			throw new IndexOutOfBoundsException("Parameter out of bounds in Module constructor");
		
		this.code = code;
		this.xCoord = xcoord;
		this.yCoord = ycoord;
		this.turns = turns;
		this.status = status;
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
	
	public int getStatus() {
		return status;
	}
	
	// Instance fields
	private int code,
				xCoord,
				yCoord,
				turns,
				status;
	
	// type?
}
