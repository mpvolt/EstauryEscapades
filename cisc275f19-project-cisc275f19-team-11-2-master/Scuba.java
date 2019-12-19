import javafx.scene.image.Image;
/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
public class Scuba extends Animal{
	/**
	 * Constructor for class Scuba
	@param canWidth the width of the canvas the Scuba will be drawn on
	@param canHeight the height of the canvas the Scuba will be drawn on
	@return a new Scuba object
	*/
    public Scuba(int canWidth, int canHeight) {
		super(canWidth, canHeight);
		this.name = "Scuba";
		this.setImageKey("scuba");
		this.setImgWidth(125);
		this.setImgHeight(75);
		this.setX(50);
		this.setY(canHeight / 5);
		this.setYSpeed(5);
		this.setXSpeed(5);
		this.capture = false;
	}
}

