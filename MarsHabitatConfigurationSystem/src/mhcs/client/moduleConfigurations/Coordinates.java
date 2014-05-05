package mhcs.client.moduleConfigurations;

/**
 * A small class that is simply a collection of two 
 * integers: the X coordinate and the Y coordinate.
 * @author Jeremiah Wilhelmy
 */
public class Coordinates{
	int xCoord;
	int yCoord;
	
	public Coordinates(final int x, final int y){
		this.xCoord = x;
		this.yCoord = y;
	}
	
	public int getX() {
		return this.xCoord;
	}
	
	public int getY() {
		return this.yCoord;
	}
}	
