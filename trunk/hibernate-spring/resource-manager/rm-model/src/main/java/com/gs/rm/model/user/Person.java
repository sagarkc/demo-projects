/**
 * 
 */
package com.gs.rm.model.user;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import com.gs.rm.model.contact.Address;

/**
 * @author Sabuj Das
 * 
 */
@MappedSuperclass
public abstract class Person implements Serializable, Comparable<Person> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7860341267098424360L;
	
	private String nationalIdentificationNumber;
	private String firstName;
	private String lastName;
	private String middleName;

	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getNationalIdentificationNumber() {
		return nationalIdentificationNumber;
	}

	public void setNationalIdentificationNumber(
			String nationalIdentificationNumber) {
		this.nationalIdentificationNumber = nationalIdentificationNumber;
	}

	@Override
	public int compareTo(Person other) {
		return this.getNationalIdentificationNumber().compareTo(
				other.getNationalIdentificationNumber());
	}
}
