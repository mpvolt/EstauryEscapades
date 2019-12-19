/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
class Crab extends Animal{
	/**
	 * Constructor for Crab
	@param width a variable which should represent the width of the canvas 
	@param height a variable which should represent the height of the canvas
	@return a new Bass object
	*/
	Crab(int width, int height){
		super(width, height);
		this.name = "Crab";
		this.setImageKey("Crab");
		this.setImgWidth(90);
		this.setImgHeight(44);
		this.setYSpeed(0);
		this.setY((int)(height*0.9));
	}
}
