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
public class TestScuba{
	@Test
	public void testConstructor(){
		Scuba s1 = new Scuba(100, 200);
		assertEquals(100, s1.canvasWidth);
		assertEquals(200, s1.canvasHeight);
		assertEquals("scuba", s1.getImageKey());
		assertEquals(125, s1.getImgWidth());
		assertEquals(75, s1.getImgHeight());
		assertEquals(50, s1.getX());
		assertEquals((s1.canvasHeight / 5), s1.getY());
		assertEquals(5, s1.getXSpeed());
		assertEquals(5, s1.getYSpeed());
		assertFalse(s1.capture);
	}
}
