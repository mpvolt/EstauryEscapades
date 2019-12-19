/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
class Octopus extends Animal {
	/**
	 * Constructor for Octopus
	@param width the width of the canvas the Octopus will be drawn on
	@param height the height of the canvas the Octopus will be drawn on
	@return a new Octopus object
	*/
	Octopus(int width, int height){
		super(width, height);
		this.setImageKey("octopus");
		this.setImgWidth(68);
		this.setImgHeight(68);
		this.getPrey().add("Bass");
		this.getPrey().add("anchovy");
		this.getPrey().add("Crab");
	}
}
