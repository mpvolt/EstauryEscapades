import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
* 
* @author Matt Peters 
* @author Joel Farquhar 
* @author Nathan Joseph
*
*/
public class TestOctopus{
	@Test
	public void testConstructor(){
		Octopus o1 = new Octopus(100, 200);
		assertEquals(100, o1.canvasWidth);
		assertEquals(200, o1.canvasHeight);
		assertEquals(68, o1.getImgWidth());
		assertEquals(68, o1.getImgHeight());
		assertTrue(o1.getPrey().contains("Bass"));
		assertTrue(o1.getPrey().contains("anchovy"));
		assertTrue(o1.getPrey().contains("Crab"));
	}
}
