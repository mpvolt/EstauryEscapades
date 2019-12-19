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
class AnimalTutView extends View{
	GameModel model;
	GameView view;
	ImageView newPic;
	Scene theScene;
	Image background;
	GraphicsContext gc;
	Hashtable<String, Image> images = new Hashtable<String, Image>();
	StackPane textPane;
	ArrayList<Image> animalPics = new ArrayList<Image>();
	VBox v;
	boolean cancel = false;
	HBox imagesBox;
	ArrayList<String> instructions;
	int score;
	int XPos = 200;
	int YPos = 600;
	boolean isFinished = false;
	Scuba usr = new Scuba(canvasWidth, canvasHeight);
	Octopus usrs = new Octopus(canvasWidth, canvasHeight);	
	ImageView animals[] = new ImageView[2];

	/**
	 * Constructor for tutorial
	 * @param theStage the current stage
	 * @param usr the usr animal
	 */
	public AnimalTutView(Stage theStage, Animal usr){
		this.theStage = theStage;
		root = new Group();
		theScene = new Scene(root);
		this.theStage.setScene(theScene);
		canvas = new Canvas(canvasWidth, canvasHeight);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		
		importImages();
		usrs.setImageKey("octopus_user");
		animalPics.add(images.get(usr.getImageKey()));
		animalPics.add(images.get("Anchovy"));
		animals[0]=(new ImageView(images.get(usr.getImageKey())));
		animals[1]=(new ImageView(images.get("Anchovy")));
		draw();
		drawInteractive();
			
	}
	/**
	 * Function for importing images to be drawn
	 */
	private void importImages() {
        String img_file_base = "images/";
        String ext = ".png";
		images.put("background", createImage(img_file_base + "underwater" + ext));
		images.put("scuba", createImage(img_file_base + "drop-the-bass/scuba" + ".gif"));
		images.put("octopus", createImage(img_file_base + "octopus" + ext));
		images.put("octopus_user", createImage(img_file_base + "octopus" + ".gif"));
		images.put("bass", createImage(img_file_base + "bass" + ext));
		images.put("bird", createImage(img_file_base + "bird" + ".gif"));
		images.put("Anchovy", createImage(img_file_base + "anchovy" + ext));
		images.put("Crab", createImage(img_file_base + "crab" + ".gif"));
		images.put("HorseShoeCrab", createImage(img_file_base + "horseshoe crab" + ".gif"));
		images.put("Clam", createImage(img_file_base + "clams" + ".gif"));
	}

	/**
	 * Function for drawing AnimalTutView
	 */
	public void draw(){
		gc.clearRect(0, 0, canvasWidth, canvasHeight);
		textPane = new StackPane();	
		textPane.setPrefSize(1000, 1000);
		
		v = new VBox(9);
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
		
		Text thirdInstruction = new Text("Press the SPACEBAR to capture an animal");
		thirdInstruction.setFont(new Font("Verdana", 40));
		thirdInstruction.setFill(Color.RED);
		v.getChildren().add(thirdInstruction);
		
		Text fourthInstruction = new Text("Some examples of local sealife:");
		fourthInstruction.setFont(new Font("Verdana", 40));
		fourthInstruction.setFill(Color.RED);
		v.getChildren().add(fourthInstruction);
		
		imagesBox= new HBox(5);
		imagesBox.setAlignment(Pos.CENTER);
		imagesBox.getChildren().addAll(animals);
		
		v.getChildren().add(imagesBox);
		
		Text fifthInstruction = new Text("Try moving the scuba diver to capture the octopus");
		fifthInstruction.setFont(new Font("Verdana", 40));
		fifthInstruction.setFill(Color.RED);
		v.getChildren().add(fifthInstruction);
		
		Button next2 = new Button("Play");
        next2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
			public void handle(ActionEvent e){
            isFinished = true;
            }
        });
        v.getChildren().add(next2);
        v.setAlignment(Pos.TOP_CENTER);
		root.getChildren().add(v);
	}

	/**
	 * Function for drawing the interactive tutorial
	 */
	public void drawInteractive(){
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

	private Image createImage(String path){
		return new Image(path);
	}

	public Boolean getIsFinished(){
		return isFinished;
	}
}
