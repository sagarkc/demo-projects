/**
 * 
 */
package net.sf.bagh.bandhi.core.model;

import net.sf.bagh.bandhi.core.model.Animal.AnimalType;


/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public abstract class Player{

	private String name;
	private AnimalType animalType;
	
	public Player(String name, AnimalType animalType) {
		this.name = name;
		this.animalType = animalType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the animalType
	 */
	public Animal.AnimalType getAnimalType() {
		return animalType;
	}

	/**
	 * @param animalType the animalType to set
	 */
	public void setAnimalType(Animal.AnimalType animalType) {
		this.animalType = animalType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((animalType == null) ? 0 : animalType.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Player)) {
			return false;
		}
		Player other = (Player) obj;
		if (animalType != other.animalType) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [name=" + name + ", animalType=" + animalType + "]";
	}
	
	
	
}
