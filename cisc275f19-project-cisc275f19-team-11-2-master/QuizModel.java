import java.util.*;
/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
class QuizModel{
	ArrayList<Question> questions;
	
	/**
	 * Constructor for quizmodel
	 * @param chosenAnimal Displays the animal chosen
	 */
	public QuizModel(Animal chosenAnimal){
		generateQuestions(chosenAnimal);
	}
	/**
	 * Function for generating questions based on chosenAnimal
	 * @param chosenAnimal the Animal that the user chooses
	 */
	void generateQuestions(Animal chosenAnimal){
		questions = new ArrayList<Question>();
		switch(chosenAnimal.getClass().getSimpleName()){
			case "Bass":
				System.out.println("chosenAnimal Bass");
				questions.add(new Question("Bass prefer to eat small fish\n such as minnows and sunfish,\n but will also eat frogs \nand insects as well", true, "bass"));
				questions.add(new Question("Bass have no predators\n and are not eaten by \nany other organisms", false, "bass"));
				questions.add(new Question("Bass tend to be colored green\n and weight 12 pounds on average", true, "bass") );
				questions.add(new Question("In the Delaware Estuary,\n there are no Bass due to\n their predatory behaviors", false, "bass"));

				
				break;
			case "Octopus":
				System.out.println("chosenAnimal Octopus");	
				questions.add(new Question("Octopus prefer to eat worms,\n clams, and other small fish", true, "octopus"));
				questions.add(new Question("Octopus are eaten by eaten\n by some large fish,\n birds, and  some types of whales", true, "octopus"));
				questions.add(new Question("Octopus all tend to\n colored purple or pink", false, "octopus"));
				questions.add(new Question("There are no octopus in\n any US Estuaries\n as they only live in the ocean", false, "octopus"));
				break;
			case "Bird":
				System.out.println("chosenAnimal Bird");
				questions.add(new Question("Some common birds found\n in the Delawre estuary are\n waterfowl and shorebirds", true));
				questions.add(new Question("Shorebirds tne to have\n short legs and flat heads,\n making it difficult for them\n to find food in sand and water", false));
				questions.add(new Question("Waterfowl tend to eat\n small fish, eggs, snails,\n and other small crustaceans", true));
				break;
			case "HorseShoeCrab":
				System.out.println("chosenAnimal HorseshoeCrab");
				questions.add(new Question("Horseshoe crabs tend to eat\n small clams, worms,\n and other small crustaceans", true));
				questions.add(new Question("Horseshoe can grow quite\n large, becoming biggger\n them most estuary animals", false));
				questions.add(new Question("Horseshhoe crabs have\n a hard exoskeleton,\n and use their 10 legs to\n walk along the sea floor", true));
				break;
			default:
				System.out.println("default case");
				break;
		}
		questions.add(new Question("Estuaries are characterized\n by their varying degrees of \nsalinity and complex water movements", true));
		questions.add(new Question("The Delaware estuary\n is primarily for adults\n and off limits for kids", false));
		questions.add(new Question("The Hunger Bar\n continuously falls in the next\n game so you must eat prey", true));
		Collections.shuffle(questions);
	}

	/**
	 * Return the arraylist that contains the questions
	 * @return arrayList of questions
	 */
	ArrayList<Question> getQuestions(){
		return questions;
	}

}
