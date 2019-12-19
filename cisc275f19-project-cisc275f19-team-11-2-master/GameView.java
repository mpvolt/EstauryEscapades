import javafx.application.Application;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Rotate;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import java.awt.*;
import java.awt.event.*;
import javafx.scene.shape.Rectangle;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.Node;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.util.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.geometry.Pos;


/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
public class GameView extends View {
	
	static int accuracy = 20;
	
	int gameNum;

	Animal usr;
    int XPos = 10;
	boolean pressed = false;
	MakeSound sound = new MakeSound();
	Hashtable<String, Image> images = new Hashtable<String, Image>();
	Hashtable<Animal, ImageView> animals = new Hashtable<Animal, ImageView>();
	StackPane timer;
	Text scoreboard;
	int sideScroll;
	Image background;
	ArrayList<Animal> listAnimals;

	/**
	 * Constructor for gameview
	@param theStage theStage which will be used for the games involving the Game Model and Views
	@param usr the Animal found in the GameModel which is to be controlled by the player
	*/
	public GameView(Stage theStage, Animal usr, ArrayList<Animal> listAnimals) {
		sideScroll=0;
		this.listAnimals = listAnimals;
        this.theStage = theStage;
		this.theStage.setTitle("Bass");
		this.usr = usr;
		root = new Group();
		//Rectangle rect1 = new Rectangle(usr.nx, 10, usr.health, 20);
		//rect1.setFill(Color.GREEN);
		//root.getChildren().addAll(rect1);
		theScene = new Scene(root);
		this.theStage.setScene(theScene);
		
		theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			@Override
			/**
			* This method handles what happens if any key is pressed
			@param event the key being pressed
			*/
			public void handle(KeyEvent event){
				switch(event.getCode()){
					case UP:
						usr.setDirection(Direction.NORTH);
						break;
					case DOWN:
						usr.setDirection(Direction.SOUTH);
						usr.dive = true;
						break;
					case SPACE:
						usr.capture = true;	
						//XPos += 10;
						//sound.playSound("cameraSound.wav");
						break;
					case RIGHT:
						usr.setDirection(Direction.EAST);
						break;
					case LEFT:
						usr.setDirection(Direction.WEST);
					case DIGIT2:
					default:
						break;
				}
			}
		});
        canvas = new Canvas(canvasWidth, canvasHeight);
        root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		importImages();
		timer = new StackPane();
		timer.setPrefSize(canvasWidth, canvasHeight);
		scoreboard = new Text("Time: 0");
		scoreboard.setFont(new Font("Verdana", 40));
		timer.getChildren().add(scoreboard);
		timer.setAlignment(scoreboard, Pos.TOP_RIGHT);
		root.getChildren().add(timer);
		generateAnimals();
	}
	
	/**
	* This method imports all of the images used in this View
	*/
	private void importImages() {
        String img_file_base = "images/";
        String ext = ".png";
		images.put("background", createImage(img_file_base + "underwater" + ext));
		images.put("scuba", createImage(img_file_base + "drop-the-bass/scuba" + ".gif"));
		images.put("octopus", createImage(img_file_base + "octopus" + ext));
		images.put("octopus_user", createImage(img_file_base + "octopus" + ".gif"));
		images.put("bass", createImage(img_file_base + "bass" + ext));
		images.put("userBass", createImage(img_file_base + "userBass" + ext));
		images.put("bird", createImage(img_file_base + "bird" + ".gif"));
		images.put("Anchovy", createImage(img_file_base + "anchovy" + ext));
		images.put("Crab", createImage(img_file_base + "crab" + ".gif"));
		images.put("HorseShoeCrab", createImage(img_file_base + "horseshoe crab" + ".gif"));
		images.put("Clam", createImage(img_file_base + "clams" + ".gif"));
	}

	/**
	* This method creates an Image object from the image stored at the path passed in from the param
	@param image_file the path to the image file which is to be created
	*/
    private Image createImage(String image_file) {
        Image img = new Image(image_file);
        return img;   	
    }

	/**
	* This method is the highest level method which draws everything onto the screen as seen by the player
	@param animals all of the animals found in the Model which need to be drawn
	@param captureAnimals all of the animals whose pictures have been taken which need to be drawn in the top left
	@param time the current time which is drawn in the top right
	*/
	public void update(ArrayList<Animal> allAnimals, HashSet<Animal> capturedAnimals, int time) {
		gc.clearRect(0, 0, canvasWidth, canvasHeight);
		sideScroll-=3;
		
		gc.drawImage(images.get("background"),sideScroll, 0, canvasWidth, canvasHeight);
		gc.drawImage(images.get("background"), sideScroll+canvasWidth, 0, canvasWidth, canvasHeight);
		gc.drawImage(images.get("background"), sideScroll+canvasWidth*2, 0, canvasWidth, canvasHeight);
		gc.drawImage(images.get("background"), sideScroll+canvasWidth*3, 0, canvasWidth, canvasHeight);
		gc.drawImage(images.get("background"), sideScroll+canvasWidth*4, 0, canvasWidth, canvasHeight);
		gc.drawImage(images.get("background"), sideScroll+canvasWidth*5, 0, canvasWidth, canvasHeight);
		Iterator<Animal> itr = allAnimals.iterator();
		while(itr.hasNext()){
			Animal a = itr.next();
			if(!a.eaten)
				transformAndDraw(a);
			else{
				ImageView iv=animals.get(a);
				root.getChildren().remove(iv);
				animals.remove(a);
			}
		}	
		/*if(!(capturedAnimals.isEmpty())){
			for(Animal b : capturedAnimals){
				b.xPos = XPos;
				b.yPos = 0;
				transformAndDraw(b);
			}
		}
		*/
		drawTime(time);
		if(usr instanceof Bird)
		{
			drawBreath(usr.breath);
		}
	}

	
	/**
	* This method draws an individual Animal
	@param a the animal to be drawn
	*/
    private void transformAndDraw(Animal a) {
		gc.setFill(Paint.valueOf("red"));
		gc.fillRect(100, 10, usr.health, 20);
		ImageView iv = animals.get(a);
		iv.setX(a.getX());
		iv.setY(a.getY());
		if(a.getDirection().getFlipped()){
			iv.setScaleX(-1);
		}
		else{
			iv.setScaleX(1);
		}
		iv.setRotate(a.getDirection().getRotationAngle());
	}
	
    /**
     * Function for generating the animals on the screen
     */
	private void generateAnimals(){
		Iterator<Animal> itr = listAnimals.iterator();
		while(itr.hasNext()){
			Animal a = itr.next();
			ImageView iv = new ImageView(images.get(a.getImageKey()));
			iv.setFitHeight(a.getImgHeight());
			iv.setFitWidth(a.getImgWidth());
			iv.setX(a.getX());
			iv.setY(a.getY());	
			root.getChildren().add(iv);
			animals.put(a, iv);
		}
	}

	/**
	This method updates the time displayed in the top right
	@param time the time which is to be drawn in the top right
	*/
	private void drawTime(int time){
		scoreboard.setText("Time: " + Integer.toString(time));
	}
	
	/**
	 * Function that draws the breath meter
	 * @param breath the amount of breath that the user has left
	 */
	private void drawBreath(int breath){
		gc.setFill(Paint.valueOf("blue"));
		gc.fillRect(700, 10, breath, 20);
	}

}
