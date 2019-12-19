import javafx.scene.image.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
abstract class View implements java.io.Serializable{
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    Stage theStage;
    Group root;
    Scene theScene;
	Canvas canvas;
	int canvasWidth = (int)primaryScreenBounds.getWidth();
    int canvasHeight = (int)primaryScreenBounds.getHeight();
    GraphicsContext gc;
    private void importImages() {}

    /**
     * Function for creating image
     * @param image_file the name of the file used for image
     * @return the String url as an image
     */
    private Image createImage(String image_file) {
        Image img = new Image(image_file);
        return img;   	
    }
    /**
     * Function for getting width of view
	@return returns the width of the canvas
	*/
    public int getWidth() {
		return canvasWidth;
	}

    /**
	 * Function for getting height of view
	@return the height of the canvas
	*/
	public int getHeight() {
		return canvasHeight;
	}
}