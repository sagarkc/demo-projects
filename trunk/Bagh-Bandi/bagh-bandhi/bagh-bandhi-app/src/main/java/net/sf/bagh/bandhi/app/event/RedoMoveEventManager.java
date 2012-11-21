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
public final class RedoMoveEventManager {

	private static RedoMoveEventManager instance = null;
	private List<RedoMoveEventListener> listeners = new ArrayList<RedoMoveEventListener>(0);
	
	private RedoMoveEventManager(){}
	
	public static RedoMoveEventManager getInstance() {
		if(null != instance)
			return instance;
		synchronized (RedoMoveEventManager.class) {
			instance = new RedoMoveEventManager();
		}
		return instance;
	}


	public synchronized void addRedoMoveEventListener(RedoMoveEventListener changeListener){
		listeners.add(changeListener);
	}
	
	public synchronized void removeRedoMoveEventListener(RedoMoveEventListener changeListener){
		listeners.remove(changeListener);
	}
	
	public synchronized void fireRedoMoveEvent(RedoMoveEvent event) {
		if(null == event)
			return;
		
		for (RedoMoveEventListener listener : listeners) {
			listener.redoComplete(event);
		}
	}
	
}
