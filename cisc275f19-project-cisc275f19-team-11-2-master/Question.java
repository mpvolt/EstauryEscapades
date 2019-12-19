/**
 * 
 * @author Matt Peters 
 * @author Joel Farquhar 
 * @author Nathan Joseph
 *
 */
public class Question{
	private String text;
	private boolean answer;
	public String imgKey="crab";

	/**
	 * Constructor for question
	 * @param text what the question says
	 * @param answer true or false based on the questions correctness
	 */
	Question(String text, boolean answer){
		this.text = text;
		this.answer = answer;
	}
	/**
	 * Secondary constructor for displaying an image as well
	 * @param text
	 * @param answer
	 * @param imgKey
	 */
	Question(String text, boolean answer, String imgKey){
		this.text = text;
		this.answer = answer;
		this.imgKey=imgKey;
	}

	/**
	 * Get the questions text
	 * @return the text
	 */
	String getText(){
		return text;
	}

	/**
	 * Get the answer
	 * @return the answer
	 */
	boolean getAnswer(){
		return answer;
	}

	/**
	 * Get the string that points to the corresponding image
	 * @return image's key
	 */
	String getImgKey(){
		return imgKey;
	}

}
