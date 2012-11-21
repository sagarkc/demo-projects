/**
 * 
 */
package net.sf.bagh.bandhi;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public enum GameStatusEnum {

	ANIMAL_MOVED("ANIMAL_MOVED"),
	ANIMAL_CAPTURED("ANIMAL_CAPTURED"),
	NEW_GAME("NEW_GAME"),
	PLAYING("PLAYING"),
	PAUSED("PAUSED"),
	ENDED("ENDED"),
	EXITED("EXITED"),
	UNKNOWN("UNKNOWN");
	
	private final String status;

	private GameStatusEnum(String status) {
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	public boolean isEqual(GameStatusEnum statusEnum){
		return this.status.equals(statusEnum.status);
	}
	
	
	
}
