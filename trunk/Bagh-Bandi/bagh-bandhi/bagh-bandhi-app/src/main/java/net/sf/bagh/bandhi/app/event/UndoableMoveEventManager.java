/**
 * 
 */
package net.sf.bagh.bandhi.app.event;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public final class UndoableMoveEventManager {

	private static UndoableMoveEventManager instance = null;
	private List<UndoableMoveListener> listeners = new ArrayList<UndoableMoveListener>(0);
	
	private UndoableMoveEventManager(){}
	
	public static UndoableMoveEventManager getInstance() {
		if(null != instance)
			return instance;
		synchronized (UndoableMoveEventManager.class) {
			instance = new UndoableMoveEventManager();
		}
		return instance;
	}


	public synchronized void addUndoableMoveListener(UndoableMoveListener changeListener){
		listeners.add(changeListener);
	}
	
	public synchronized void removeUndoableMoveListener(UndoableMoveListener changeListener){
		listeners.remove(changeListener);
	}
	
	public synchronized void fireUndoableMoveEvent(UndoableMoveEvent event) {
		if(null == event)
			return;
		
		for (UndoableMoveListener listener : listeners) {
			listener.undoableMoveHappened(event);
		}
	}
	
}
