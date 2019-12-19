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
public class TestAnimal{	
	@Test
	public void testConstructor(){
		Animal a1 = new Animal(100, 100);
		assertTrue(a1.getX() < a1.canvasWidth);
		assertTrue(a1.getX() > 10);
		assertTrue(a1.getY() < a1.canvasHeight);
		assertTrue(a1.getY() > 10);
		boolean directionCorrect = false;
		int i = 0;
		while ((i < Direction.values.length) && !directionCorrect){
			if (a1.getDirection() == Direction.values[i]){
				directionCorrect = true;
			}
			i++;
		}
		assertTrue(directionCorrect);
	}

	@Test
	public void testDetermineDirection(){
		Animal a1 = new Animal(100, 100);
		a1.setImgWidth(50);
		a1.setImgHeight(50);
		assertTrue(a1.setDirection(Direction.NORTH));
		
		a1.setX(60);
		a1.setY(60);
		a1.determineDirection();
		assertEquals(Direction.NORTHWEST, a1.getDirection());
		
		a1.setY(0);
		a1.determineDirection();
		assertEquals(Direction.SOUTHWEST, a1.getDirection());
		
		a1.setY(30);
		a1.determineDirection();
		assertEquals(Direction.WEST, a1.getDirection());
		
		a1.setX(0);
		a1.determineDirection();
		assertEquals(Direction.EAST, a1.getDirection());

		a1.setY(0);
		a1.determineDirection();
		assertEquals(Direction.SOUTHEAST, a1.getDirection());

		a1.setY(60);
		a1.determineDirection();
		assertEquals(Direction.NORTHEAST, a1.getDirection());

		a1.setX(30);
		a1.determineDirection();
		assertEquals(Direction.NORTH, a1.getDirection());

		a1.setY(0);
		a1.determineDirection();
		assertEquals(Direction.SOUTH, a1.getDirection());
	}
	
	@Test
	public void testRandomizeDirection(){
		Animal a1 = new Animal(100, 100);
		a1.randomizeDirection();	
		boolean directionCorrect = false;
		int i = 0;
		while ((i < Direction.values.length) && !directionCorrect){
			if (a1.getDirection() == Direction.values[i]){
				directionCorrect = true;
			}
			i++;
		}
		assertTrue(directionCorrect);
	}

	@Test 
	public void testMove(){
		Animal a1 = new Animal(1000, 1000);
		a1.setX(500);
		a1.setY(500);

		testMoveHelper(a1, Direction.NORTH, 0, -1);
		testMoveHelper(a1, Direction.SOUTH, 0, 1);
		testMoveHelper(a1, Direction.EAST, 1, 0);
		testMoveHelper(a1, Direction.WEST, -1, 0);
		testMoveHelper(a1, Direction.NORTHEAST, 1, -1);
		testMoveHelper(a1, Direction.NORTHWEST, -1, -1);
		testMoveHelper(a1, Direction.SOUTHEAST, 1, 1);
		testMoveHelper(a1, Direction.SOUTHWEST, -1, 1);
		
		a1.setStopped(true);
		testMoveHelper(a1, Direction.NORTHEAST, 0 ,0);
		testMoveHelper(a1, Direction.SOUTHWEST, 0, 0);
		testMoveHelper(a1, Direction.NORTH, 0, 0);
	}
	
	public void testMoveHelper(Animal a1, Direction dir, int xOp, int yOp){
		a1.setDirection(dir);
		int prevX = a1.getX();
		int prevY = a1.getY();
		a1.move();
		if (xOp == -1){
			assertEquals(prevX - a1.getXSpeed(), a1.getX());
		}
		else if (xOp == 0){
			assertEquals(prevX, a1.getX());
		}
		else if (xOp == 1){
			assertEquals(prevX + a1.getXSpeed(), a1.getX());
		}
		if (yOp == -1){
			assertEquals(prevY - a1.getYSpeed(), a1.getY());
		}
		else if (yOp == 0){
			assertEquals(prevY, a1.getY());
		}
		else if (yOp == 1){
			assertEquals(prevY + a1.getYSpeed(), a1.getY());
		}
	}

	@Test
	public void testWithinDistance(){
		Animal a1 = new Animal(200, 200);
		Animal a2 = new Animal(200, 200);
		a1.setImgWidth(10);
		a1.setImgHeight(10);
		a2.setImgWidth(10);
		a2.setImgHeight(10);
		a1.setX(10);
		a1.setY(10);
		a2.setX(11);
		a2.setY(11);
		assertTrue(a1.withinDistance(a2));

		a2.setX(15);
		a2.setY(15);
		assertTrue(a1.withinDistance(a2));
		
		a2.setX(19);
		a2.setY(19);
		assertTrue(a1.withinDistance(a2));

		
		a2.setX(25);
		a2.setY(25);
		assertFalse(a1.withinDistance(a2));
	}

	@Test
	public void testGetStopped(){
		Animal a1 = new Animal(100, 100);
		assertFalse(a1.getStopped());
	}
}
