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
public class TestBass{
	@Test
	public void testConstructors(){
		Bass b1 = new Bass(200, 300);
		assertEquals(200, b1.canvasWidth);
		assertEquals(300, b1.canvasHeight);
		assertEquals(90, b1.getImgWidth());
		assertEquals(44, b1.getImgHeight());
		assertTrue(b1.getPrey().contains("anchovy"));
		assertTrue(b1.getPrey().contains("Clam"));
		assertTrue(b1.getPrey().contains("Crab"));
		assertEquals("bass", b1.getImageKey());

		Bass b2 = new Bass(200, 300, 1);
		assertEquals(200, b2.canvasWidth);
		assertEquals(300, b2.canvasHeight);
		assertEquals(100, b2.getImgWidth());
		assertEquals(64, b2.getImgHeight());
		assertTrue(b2.getPrey().contains("anchovy"));
		assertTrue(b2.getPrey().contains("Clam"));
		assertTrue(b2.getPrey().contains("Crab"));
		assertEquals("userBass", b2.getImageKey());
	}

}
