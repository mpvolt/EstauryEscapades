import java.io.Serializable;
import java.util.*;

import com.sun.javafx.geom.Rectangle;

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
class GameModel implements java.io.Serializable{
	public int width;
	public int height;
	private ArrayList<Animal> animals = new ArrayList<Animal>();
	private HashSet<Animal> capturedAnimals = new HashSet<Animal>();
	private Animal usr;
	double startTime = System.currentTimeMillis()/1000;
	double time = 0;
	int gameNum;
	/**
	 * GameModel Constructor
	@param width will be set as the width of the canvas
	@param height will be set as the height of the canvas
	@return a GameModel object filled with it's starting Animal objects
	*/
	GameModel(int gameNum, int width, int height){
		this.gameNum = gameNum;
		this.width = width;
		this.height = height;
		generateStartingAnimals();
	}
	
	/**
	 * Function for setting the user's animal
	@param a the animal which will be controlled by the player (representing by the usr variable)
	*/
	void setUser(Animal a){
		usr = a;
	}
	
	/**
	 * Function for getting the remaining time @see GameView
	@return returns an integer representing the current in-game time in seconds, will be written onto the screen in the View
	*/
	public int getTime(){
		return (int)time;
	}
	
	/**
	* This method generates the starting Animals for the given Game, one of each Animal is added then the rest are determined randomly
	*/
	void generateStartingAnimals(){
		addAnimal(new Octopus(width, height));
		//addAnimal(new Anchovy(width, height));
		addAnimal(new Bass(width, height));
		addAnimal(new Bass(width, height));
		if(gameNum == 1){
			addAnimal(new Crab(width, height));
			addAnimal(new HorseShoeCrab(width, height));
			addAnimal(new Clam(width, height));
		}
		else{
			for (int i = 0; i < 5; i++){
				int rand = (int)((Math.random() * ((1 - 0) + 1)) + 0);
				switch(rand){
					case 0:
						addAnimal(new Bass(width, height));
						break;
					case 1:
						addAnimal(new Octopus(width, height));
						break;
				}
			}
		}
	}
	
	/**
	 * Function for bird class that determines if user is underwater
	 */
	void underwater(){
		if(usr instanceof Bird){
			if(usr.getY() > (height * .2)){ //if the bird usr goes more than 20% down the screen it is underwater and can't breathe so breath is decreased
				usr.breath -= 1;
			}
			else{
				if(usr.breath < 100){
					usr.breath += 1; 
				}
			}
		}
	}
	
	/**
	 * Function for getting the arraylist of animals
	@return an ArrayList containing all of the Animals currently in the GameModel
	*/
	ArrayList<Animal> getAllAnimals(){
		return animals;
	}

	/**
	 * Funciton for adding a new animal to the list of animals
	@param a the animal which will be added to the GameModel
	*/
	void addAnimal(Animal a){
		animals.add(a);
	}

	/**
	* This method is called when the user attempts to take a picture in the first game.
	* It checks whether any animals are in range of the scuba diver and if so it removes them from the game and adds them to the capturedAnimals arraylist
	*/
	void takePicture(){
		Iterator iter = animals.iterator();
		while(iter.hasNext()){
			Animal a = (Animal) iter.next();
			if(usr.withinDistance(a) && !(a instanceof Scuba) && (a.eaten == false)){
				capturedAnimals.add(a);
				a.eaten = true;
				
			}
		}
		usr.capture = false;
	}
	
	/**
	 * Function for getting the list of captured animals
	@return an arraylist of all of the capture Animals
	*/
	HashSet<Animal> getCapturedAnimals(){
		return capturedAnimals;
	}
	
	/**
	* This method updates the location and direction of every Animal found in the GameModel.
	* Based on random numbers it may reassign the direction of a given Animal (never the usr) but otherwise every Animal will be moved simply through a move call
	*/
	void updateLocationandDirection(){
		Random generator = new Random();
		int random = generator.nextInt(100);
		for(Animal a: animals){
			random = generator.nextInt(100);
			if(!(a.name.equals(usr.name))){
				if(random < 5){ //5% chance that an animals direction will be changed
					a.randomizeDirection();
				}
			}
			a.move();
		}
		collisionDetector();
		incrementTime();
		underwater();
	}

	/**
	* This method determines if any collisions have occured after every Animal's position has been updated, annd if so collided is called to determine what if anything should happen.
	*/
	void collisionDetector(){
		int size = animals.size();
		for (int i = 0; i < size; i++){
			for (int j = i + 1; j < size; j++){
				Animal a = animals.get(i);
				Animal b = animals.get(j);
				if((a.getX() > b.getX()) && (a.getX() < (b.getX() + b.getImgWidth()))){
					if((a.getY() > b.getY()) && (a.getY() < (b.getY() + b.getImgHeight()))){
						collided(a, b);
					}
				}
				else if((b.getX() > a.getX()) && (b.getX() < (a.getX() + a.getImgWidth()))){
					if((b.getY() > a.getY()) && (b.getY() < (a.getY() + a.getImgHeight()))){
						collided(a, b);
					}
				}
				size = animals.size();
			}
		}
	}

	/**
	* This method checks the two Animal's prey HashSets and if one animal is the prey of the other it is removed from the GameModel and the animal's health is increased.
	@param a the first of the two Animals which collided
	@param b the second of the two Animals which collided
	*/
	void collided(Animal a, Animal b){
		if(a.getPrey().contains(b.getClass().getSimpleName())){
			a.health += 10; //eating one animal corresponds to gaining 10 ticks of play time 
			b.eaten = true;
			b.photoTaken = true;
		
		}
		else if (b.getPrey().contains(a.getClass().getSimpleName())){
			b.health += 10; //eating one animal corresponds to gaining 10 ticks of play time
			a.eaten = true;
			a.photoTaken = true;
			
		}
	}
	/**
	* This method increases the time variable. It is used in the timer which is displayed in the View
	*/
	void incrementTime(){
		time = (System.currentTimeMillis() / 1000) - startTime;
	}
}
