import net.sf.bagh.bandhi.core.GameEngine;
import net.sf.bagh.bandhi.core.GameSimulator;
import net.sf.bagh.bandhi.core.ManualSimulator;
import net.sf.bagh.bandhi.core.exception.InvalidMoveException;
import net.sf.bagh.bandhi.core.model.Animal;
import net.sf.bagh.bandhi.core.model.Animal.AnimalType;
import net.sf.bagh.bandhi.core.model.Board;
import net.sf.bagh.bandhi.core.model.Box;
import net.sf.bagh.bandhi.core.model.Goat;
import net.sf.bagh.bandhi.core.model.Human;
import net.sf.bagh.bandhi.core.model.Tiger;

/**
 * 
 */

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Game {
	private static GameEngine gameEngine = GameEngine.getEngine();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Human firstPlayer = new Human("A", AnimalType.TIGER);
		Human secondPlayer = new Human("B", AnimalType.GOAT);
		Tiger[] tigers = new Tiger[2];
		tigers[0] = new Tiger("T1", 1);
		tigers[1] = new Tiger("T2", 2);
		
		Goat[] goats = new Goat[20];
		for (int i = 0; i < goats.length; i++) {
			goats[i] = new Goat("G", i+1);
		}
		Board board = new Board(tigers, goats);
		GameSimulator gameSimulator = new ManualSimulator(firstPlayer, secondPlayer, board);
		gameEngine.setGameSimulator(gameSimulator);
		
		//gameEngine.getGameSimulator().getBoard().setSelectedAnimal(tigers[0]);
		//gameEngine.getGameSimulator().getBoard().set
		
		play();
		
	}

	/**
	 * 
	 */
	private static void play() {
		GameSimulator gameSimulator = gameEngine.getGameSimulator();
		
		gameSimulator.setCurrentPlayer(gameSimulator.getFirstPlayer());
		System.out.println("Current Player " + gameSimulator.getCurrentPlayer());
		
		Animal animal = gameSimulator.getBoard().getTigers()[0];//TODO: gameSimulator.selectAnimal(gameSimulator.getCurrentPlayer());
		Box box = gameSimulator.getBoard().getBoxAt(2,0);// TODO: gameSimulator.selectTargetBox(gameSimulator.getCurrentPlayer());
		if(null != animal && null != box){
			try {
				boolean moved = gameSimulator.doNextMove(gameSimulator.getCurrentPlayer(), animal, box);
				System.out.println("Move : " + moved);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		gameSimulator.setNextPlayer(gameSimulator.getSecondPlayer());
		System.out.println("Next Player " + gameSimulator.getNextPlayer());
	}

}
