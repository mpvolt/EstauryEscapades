/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
public class Bird extends Animal{
	/**
	 * Constructor for bird class
	 * @param width the width of the bird
	 * @param height the height of the bird
	 */
	Bird(int width, int height){
		super(width, height);
		this.setImageKey("bird");
		this.getPrey().add("anchovy");
		this.getPrey().add("Bass");
		this.getPrey().add("Crab");
		this.setImgWidth(50);
		this.setImgHeight(50);
		this.setY(0);
		this.setX(200);
	}

	/**
	 * Function for Bird movement, overrides Animal move function @see Animal
	 */
	@Override
	void move(){
		determineDirection();
		if (stopped){
			return;
		}
		switch(dir){
			case EAST:
				this.setX(this.getX() + this.getXSpeed());
				break;
			case WEST:
				this.setX(this.getX() - this.getXSpeed());
				break;
			case NORTH:
				this.setY(this.getY() - this.getYSpeed());
				break;
		} 
		if(this.dive == true)
		{
			dive();
			this.dive = false;
		}
		 
	}

	/**
	 * Determine direction of the bird
	 */
	@Override
	public boolean setDirection(Direction dir){
		if ((dir == Direction.EAST) || (dir == Direction.WEST) || (dir == Direction.NORTH)){
			super.setDirection(dir);
			return true;
		}
		return false;
	}

	/**
	 * Function for making bird go underwater
	 */
	@Override
	void determineDirection(){
		if (this.getX() >= canvasWidth - imgWidth){
			setDirection(Direction.WEST);
		}
		else if (this.getX() <= 0){
			setDirection(Direction.EAST);
		}
		
	}
	
	/**
	 * Function for making bird go underwater
	 */
	void dive(){
		this.setY(this.getY() + 50);
	}
}
