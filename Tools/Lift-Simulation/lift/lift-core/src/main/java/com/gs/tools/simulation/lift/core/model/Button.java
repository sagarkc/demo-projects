/**
 * 
 */
package com.gs.tools.simulation.lift.core.model;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public abstract class Button {

	private final String name;
	private String displayName;
	private ButtonState state;

	/**
	 * @param name
	 */
	public Button(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		if (null == displayName || displayName.trim().length() <= 0)
			displayName = name;
		this.displayName = displayName;
	}

	public ButtonState getState() {
		return state;
	}

	public void setState(ButtonState state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public abstract void press();
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Button)) {
			return false;
		}
		Button other = (Button) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Button [Name=");
		builder.append((null != displayName) ? displayName : name);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}

}
