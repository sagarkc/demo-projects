/**
 * 
 */
package net.sf.bagh.bandhi.core;

import net.sf.bagh.bandhi.core.exception.InvalidMoveException;
import net.sf.bagh.bandhi.core.model.Animal;
import net.sf.bagh.bandhi.core.model.Auto;
import net.sf.bagh.bandhi.core.model.Board;
import net.sf.bagh.bandhi.core.model.Box;
import net.sf.bagh.bandhi.core.model.Player;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class AutoSimulator extends GameSimulator {

	public AutoSimulator(Player firstPlayer, Player secondPlayer, Board board) {
		super(firstPlayer, secondPlayer, board);
		// TODO Auto-generated constructor stub
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
	public boolean doNextMove(Player player, Animal animal, Box box)  throws InvalidMoveException{
		// TODO Auto-generated method stub
		return false;
	}

	
	

}
