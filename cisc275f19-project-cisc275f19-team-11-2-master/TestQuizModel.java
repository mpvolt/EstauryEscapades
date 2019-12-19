import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
public class TestQuizModel{
	@Test
	public void testConstructor(){
	Bass ba1 = new Bass(100, 100);
	QuizModel baqm = new QuizModel(ba1);
	assertEquals(7, baqm.getQuestions().size());

	Octopus o1 = new Octopus(100, 100);
	QuizModel oqm = new QuizModel(o1);
	assertEquals(7, oqm.getQuestions().size());
	
	Bird bi1 = new Bird(100, 100);
	QuizModel biqm = new QuizModel(bi1);
	assertEquals(6, biqm.getQuestions().size());

	HorseShoeCrab h1 = new HorseShoeCrab(100, 100);
	QuizModel hqm = new QuizModel(h1);
	assertEquals(6, hqm.getQuestions().size());
	
	Animal a1 = new Animal(100, 100);
	QuizModel aqm = new QuizModel(a1);
	assertEquals(3, aqm.getQuestions().size());
	}
}
