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
public class TestClam{
	@Test
	public void testConstructor(){
		Clam c1 = new Clam(100, 200);
		assertEquals(100, c1.canvasWidth);
		assertEquals(200, c1.canvasHeight);
		assertEquals("Clam", c1.getImageKey());
		assertEquals(90, c1.getImgWidth());
		assertEquals(44, c1.getImgHeight());
		assertEquals(0, c1.getXSpeed());
		assertEquals(0, c1.getYSpeed());
		assertEquals((int)(c1.canvasHeight * 0.9), c1.getY());
	}
}
