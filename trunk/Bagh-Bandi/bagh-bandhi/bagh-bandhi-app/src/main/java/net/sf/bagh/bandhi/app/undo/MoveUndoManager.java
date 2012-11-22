/**
 * 
 */
package net.sf.bagh.bandhi.app.undo;

import javax.swing.undo.UndoManager;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public class MoveUndoManager extends UndoManager{

	private static MoveUndoManager manager;
	
	private MoveUndoManager(){
		super();
	}
	
	public static MoveUndoManager getInstance() {
		if(null == manager){
			synchronized (MoveUndoManager.class) {
				manager = new MoveUndoManager();
			}
		}
		return manager;
	}
	
	
}
