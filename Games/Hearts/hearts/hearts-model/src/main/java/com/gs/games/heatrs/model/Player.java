/**
 * 
 */
package com.gs.games.heatrs.model;

import com.gs.games.heatrs.model.entity.Hand;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class Player {

	private final String name;
	private Hand hand;
	
	private Player nextPlayer;
	
	/**
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
	}


	/**
	 * @param name
	 * @param hand
	 */
	public Player(String name, Hand hand) {
		this.name = name;
		this.hand = hand;
	}


	public Hand getHand() {
		return hand;
	}


	public void setHand(Hand hand) {
		this.hand = hand;
	}


	public String getName() {
		return name;
	}


	public Player getNextPlayer() {
		return nextPlayer;
	}


	public void setNextPlayer(Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Player)) {
			return false;
		}
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Player [name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
	
}
