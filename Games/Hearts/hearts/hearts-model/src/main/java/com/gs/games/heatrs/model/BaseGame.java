/**
 * 
 */
package com.gs.games.heatrs.model;

import java.util.Date;
import java.util.List;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public abstract class BaseGame {

	private Deck deck;
	
	private Player currentPlayer = null;
	private Player winer = null;
	private List<Player> players;
	
	private Date startTime;
	private Date pausedAt;
	private Date resumedAt;
	private Date endTime;
	
	public abstract void start();
	public abstract void pause();
	public abstract void resume();
	public abstract void resine();
	
}
