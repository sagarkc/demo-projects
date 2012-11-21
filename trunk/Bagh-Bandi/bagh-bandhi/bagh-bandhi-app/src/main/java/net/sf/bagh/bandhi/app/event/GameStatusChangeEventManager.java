/**
 * 
 */
package net.sf.bagh.bandhi.app.event;

import java.util.ArrayList;
import java.util.List;

import net.sf.bagh.bandhi.GameStatusEnum;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class GameStatusChangeEventManager {

	private static GameStatusChangeEventManager instance = null;
	private List<GameStatusChangeListener> listeners = new ArrayList<GameStatusChangeListener>(0);
	
	private GameStatusChangeEventManager(){}
	
	public static GameStatusChangeEventManager getInstance() {
		if(null != instance)
			return instance;
		synchronized (GameStatusChangeEventManager.class) {
			instance = new GameStatusChangeEventManager();
		}
		return instance;
	}


	public synchronized void addGameStatusChangeListener(GameStatusChangeListener changeListener){
		listeners.add(changeListener);
	}
	
	public synchronized void removeGameStatusChangeListener(GameStatusChangeListener changeListener){
		listeners.remove(changeListener);
	}
	
	public synchronized void fireGameStatusChangeEvent(GameStatusChangeEvent changeEvent) {
		if(null == changeEvent)
			return;
		if(null == changeEvent.getGameStatus()){
			return;
		}
		for (GameStatusChangeListener listener : listeners) {
			if(GameStatusEnum.NEW_GAME.equals(changeEvent.getGameStatus())){
				listener.gameStarted(changeEvent);
			}
			if(GameStatusEnum.EXITED.equals(changeEvent.getGameStatus())){
				listener.gameExited(changeEvent);
			}
			if(GameStatusEnum.ANIMAL_MOVED.equals(changeEvent.getGameStatus())){
				listener.animalMoved(changeEvent);
			}
		}
	}
}
