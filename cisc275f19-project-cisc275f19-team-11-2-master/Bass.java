/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
class Bass extends Animal{
	/**
	 * Constructor for Bass
	@param width a variable which should represent the width of the canvas 
	@param height a variable which should represent the height of the canvas
	@return a new Bass object
	*/
	Bass(int width, int height){
		super(width, height);
		this.name = "Bass";
		this.setImageKey("bass");
		this.setImgWidth(90);
		this.setImgHeight(44);
		this.getPrey().add("anchovy");
		this.getPrey().add("Clam");
		this.getPrey().add("Crab");
	}

	/**
	 * Secondary constructor for bass for the user to control
	 @param width a variable which should represent the width of the canvas 
	 @param height a variable which should represent the height of the canvas
	 @param user the user @see View/Model
	 */
	Bass(int width, int height, int user){
		super(width, height);
		this.name = "Bass";
		this.setXSpeed(7);
		this.setYSpeed(7);
		this.setImageKey("userBass");
		this.setImgWidth(100);
		this.setImgHeight(64);
		this.setX(20);
		this.setY(50);
		this.getPrey().add("anchovy");
		this.getPrey().add("Clam");
		this.getPrey().add("Crab");
	}
}
