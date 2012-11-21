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
public final class UndoMoveEventManager {

	private static UndoMoveEventManager instance = null;
	private List<UndoMoveEventListener> listeners = new ArrayList<UndoMoveEventListener>(0);
	
	private UndoMoveEventManager(){}
	
	public static UndoMoveEventManager getInstance() {
		if(null != instance)
			return instance;
		synchronized (UndoMoveEventManager.class) {
			instance = new UndoMoveEventManager();
		}
		return instance;
	}


	public synchronized void addUndoMoveEventListener(UndoMoveEventListener changeListener){
		listeners.add(changeListener);
	}
	
	public synchronized void removeUndoMoveEventListener(UndoMoveEventListener changeListener){
		listeners.remove(changeListener);
	}
	
	public synchronized void fireUndoMoveEvent(UndoMoveEvent event) {
		if(null == event)
			return;
		
		for (UndoMoveEventListener listener : listeners) {
			listener.undoComplete(event);
		}
	}
	
}
