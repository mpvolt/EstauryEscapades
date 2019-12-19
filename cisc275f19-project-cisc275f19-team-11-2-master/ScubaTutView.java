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
import javafx.scene.layout.HBox;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;

import javafx.scene.image.ImageView;

/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
class ScubaTutView extends View{
	
	GameModel model;
	GameView view;
	
	ImageView newPic;
	
	Image background;
	GraphicsContext gc;
	StackPane textPane;
	ArrayList<Image> animalPics = new ArrayList<Image>();
	Image aba;
	VBox v;
	boolean cancel = false;
	HBox images;
	ArrayList<String> instructions;
	int score;
	int XPos = 200;
	int YPos = 600;
	boolean isFinished = false;
	Scuba usr = new Scuba(canvasWidth, canvasHeight);
	Octopus usrs = new Octopus(canvasWidth, canvasHeight);
	
	ImageView animals[] = new ImageView[4];
	/**
	 * Class constructor
	 * @param theStage the current stage
	 */
	public ScubaTutView(Stage theStage){
		this.theStage = theStage;
		root = new Group();
		theScene = new Scene(root);
		this.theStage.setScene(theScene);
		canvas = new Canvas(canvasWidth, canvasHeight);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		usrs.setImageKey("octopus_user");
		importImages();
		animalPics.add(createImage("images/drop-the-bass/scuba.gif"));
		animalPics.add(createImage("images/octopus.gif"));
		draw();
		drawInteractive();
			
	}
	/**
	 * Function for drawing the scubaTutView
	 */
	public void draw(){
		gc.clearRect(0, 0, canvasWidth, canvasHeight);
		textPane = new StackPane();	
		textPane.setPrefSize(canvasWidth, canvasHeight);
		
		
		v = new VBox(9);
		//v.setLayoutX(canvasWidth);
		Text title = new Text("Scuba Instructions");
		title.setFont(new Font("Verdana", 80));
		title.setFill(Color.RED);
		v.getChildren().add(title);

		Text firstInstruction = new Text("You are about to play an estuary game. Yay!!");
		firstInstruction.setFont(new Font("Verdana", 40));
		firstInstruction.setFill(Color.RED);
		v.getChildren().add(firstInstruction);

		
		Text secondInstruction = new Text("Your mission, capture  local sealife for research");
		secondInstruction.setFont(new Font("Verdana", 40));
		secondInstruction.setFill(Color.RED);
		v.getChildren().add(secondInstruction);
		
		Text thirdInstruction = new Text("Press the SPACEBAR to capture an animal, when you are close");
		thirdInstruction.setFont(new Font("Verdana", 60));
		thirdInstruction.setFill(Color.RED);
		v.getChildren().add(thirdInstruction);
		
		Text fourthInstruction = new Text("Some examples of local sealife:");
		fourthInstruction.setFont(new Font("Verdana", 40));
		fourthInstruction.setFill(Color.RED);
		v.getChildren().add(fourthInstruction);
		
		images = new HBox(5);
		images.setAlignment(Pos.CENTER);
		images.getChildren().addAll(animals);
		
		v.getChildren().add(images);
		
		Text fifthInstruction = new Text("Try moving the scuba diver with the arrows to capture the octopus");
		fifthInstruction.setFont(new Font("Verdana", 40));
		fifthInstruction.setFill(Color.RED);
		v.getChildren().add(fifthInstruction);
		
		Button next2 = new Button("Play");
        next2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
			public void handle(ActionEvent e){
            isFinished=true;
            }
        });
        
        v.getChildren().add(next2);
		
        v.setAlignment(Pos.TOP_CENTER);
		root.getChildren().add(v);
		
		
		

	}

	/**
	 * Function for drawing the interactive tutorial
	 */
	public void drawInteractive()
	{
		gc.clearRect(0, 0, canvasWidth, canvasHeight);
		gc.drawImage(background, 0, 0,canvasWidth, canvasHeight);
		for(Image a: animalPics)
		{
			if(a.equals(animalPics.get(0)))
			
				gc.drawImage(animalPics.get(0), XPos, YPos);
			
			else if(a.equals(animalPics.get(1)) && cancel == false)
				gc.drawImage(animalPics.get(1), 500, 600);
			
		}
		theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			/**
			* This method handles what happens if any key is pressed
			@param event the key being pressed
			*/
			public void handle(KeyEvent event){
				switch(event.getCode()){
					case UP:
						YPos -= 10;
						break;
					case DOWN:
						YPos += 10;
						break;
					case SPACE:
						cancel = true;
						break;
					case RIGHT:
						XPos += 10;
						break;
					case LEFT:
						XPos -= 10;
					case DIGIT2:
					default:
						break;
				}
			}
		});
		
	}
	

	/**
	 * Function for importing images
	 */
	private void importImages(){
		background = createImage("images/QuizBackground.png");
		animals[0] = new ImageView(createImage("images/bass.png"));
		animals[1] = new ImageView(createImage("images/octopus.png"));
		animals[2] = new ImageView(createImage("images/bird.gif"));
		animals[3] = new ImageView(createImage("images/horseshoe crab.gif"));
		
		for (int i = 0; i < 4; i++){
			animals[i].setFitHeight(150);
			animals[i].setFitWidth(150);
		}
		
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

	
	

}
