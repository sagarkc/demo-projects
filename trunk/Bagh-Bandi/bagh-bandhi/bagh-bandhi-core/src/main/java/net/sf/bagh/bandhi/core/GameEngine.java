/**
 * 
 */
package net.sf.bagh.bandhi.core;

/**
 * Game Engine
 * 
 * @author Sabuj Das | sabuj.das@gmail.com
 *  
 */
public final class GameEngine {

	private GameSimulator gameSimulator;
	
	private static GameEngine engine;
	
	private GameEngine(){
		
	}
	/**
	 * @return the engine
	 */
	public static GameEngine getEngine() {
		if(null != engine)
			return engine;
		synchronized (GameEngine.class) {
			engine = new GameEngine();
		}
		return engine;
	}
	
	/**
	 * @return the gameSimulator
	 */
	public GameSimulator getGameSimulator() {
		return gameSimulator;
	}
	
	/**
	 * @param gameSimulator the gameSimulator to set
	 */
	public void setGameSimulator(GameSimulator gameSimulator) {
		this.gameSimulator = gameSimulator;
	}
	
	

}
