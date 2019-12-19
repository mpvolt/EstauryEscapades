/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */

public enum Direction{
	NORTHEAST(-45, false), SOUTHEAST(45, false), NORTHWEST(45, true), SOUTHWEST(-45, true), NORTH(-90, false), SOUTH(90, false), EAST(0, false), WEST(0, true);
	public static Direction values[] = Direction.values();
	public static int length = values.length;
	private int rotation;
	private boolean flipped;

	/**
	 * Constructor for Direction
	@param rotation the degree of rotation for this given Direction
	@param flipped a boolean representing whether an image needs to be flipped for this Direction
	@return a new Direction
	*/
	Direction(int rotation, boolean flipped){
		this.rotation = rotation;
		this.flipped = flipped;
	}

	/**
	 * Function for getting angle of rotation
	@return an int representing the degree of rotation for this given direction
	*/
	int getRotationAngle(){
		return rotation;
	}

	/**
	 * Function for whether an image should be flipped
	@return a boolean representing whether an image needs to be flipped at this given Direction
	*/
	boolean getFlipped(){
		return flipped;
	}
}
