/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
class HorseShoeCrab extends Animal{
	/**
	 * Constructor for HorseShoeCrab
	@param width a variable which should represent the width of the canvas 
	@param height a variable which should represent the height of the canvas
	@return a new Bass object
	*/
	HorseShoeCrab(int width, int height){
		super(width, height);
		this.name = "HorseShoeCrab";
		this.setImageKey("HorseShoeCrab");
		this.setImgWidth(90);
		this.setImgHeight(45);
		this.setYSpeed(0);
		this.setY((int)(height*0.9));
	}
}
