/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
class Clam extends Animal{
	/**
	 * Constructor for Clam
	@param width a variable which should represent the width of the canvas 
	@param height a variable which should represent the height of the canvas
	@return a new Bass object
	*/
	Clam(int width, int height){
		super(width, height);
		this.name = "Clam";
        this.setImageKey("Clam");
		this.setImgWidth(90);
        this.setImgHeight(44);
        this.setYSpeed(0);
        this.setXSpeed(0);
		this.setY((int)(height*0.9));
	}
}
