/**
 * 
 */
package net.sf.bagh.bandhi.app.event;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface GameStatusChangeListener {

	void gameStarted(GameStatusChangeEvent event);
	void gamePaused(GameStatusChangeEvent event);
	void gameExited(GameStatusChangeEvent event);
	void gameEnded(GameStatusChangeEvent event);
	void gameSaved(GameStatusChangeEvent event);
	void gameLoaded(GameStatusChangeEvent event);
	
	void animalMoved(GameStatusChangeEvent event);
	void animalCaptured(GameStatusChangeEvent event);
}
