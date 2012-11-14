/**
 * 
 */
package net.sf.bagh.bandhi.app;

import java.util.ArrayList;
import java.util.List;

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
		for (GameStatusChangeListener listener : listeners) {
			listener.animalMoved(changeEvent);
		}
	}
}
