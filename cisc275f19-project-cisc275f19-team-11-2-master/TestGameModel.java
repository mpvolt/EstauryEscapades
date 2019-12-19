import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
public class TestGameModel{
	@Test
	public void testConstructor(){
		GameModel gameModel = new GameModel(1, 100, 100);
		assertEquals(1, gameModel.gameNum);
		assertTrue(gameModel.getAllAnimals().size() > 0);
	}

	@Test
	public void testGenerateStartingAnimals(){
		GameModel gameModel = new GameModel(0, 100, 100);
		assertTrue(gameModel.getAllAnimals().size() > 0);
	}

	@Test
	public void testTakePicture(){
		GameModel gameModel = new GameModel(0, 100, 100);
		Scuba usr = new Scuba(gameModel.width, gameModel.height);
		Animal capturee = new Bass(gameModel.width, gameModel.height);
		usr.setX(10);
		usr.setY(10);
		capturee.setX(20);
		capturee.setY(20);
		usr.setDirection(Direction.EAST);
		capturee.setDirection(Direction.EAST);
		gameModel.addAnimal(usr);
		gameModel.addAnimal(capturee);
		gameModel.setUser(usr);
		gameModel.takePicture();
		assertTrue(gameModel.getCapturedAnimals().size() > 0);
	}

	@Test
	public void testCollided(){
		GameModel gameModel = new GameModel(0, 100, 100);
		Octopus capturer = new Octopus(gameModel.width, gameModel.height);
		Bass capturee = new Bass(gameModel.width, gameModel.height);
		capturer.setX(10);
		capturer.setY(10);
		capturee.setX(20);
		capturee.setY(20);
		gameModel.addAnimal(capturer);
		gameModel.addAnimal(capturee);
		int prevHealth = capturer.health;
		gameModel.collided(capturer, capturee);
		assertTrue(capturee.eaten);
		capturee.eaten = false;
		assertEquals(prevHealth + 10,  capturer.health);
		capturer.setX(20);
		capturer.setY(20);
		capturee.setX(10);
		capturee.setY(10);
		gameModel.collided(capturee, capturer);
		assertTrue(capturee.eaten);
	}

	@Test
	public void testCollisionDetector(){
		GameModel gameModel = new GameModel(0, 100, 100);
		Octopus capturer = new Octopus(gameModel.width, gameModel.height);
		Bass capturee = new Bass(gameModel.width, gameModel.height);
		capturer.setX(10);
		capturer.setY(10);
		capturee.setX(20);
		capturee.setY(20);
		gameModel.addAnimal(capturer);
		gameModel.addAnimal(capturee);
		int prevHealth = capturer.health;
		gameModel.collisionDetector();
		assertTrue(capturee.eaten);
		assertTrue(prevHealth < capturer.health);
	}

	@Test
	public void testUpdateLocationAndDirection(){
		//This method involves random number generation and can't be tested
		//The methods which compose this method are tested 
		GameModel gameModel = new GameModel(0, 100, 100);
		Scuba usr = new Scuba(100, 100);
		gameModel.addAnimal(usr);
		gameModel.setUser(usr);
		gameModel.updateLocationandDirection();
		assertTrue(true);
	}

	@Test
	public void testIncrementTime(){
		GameModel gameModel = new GameModel(0, 100, 100);
		int startTime = gameModel.getTime();
		try{
			Thread.sleep(1000);
		}
		catch(InterruptedException ex){
			Thread.currentThread().interrupt();
		}
		gameModel.incrementTime();
		assertEquals(startTime + 1, gameModel.getTime());
	}

	@Test
	public void testUnderwater(){
		GameModel gameModel = new GameModel(0, 100, 100);
		Bird b1 = new Bird(100, 100);
		gameModel.addAnimal(b1);
		gameModel.setUser(b1);
		b1.setY(0);
		b1.breath = 5;
		gameModel.underwater();
		assertEquals(6, b1.breath);
	}
}
