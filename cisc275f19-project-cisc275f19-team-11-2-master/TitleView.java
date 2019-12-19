import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import java.util.ArrayList;
import java.util.Random;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.Node;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */

class TitleView extends View{
   
    Image background;
    GraphicsContext gc;
	StackPane textPane;
    VBox vbox;
    boolean isFinished = false;
   
    /**
     * Class constructor
     * @param theStage the current stage (state of the view)
     */
    public TitleView(Stage theStage){
        this.theStage=theStage;
        //theStage.setFullScreen(true);
        root = new Group();
        theScene = new Scene(root);
        this.theStage.setScene(theScene);
        canvas = new Canvas(canvasWidth, canvasHeight);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		importImages();
		draw();
    }
    /**
     * Function for drawing titleView
     */
    public void draw(){
        gc.clearRect(0, 0, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
		textPane = new StackPane();	
        textPane.setPrefSize(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
        Text title = new Text("Title Screen");
        title.setFont(new Font("Verdana", 40));
       // textPane.getChildren().add(title);
        //textPane.setAlignment(title, Pos.TOP_CENTER);
        root.getChildren().add(textPane);
        vbox = new VBox(5);
        vbox.setAlignment(Pos.CENTER);
        Button next2 = new Button("Play");
        next2.setMaxSize(200,400);
        next2.setStyle("-fx-background-color: #00ff00; ");
        next2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
			public void handle(ActionEvent e){
                isFinished=true;
            }
        });
        vbox.getChildren().add(next2);
		textPane.getChildren().add(vbox);
        gc.drawImage(background, 0, 0, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
    }
    public void update(){
    }
    /**
     * Function for getting image of titlescreen
     */
    private void importImages(){
        background = createImage("images/TitleScreen.png");
    }
    /**
     * Function for creating image
     * @param path string containing the path to the image file
     * @return the image created from the path
     */
    private Image createImage(String path){
		return new Image(path);
	}
    /**
     * Function for going to the next view
     * @return whether this view is finished, should move to the next
     */
	public Boolean getIsFinished(){
		return isFinished;
    }
	/**
	 * Function for returning the scene
	 * @return the scene
	 */
    public Scene getScene(){
        //theStage.initStyle(StageStyle.TRANSPARENT);
        
        
        return theScene;
    }
}
