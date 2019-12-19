import javafx.application.Application;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.Node;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.control.Button;
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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.Node;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import java.util.HashSet;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
import java.io.Serializable;
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.File;
import javafx.scene.input.KeyEvent;

/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
public class Controller extends Application implements Serializable{
	private GameModel model;
	Stage svaedStage;
	private QuizModel quizModel;
	private GameView view;
	private QuizView quizView;
	private TitleView titleView;
	private FFView ffView;
	private ScubaTutView scubaTutorial;
	private AnimalTutView animalTutorial;
	Scene titleScene;
	private GameMode gm;
	private HashSet<Animal> capturedAnimals;
	private Animal player;
	
	
	/**
	* The main for this game, this is the first piece of code run
	@param args an array of Strings representing any arguements used when the program is run
	*/
	public static void main(String[] args) {
		launch(args);
		
	}
	//saves state of Controller
	/**
	 * Save the current game
	 * @param file the File to read
	 */
	public void saveGame(File file){
		try{
			FileOutputStream fileStream = new FileOutputStream(file);   
			ObjectOutputStream objectStream = new ObjectOutputStream(fileStream); 
			objectStream.writeObject(this);
			objectStream.close();   
        	fileStream.close();
		}catch (Exception e) {  
				System.out.println("\nFail to save game state.");
		}

	}
	//Loads state of Controller
	/**
	 * Function for loading the game data
	 * @param file the file to read
	 * @throws ClassNotFoundException throw exception if file is incorrect
	 */
	public void loadGameDataFromFile(File file) throws ClassNotFoundException{
		try{ 
			FileInputStream fileStream = new FileInputStream(file);   
			ObjectInputStream objectStream = new ObjectInputStream(fileStream); 
			//svaedStage=(Controller)objectStream.readObject();
		}catch (Exception e) {  
			e.printStackTrace();
		}
	}

	public void serilaizeToDkisk(GameModel model){
		try{
			FileOutputStream fos=new FileOutputStream("controller.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(model);
			oos.close();
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
		try {
			FileInputStream fis = new FileInputStream("controller.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			GameModel Model=(GameModel) ois.readObject();
			ois.close();
			new File("controller.ser").delete();
			
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}
	/**
	* This method starts the game
	@param theStage the Stage object which will be used for each subgame
	*/
    @Override
	public void start(Stage theStage) {
		
		
		titleView = new TitleView(theStage);
		Scuba usr = new Scuba(titleView.getWidth(), titleView.getHeight());
		Octopus usrs = new Octopus(titleView.getWidth(), titleView.getHeight());
		usrs.setImageKey("octopus_user");
		theStage.show();
		//Change game mode
		gm = GameMode.STARTSCREEN;
		new AnimationTimer() {
			/**
			* This method determines what section the game is currently at and what code should be run in order to continue the overall game
			@param currentNanoTime the current nanosecond the game is at 
			*/
			public void handle(long currentNanoTime){
				switch(gm){
					//title Screen
					case STARTSCREEN:		
						if(titleView.getIsFinished()){
							gm = GameMode.SCUBATUTORIAL;
						}
						break;
					//Scuba Tutorial
					case SCUBATUTORIAL:
						theStage.close();
						scubaTutorial = new ScubaTutView(theStage);
						theStage.show();
						gm = GameMode.SCUBATUTORIALRUN;
						break;
					//Player interaction with scuba Tutorial
					case SCUBATUTORIALRUN:
						scubaTutorial.drawInteractive();
						if(scubaTutorial.getIsFinished()){
							gm = GameMode.SCUBASTART;
						}
						break;
					//Beginning of Scuba Game
					case SCUBASTART:
						theStage.close();
						model = new GameModel(0, titleView.getWidth(), titleView.getHeight());
						model.addAnimal(usr);
						model.setUser(usr);
						view = new GameView(theStage, usr, model.getAllAnimals());		
						theStage.show();
						gm = GameMode.SCUBARUN;
						break;
					//Scuba Game Logic
					case SCUBARUN:
						model.updateLocationandDirection();
						view.update(model.getAllAnimals(), model.getCapturedAnimals(), model.getTime());
						if (usr.capture == true){
							model.takePicture();
							capturedAnimals = model.getCapturedAnimals();
						}
						if(model.getTime() >= 30){
							gm = GameMode.FFTITLESTART;
						}
						break;
					case SCUBAEND:
						break;
					//Selection Screen 
					case FFTITLESTART:
						if ((capturedAnimals == null) || (capturedAnimals.size() == 0)){
							gm = GameMode.SCUBASTART;
						}
						else{
							theStage.close();
							ffView = new FFView(theStage, capturedAnimals);
							theStage.show();
							gm = GameMode.FFTITLE;
						}
						break;
					//Input from Selection Screen
					case FFTITLE:
						String choice = ffView.getGameChoice();
						if(!choice.equals("NONE")){
							gm = GameMode.QUIZSTART;
							if (choice.equals("Bass")){
								player = new Bass(titleView.getWidth(), titleView.getHeight(), 1);
							}
							else if (choice.equals("Octopus")){
								player = new Octopus(titleView.getWidth(), titleView.getHeight());
								player.setImageKey("octopus_user");
							}
							else if (choice.equals("Bird")){
								player = new Octopus(titleView.getWidth(), titleView.getHeight());
							}
							else if (choice.equals("Horshoe Crab")){
								player = new Octopus(titleView.getWidth(), titleView.getHeight());
							}
							quizModel = new QuizModel(player);
						}
						break;
					case FFTITLEEND:
						break;
					//Creating Quiz Stage
					case QUIZSTART:
						theStage.close();
						quizView = new QuizView(theStage, quizModel.getQuestions());
						theStage.show();
						gm = GameMode.QUIZRUN;
						break;
					//Quiz Logic
					case QUIZRUN:
						quizView.update();
						if(quizView.getIsFinished()){
							gm = GameMode.ANIMALSTART;
						}
						break;
					//Closing Quiz Stage
					case QUIZEND:
						break;
					//Beginning of Animal Game
					case ANIMALSTART:
						theStage.close();
						model = new GameModel(1, titleView.getWidth(), titleView.getHeight());
						model.addAnimal(player);
						model.setUser(player);
						view = new GameView(theStage, player, model.getAllAnimals());
						view.gameNum = 1;
						theStage.show();
						gm = GameMode.ANIMALRUN;
						break;
					//Animal Game Logic
					case ANIMALRUN:
						model.updateLocationandDirection();
						view.update(model.getAllAnimals(), model.getCapturedAnimals(), model.getTime());
						if(player.health < 0){
							gm = GameMode.ANIMALEND;
						}
						break;
					//ending Animal Game
					case ANIMALEND:
						serilaizeToDkisk(model);
						theStage.close();
						break;
					}
					try {
						Thread.sleep(2);
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
					}	
				}
			}.start();
        theStage.show();
    }
}
