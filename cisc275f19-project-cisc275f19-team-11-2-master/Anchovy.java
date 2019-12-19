/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
class Anchovy extends Animal{
	/**
	 * Anchovy Constructor
	@param width a variable which should represent the width of the canvas 
	@param height a variable which should represent the height of the canvas
	@return a new Bass object
	*/
	Anchovy(int width, int height){
		super(width, height);
		this.name = "Anchovy";
		this.setImageKey("Anchovy");
		this.setImgWidth(90);
		this.setImgHeight(45);
	}
}
