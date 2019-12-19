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
public class TestAnchovy{
	@Test
	public void testConstructor(){
		Anchovy a1 = new Anchovy(100, 200);
		assertEquals(100, a1.canvasWidth);
		assertEquals(200, a1.canvasHeight);
		assertEquals("Anchovy", a1.getImageKey());
		assertEquals(90, a1.getImgWidth());
		assertEquals(45, a1.getImgHeight());
	}
}
