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
public class TestCrab{
	@Test
	public void testConstructor(){
		Crab c1 = new Crab(100, 200);
		assertEquals(100, c1.canvasWidth);
		assertEquals(200, c1.canvasHeight);
		assertEquals("Crab", c1.getImageKey());
		assertEquals(90, c1.getImgWidth());
		assertEquals(44, c1.getImgHeight());
		assertEquals(0, c1.getYSpeed());
		assertEquals((int)(c1.canvasHeight * .9), c1.getY());
	}
}
