import javafx.scene.image.Image;
import java.util.Random;
import java.util.HashSet;
/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
public class Animal implements java.io.Serializable{
	private int xPos;
	private int yPos;
	private int xSpeed = 5;
	private int ySpeed = 5;
	protected Direction dir;
	public boolean photoTaken=false;
	protected int imgWidth;
	private int imgHeight;
	public int canvasWidth;
	public int canvasHeight;
	protected boolean stopped = false;
	public String name;
	private String comments;
	public String imgKey;
	public int health;
	boolean capture;
	private HashSet<String> prey = new HashSet<String>();
	int breath = 500;
	boolean dive = false;
	public boolean eaten=false;


	/**
	 * Constructor for Animal
	@param canWidth width of the canvas which the animals will be drawn on
	@param canHeight height of the canvas which the animals will be drawn on
	@return a new Animal object
	*/
	Animal(int canWidth, int canHeight){
		xPos = (int)((Math.random() * ((canWidth - 10) + 1)) + 10);
		yPos = (int)((Math.random() * ((canHeight - 10) + 1)) + 10); 
		//dir = Direction.values[(int)((Math.random() * ((Direction.length - 1 - 0) + 1)) + 0)];
		randomizeDirection();
		canvasWidth = canWidth;
		canvasHeight = canHeight;
		name = "TEMP";
		comments = "COMMENTS";
		
		health=500;
	}
	
	/**
	 * Function for getting the name of the animal
	@return a String representing the Animal's name
	*/
	public String toString(){
		return name;
	}
	
	/**
	 * Function for getting the animal's x coordinate
	@return an int representing the Animal's current X position
	*/
	public int getX(){
		return xPos;
	}
	
	/**
	 * Function for setting the Animals x coordinate
	 * @param x the new x coordinate
	 */
	public void setX(int x){
		xPos = x;
	}

	/**
	* Function for setting the Animals y coordinate
	@return an int representing the Animal's current Y position
	*/
	public int getY(){
		return yPos;
	}
	
	/**
	 * Function for setting the Animals y coordinate
	 * @param y the new y coordinate
	 */
	public void setY(int y){
		yPos = y;
	}

	/**
	 * Function for getting the animals current direction
	@return a Direction variable representing the current direction the Animal is moving in
	*/
	public Direction getDirection(){
		return dir;
	}
	
	/**
	 * Function for setting the animal's direction
	@param dir a Direction variable which will override the Animal's current dir attribute
	*/
	protected boolean setDirection(Direction dir){
		this.dir = dir;
		return true;
	}
	
	/**
	 * Function for getting animal image width
	@return an int representing the width of the Image used to represent a given Animal
	*/
	int getImgWidth(){
		return imgWidth;
	}
	
	/**
	 * Function for setting the animals image width
	@param imgWidth the new imgWidth for a given Animal
	*/
	void setImgWidth(int imgWidth){
		this.imgWidth = imgWidth;
	}
	
	/**
	 * Function for getting Image Height
	@return an int representing the height of the Image used to represent a given Animal
	*/
	int getImgHeight(){
		return imgHeight;
	}
	
	/**
	 * Function for getting image width
	 * @param imgHeight the new imgHeight for a given Animal
	*/
	public void setImgHeight(int imgHeight){
		this.imgHeight = imgHeight;
	}
	
	/**
	 * Function for getting animals speed on the X axis
	 * @return XSpeed the speed of the animals along the X axis
	*/
	public int getXSpeed(){
		return xSpeed;
	}

	/**
	 * Function for setting animals speed on the X axis
	 * @param speed the speed of the animals
	 * @return XSpeed the speed of the animals along the X axis
	*/
	public void setXSpeed(int speed){
		xSpeed = speed;
	}

	/**Function for getting animal speed on the Y axis
	 * @return YSpeed the speed of the animals along the X axis
	*/
	public int getYSpeed(){
		return ySpeed;
	}

	/**
	 * Function for setting animal speed on the Y axis
	 * @param speed the speed of the animals
	*/
	public void setYSpeed(int speed){
		ySpeed = speed;
	}

	/**Function that determines whether animal is stopped
	 * @return XSpeed the speed of the animals along the X axis
	*/
	public boolean getStopped(){
		return stopped;
	}

	/**
	 * Function for setting the animal's stopped boolean
	 * @param stopped the boolean that determines whether animals are stopped
	*/
	public void setStopped(boolean stopped){
		this.stopped = stopped;
	}

	/**
	 * Function that gets the animals list of prey
	@return a HashSet which contains all the prey for a given animal. They keys are the class names of the prey and if a class is contained in the HashSet then it is prey
	*/
	HashSet<String> getPrey(){
		return prey;
	}

	/**
	 * Function that gets the image key of the animal
	@return returns a String representing the key needed to access a given Animal's image in the HashTable found in the view
	*/
	String getImageKey(){
		return imgKey;
	}

	/**
	 * Function for setting the image key of the animal
	@param imgKey the new imgKey for a given Animal, used to access a given Animal's image
	*/ 
	void setImageKey(String imgKey){
		this.imgKey = imgKey;
	}

	/**
	* Determines whether a given Animal has hit the edge of the canvas, and if so adjusts it's Direction to prevent it from moving off the screen.
	*/
	void determineDirection(){
		if ((xPos >= canvasWidth - imgWidth) && (yPos >= canvasHeight - imgHeight)){
			setDirection(Direction.NORTHWEST);
		}
		else if ((xPos >= canvasWidth - imgWidth) && (yPos <= 0)){
			setDirection(Direction.SOUTHWEST);
		}
		else if ((xPos <= 0) && (yPos >= canvasHeight - imgHeight)){
			setDirection(Direction.NORTHEAST);
		}
		else if ((xPos <= 0) && (yPos <= 0)){
			setDirection(Direction.SOUTHEAST); 
		}
		else if (xPos >= canvasWidth - imgWidth){
			setDirection(Direction.WEST);
		}
		else if (xPos <= 0){
			setDirection(Direction.EAST);
		}
		else if (yPos >= canvasHeight - imgHeight){
			setDirection(Direction.NORTH);		
		}
		else if (yPos <= canvasHeight/5&&name!="Bird"){
			setDirection(Direction.SOUTH);
		}
		else if (yPos <= 0){
			setDirection(Direction.SOUTH);
		}
	}
	
	/**
	* Sets a given Animal's Direction to a random possible Direction value
	*/
	void randomizeDirection(){
		Random generator = new Random();
		setDirection(Direction.values[generator.nextInt(8)]);
	}

	/**
	* This method changes a given Animal's current position based off it's current direction and decreases its health;
	*/
	void move(){
		if(!name.equals("Scuba")){
			health--;
		}
		double scrollspeed = 3;
		
		determineDirection();
		if (stopped){
			return;
		}
		switch(dir){
			case NORTH:
				yPos -= ySpeed;
				break;
			case SOUTH:
				yPos += ySpeed;
				break;
			case WEST:
				xPos -= xSpeed;
				break;
			case NORTHEAST:
				yPos -= ySpeed;
				xPos += xSpeed;
				break;
			case NORTHWEST:
				yPos -= ySpeed;
				xPos -= xSpeed;
				break;
			case SOUTHEAST:
				yPos += ySpeed;
				xPos += xSpeed;
				break;
			case SOUTHWEST:
				yPos += ySpeed;
				xPos -= xSpeed;
				break;
			case EAST:
				xPos += xSpeed;
				break;
		}
	}
	/**
	* This method compares whether Animal a is overlapping with the current Animal
	@param a an Animal whose distance will be compared to the Animal who is making this method call
	*/
	public boolean withinDistance(Animal a) {
		if((a.getX() > this.getX()) && (a.getX() < (this.getX() + this.getImgWidth()))){
			if((a.getY() > this.getY()) && (a.getY() < (this.getY() + this.getImgHeight()))){
				return true;
			}
		}
		return false;
	}
}
