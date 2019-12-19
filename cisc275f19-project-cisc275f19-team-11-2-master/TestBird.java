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
public class TestBird{
	@Test
	public void testConstructor(){
		Bird b1 = new Bird(200, 300);
		assertEquals(200, b1.canvasWidth);
		assertEquals(300, b1.canvasHeight);
		assertEquals(50, b1.getImgWidth());
		assertEquals(50, b1.getImgHeight());
		assertEquals(b1.getX(), 200);
		assertEquals(b1.getY(), 0);
		assertTrue(b1.getPrey().contains("anchovy"));
		assertTrue(b1.getPrey().contains("Bass"));
		assertTrue(b1.getPrey().contains("Crab"));
		assertEquals("bird", b1.getImageKey());
	}

	@Test
	public void setDirection(){
		Bird b1 = new Bird(100, 100);
		assertTrue(b1.setDirection(Direction.NORTH));
		assertEquals(Direction.NORTH, b1.getDirection());

		assertTrue(b1.setDirection(Direction.WEST));
		assertEquals(Direction.WEST, b1.getDirection());

		assertTrue(b1.setDirection(Direction.EAST));
		assertEquals(Direction.EAST, b1.getDirection());

		assertFalse(b1.setDirection(Direction.SOUTH));
		assertFalse(b1.setDirection(Direction.NORTHEAST));
		assertFalse(b1.setDirection(Direction.NORTHWEST));
		assertFalse(b1.setDirection(Direction.SOUTHEAST));
		assertFalse(b1.setDirection(Direction.SOUTHWEST));
	}

	@Test
	public void testDetermineDirection(){
		Bird b1 = new Bird(100, 100);
		b1.setImgWidth(50);
		b1.setImgHeight(50);
		b1.setX(0);
		assertTrue(b1.setDirection(Direction.NORTH));
		b1.determineDirection();
		assertEquals(Direction.EAST, b1.getDirection());
		b1.setX(60);
		b1.determineDirection();
		assertEquals(Direction.WEST, b1.getDirection());
	}

	@Test
	public void testMove(){
		Bird b1 = new Bird(200, 200);
		b1.setX(50);
		b1.setY(50);

		b1.setDirection(Direction.EAST);
		int prevX = b1.getX();
		b1.move();
		assertEquals(prevX + b1.getXSpeed(), b1.getX());

		b1.setDirection(Direction.WEST);
		prevX = b1.getX();
		b1.move();
		assertEquals(prevX - b1.getXSpeed(), b1.getX());
	}

	@Test
	public void testDive(){
		Bird b1 = new Bird(100, 100);
		b1.setX(0);
		b1.setY(0);
		int prevX = b1.getX();
		int prevY = b1.getY();
		b1.dive();
		assertEquals(prevX, b1.getX());
		assertEquals(prevY + 50, b1.getY());
	}
}
