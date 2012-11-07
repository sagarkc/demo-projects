/**
 * 
 */
package net.sf.bagh.bandhi.core;

import net.sf.bagh.bandhi.core.exception.EndOfGameException;
import net.sf.bagh.bandhi.core.exception.InvalidMoveException;
import net.sf.bagh.bandhi.core.model.Animal;
import net.sf.bagh.bandhi.core.model.Board;
import net.sf.bagh.bandhi.core.model.Box;
import net.sf.bagh.bandhi.core.model.Human;
import net.sf.bagh.bandhi.core.model.Player;
import net.sf.bagh.bandhi.core.model.Animal.AnimalType;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class ManualSimulator extends GameSimulator {

	public ManualSimulator(Human firstPlayer, Human secondPlayer, Board board) {
		super(firstPlayer, secondPlayer, board);
	}

	/* (non-Javadoc)
	 * @see net.sf.bagh.bandhi.core.GameSimulator#selectAnimal(net.sf.bagh.bandhi.core.model.Player)
	 */
	@Override
	public Animal selectAnimal(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.sf.bagh.bandhi.core.GameSimulator#selectTargetBox(net.sf.bagh.bandhi.core.model.Player)
	 */
	@Override
	public Box selectTargetBox(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.sf.bagh.bandhi.core.GameSimulator#doNextMove(net.sf.bagh.bandhi.core.model.Player, net.sf.bagh.bandhi.core.model.Animal, net.sf.bagh.bandhi.core.model.Box, net.sf.bagh.bandhi.core.model.Box)
	 */
	@Override
	public boolean doNextMove(Player player, Animal animal, Box from, Box to)  throws InvalidMoveException{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see net.sf.bagh.bandhi.core.GameSimulator#doNextMove(net.sf.bagh.bandhi.core.model.Player, net.sf.bagh.bandhi.core.model.Animal, net.sf.bagh.bandhi.core.model.Box)
	 */
	@Override
	public boolean doNextMove(Player player, Animal animal, Box box)  throws InvalidMoveException, EndOfGameException{
		if(null == player || null == animal || null == box){
			throw new IllegalArgumentException("Some/all the srguments are NULL.");
		}
		AnimalType animalType = getBoard().evalute();
		if(AnimalType.NONE != animalType){
			throw new EndOfGameException("Game already ended...");
		}
		return false;
	}

	

	

}
