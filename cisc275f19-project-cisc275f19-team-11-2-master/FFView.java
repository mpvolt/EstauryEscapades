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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import javafx.scene.image.ImageView;
import java.util.Hashtable;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.util.Collection;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;

/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
class FFView{
	Stage theStage;
	Group root;
	Scene theScene;
	Canvas canvas;
	Image background;
	GraphicsContext gc;
	Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
	int canvasWidth = (int)primaryScreenBounds.getWidth();
	int canvasHeight = (int)primaryScreenBounds.getHeight();
	StackPane sp;
	boolean buttonBassFlag=true;
	boolean buttonOctopusFlag=true;
	HBox imagesBox;
	HBox buttons;
	VBox vbox;
	ImageView playableAnimals[];
	HashSet<Animal> capturedAnimals;
	Hashtable<String, Image> images = new Hashtable<String, Image>();
	String gameChoice = "NONE";
	boolean isFinished = false;
	boolean bassFlag = true;
	boolean octopusFlag = true;

	/**
	 * Constructor for FFView
	 * @param theStage the current stage
	 * @param capturedAnimals the ArrayList that contains the animals that were captured
	 */
	public FFView(Stage theStage, HashSet<Animal> capturedAnimals){
		this.theStage = theStage;
		this.capturedAnimals = capturedAnimals;
		root = new Group();
		theScene = new Scene(root);
		this.theStage.setScene(theScene);
		canvas = new Canvas(canvasWidth, canvasHeight);
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		importImages(capturedAnimals);
		draw();
	}

	/**
	 * Function for drawing FFView
	 */
	public void draw(){
		gc.clearRect(0, 0, canvasWidth, canvasHeight);
		sp = new StackPane();	
		sp.setPrefSize(canvasWidth, canvasHeight);
		Text title = new Text("Animal Selection");
		title.setFont(new Font("Verdana", 40));
		vbox = new VBox(2);
		vbox.setAlignment(Pos.CENTER);
		imagesBox = new HBox(5);
		buttons = new HBox(5);
		imagesBox.setAlignment(Pos.CENTER);
		buttons.setAlignment(Pos.CENTER);
		buttons.getChildren().addAll(generateAllButtons(capturedAnimals));
		playableAnimals = generatePlayableImages(capturedAnimals);
		System.out.println("playable Animals" + playableAnimals);
		imagesBox.getChildren().addAll(playableAnimals);
		vbox.getChildren().add(imagesBox);
		vbox.getChildren().add(buttons);
		sp.getChildren().add(title);
		sp.getChildren().add(vbox);
		sp.setAlignment(title, Pos.TOP_CENTER);
		sp.setAlignment(vbox, Pos.CENTER);
		root.getChildren().add(sp);
		gc.drawImage(background, 0, 0, canvasWidth, canvasHeight);
	}

	public void update(){
	
	}

	/**
	 * Function for importing images
	 * @param capturedAnimals the list of captured animals
	 */
	private void importImages(HashSet<Animal> capturedAnimals){
		background = createImage("images/QuizBackground.png");
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
		/*
		Collection<ImageView> keys = images.values();
		Iterator<ImageView> itr = keys.iterator();
		while(itr.hasNext()){
			ImageView iv = itr.next();
			iv.setFitHeight(150);
			iv.setFitWidth(150);
		}
		*/
	}

	 /**
     * Function for creating image
     * @param image_file the name of the file used for image
     * @return the String url as an image
     */
	private Image createImage(String path){
		return new Image(path);
	}

	/**
	 * Function for creating the animal images
	 * @param capturedAnimals arrayList of captured animals
	 * @return an array of ImageViews
	 */
	private ImageView[] generatePlayableImages(HashSet<Animal> capturedAnimals){
		ArrayList<ImageView> playableAnimals = new ArrayList<ImageView>();//ImageView[capturedAnimals.size()];
		Iterator<Animal> itr = capturedAnimals.iterator();
		while(itr.hasNext()){
			String imgKey = itr.next().getImageKey();
			//|| imgKey.equals("octopus") || imgKey.equals("bird")){
			if (imgKey.equals("bass") && (bassFlag)){
				ImageView iv = new ImageView(images.get(imgKey));
				iv.setFitHeight(150);
				iv.setFitWidth(150);
				playableAnimals.add(iv);
				bassFlag = false;
			}
			if (imgKey.equals("octopus") && (octopusFlag)){
				ImageView iv = new ImageView(images.get(imgKey));
				iv.setFitHeight(150);
				iv.setFitWidth(150);
				playableAnimals.add(iv);
				octopusFlag = false;
			}
			else{
				System.out.println("imgKey: " + imgKey);
			}
		}
		ImageView[] imagesPlayed=new ImageView[playableAnimals.size()];
		for(int i=0;i<playableAnimals.size();i++){
			imagesPlayed[i]=playableAnimals.get(i);
		}
		return imagesPlayed;
	}

	/**
	 * Function for creating the buttons
	 * @param capturedAnimals arrayList of captured animals
	 * @return an arraylist of buttons
	 */
	private ArrayList<Button> generateAllButtons(HashSet<Animal> capturedAnimals){
		ArrayList<Button> buttons = new ArrayList<Button>();
		Iterator<Animal> itr = capturedAnimals.iterator();
		while (itr.hasNext()){
			String simpleName = itr.next().getClass().getSimpleName();
			//if (simpleName.equals("Bass") || simpleName.equals("Octopus") || simpleName.equals("Bird")){
			if (simpleName.equals("Bass") && (buttonBassFlag)){
				buttons.add(generateSelectionButton(simpleName));
				buttonBassFlag = false;
			}
			else if (simpleName.equals("Octopus") && (buttonOctopusFlag)){
				buttons.add(generateSelectionButton(simpleName));
				buttonOctopusFlag = false;
			}
			
			else{
				System.out.println("simpleName: " + simpleName);
			}
		}
		return buttons;
	}

	/**
	 * Generate the button for selecting animal
	 * @param text words contained in the button
	 * @return a button
	 */
	private Button generateSelectionButton(String text){
		Button btn = new Button(text);
		btn.setPrefWidth(150);
		btn.setPrefHeight(50);
		btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				gameChoice = text;
			}
		});
		return btn;
	}

	/**
	 * Function for getting the users choice
	 * @return a string containing their choice
	 */
	public String getGameChoice(){
		return gameChoice;
	}
}
