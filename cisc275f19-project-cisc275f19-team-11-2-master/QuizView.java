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
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.geometry.Rectangle2D;
/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
class QuizView extends View{
	Image background;
	GraphicsContext gc;
	StackPane textPane;
	VBox vbox;
	HBox questionImages;
	HBox tf;
	Text scoreboard;
	ArrayList<Question> questions;
	int currentQuestionNum;
	boolean userResponse;
	boolean correctResponse;
	int answeredCorrectly;;
	int score;
	boolean isFinished = false;
	Hashtable<String, Image> images = new Hashtable<String, Image>();
	int currentTutorialQuestionNum;
	boolean quizTutorialAnswer;

	/**
	 * Constructor for QuizView
	 * @param theStage the current stage
	 * @param questions the questions that will be asked during the quiz
	 */
	public QuizView(Stage theStage, ArrayList<Question> questions){
		this.theStage = theStage;
		this.questions = questions;
		currentQuestionNum = -1;
		currentQuestionNum = -1;
		int score = 0;
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
	 * Function for drawing quizView
	 */
	public void draw(){
		gc.clearRect(0, 0, canvasWidth, canvasHeight);
		textPane = new StackPane();	
		textPane.setPrefSize(canvasWidth, canvasHeight);
		Text title = new Text("Quiz Time");
		title.setFont(new Font("Verdana", 40));
		scoreboard = new Text("Score: 0");
		scoreboard.setFont(new Font("Verdana", 40));
		textPane.getChildren().add(title);
		textPane.setAlignment(title, Pos.TOP_CENTER);
		textPane.getChildren().add(scoreboard);
		textPane.setAlignment(scoreboard, Pos.TOP_RIGHT);
		root.getChildren().add(textPane);
		//displayNextQuestion();
		displayTutorial();
		gc.drawImage(background, 0, 0, canvasWidth, canvasHeight);
	}

	/**
	 * Function for updating quizView based on the answers given
	 */
	public void update(){
		switch(answeredCorrectly){
			case -1:
				showResults("Sorry you got the question wrong");
				break;
			case 0:
				//scoreboard.setText("Score: " + Integer.toString(score));		
				break;
			case 1:
				showResults("Congratulation you were right");
				score++;
				scoreboard.setText("Score: " + Integer.toString(score));
				break;
			case 2:
				displayNextQuestion();
				break;
			case 3:
				isFinished = true;
				break;
			case 4:
				displayTutorial();
				break;
			case 5:

				break;
		}
	}

	/**
	 * Function for displaying Correct or Incorrect
	 * @param response String that displays the message
	 */
	void showResults(String response){
		textPane.getChildren().remove(vbox);
		answeredCorrectly = 0;
		vbox = new VBox(5);
		vbox.setAlignment(Pos.CENTER);
		Text response2 = new Text(response);
		response2.setFont(new Font("Verdana", 40));
		Button next2 = new Button("Next question");
		next2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				answeredCorrectly = 2;
			}
		});
		vbox.getChildren().add(response2);
		vbox.getChildren().add(next2);
		textPane.getChildren().add(vbox);
	}
	
	/**
	 * Function for displaying the next question
	 */
	void displayNextQuestion(){
		currentQuestionNum++;
		answeredCorrectly = 0;
		if (questions.size() - 1 < currentQuestionNum){
			completedQuestions();
			return;
		}
		textPane.getChildren().remove(vbox);
		vbox = new VBox(5);
		vbox.setAlignment(Pos.CENTER);
		questionImages = new HBox(3);
		tf = new HBox(2);
		questionImages.setAlignment(Pos.CENTER);
		tf.setAlignment(Pos.CENTER);
		
		Text question = new Text(questions.get(currentQuestionNum).getText());
		question.setFont(new Font("Verdana", 40));
		correctResponse = questions.get(currentQuestionNum).getAnswer();
		
		ImageView i1 = createQuestionIV(questions.get(currentQuestionNum).getImgKey());
		ImageView i2 = createQuestionIV(questions.get(currentQuestionNum).getImgKey());
		questionImages.getChildren().add(i1);
		questionImages.getChildren().add(question);
		questionImages.getChildren().add(i2);
		Button trueButton = createButton("True", true);
		Button falseButton = createButton("False", false);
		tf.getChildren().add(trueButton);
		tf.getChildren().add(falseButton);
		vbox.getChildren().add(questionImages);
		vbox.getChildren().add(tf);
		textPane.getChildren().add(vbox);
	}

	/**
	 * Function to check whether users response is correct
	 */
	void checkResponse(){
		if (userResponse == correctResponse){
			answeredCorrectly = 1;
		}
		else{	
			answeredCorrectly = -1;
		}
	}

	/**
	 * Function for handling questions already completed
	 */
	void completedQuestions(){
		textPane.getChildren().remove(vbox);
		vbox = new VBox(5);
		vbox.setAlignment(Pos.CENTER);
		Text response2 = new Text("You completed the quiz");
		response2.setFont(new Font("Verdana", 40));
		Button next2 = new Button("Return to Title Screen");
		next2.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				answeredCorrectly = 3;
			}
		});
		vbox.getChildren().add(response2);
		vbox.getChildren().add(next2);
		textPane.getChildren().add(vbox);
	}
	
	/**
	 * Function for displaying the interactive tutorial
	 */
	private void displayTutorial(){
		currentTutorialQuestionNum++;
		answeredCorrectly = 5;
		textPane.getChildren().remove(vbox);
		vbox = new VBox(5);
		vbox.setAlignment(Pos.CENTER);
		switch(currentTutorialQuestionNum){
			case 1:
				Text instr1 = new Text("Welcome to the Quiz Tutorial, please press the spacebar to continue");
				instr1.setFont(new Font("Verdana", 40));
				theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
					@Override
					public void handle(KeyEvent event){
						switch(event.getCode()){
							case SPACE:
								answeredCorrectly = 4;		
								break;
						}
					}
				});
				vbox.getChildren().add(instr1);
				textPane.getChildren().add(vbox);
				break;
			case 2:
				Text instr2 = new Text("This quiz will consist of true/false questions,\npress the left and right arrows to\nchoose either true or false");
				instr2.setFont(new Font("Verdana", 40));
				HBox buttons = new HBox(2);
				buttons.setAlignment(Pos.CENTER);
				Button tButton = new Button("True");
				tButton.setPrefWidth(350);
				tButton.setPrefHeight(150);
				tButton.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent e){
						answeredCorrectly = 4;
						quizTutorialAnswer = true;
					}
				});
				Button fButton = new Button("False");
				fButton.setPrefWidth(350);
				fButton.setPrefHeight(150);
				fButton.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent e){
						answeredCorrectly = 4;
						quizTutorialAnswer = false;
					}
				});
				buttons.getChildren().add(tButton);
				buttons.getChildren().add(fButton);
				/*
				theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
					@Override
					public void handle(KeyEvent event){
						switch(event.getCode()){
							case UP:
								answeredCorrectly = 4;
								quizTutorialAnswer = true;
								break;
							case DOWN:
								answeredCorrectly = 4;
								quizTutorialAnswer = false;
								break;
						}
					}
				});
				*/
				vbox.getChildren().add(instr2);
				vbox.getChildren().add(buttons);
				textPane.getChildren().add(vbox);
				break;
			case 3:
				Text instr3 = new Text("Your answer was " + String.valueOf(quizTutorialAnswer) + " press spacebar to proceed to the quiz");
				instr3.setFont(new Font("Verdana", 40));
				theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
					@Override
					public void handle(KeyEvent event){
						switch(event.getCode()){
							case SPACE:
								answeredCorrectly = 2;
								break;
						}
					}
				});
				vbox.getChildren().add(instr3);
				textPane.getChildren().add(vbox);
				break;
		}
	}

	/**
	 * Function for importing images
	 */
	private void importImages(){
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
	 * Function for creating button for functional user clicks
	 * @param text generated within the button itself
	 * @param response true or false depending on which button was pressed
	 * @return
	 */
	public Button createButton(String text, Boolean response){
		Button btn = new Button(text);
		btn.setPrefWidth(350);
		btn.setPrefHeight(150);
		btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent e){
				userResponse = response;
				checkResponse();
			}
		});
		return btn;
	}

	/**
	 * Function for selecting the question to be asked
	 * @param key String that points to the question
	 * @return an imageView of the question
	 */
	public ImageView createQuestionIV(String key){
		ImageView i = new ImageView(images.get(key));
		i.setFitWidth(150);
		i.setFitHeight(150);
		return i;
	}
}
