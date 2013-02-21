package jmx.spring;

import jmx.spring.ui.ColumnHeader;

/*  CREATE  TABLE `hr`.`employee` (
`first_name` VARCHAR(50) NULL ,
`last_name` VARCHAR(50) NULL ,
`salary` DOUBLE NULL DEFAULT 0 );
*/
public class Employee {

	private String firstName;
	private String lastName;
	private Double salary;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	@ColumnHeader(index=0, title="First Name", visible=true)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@ColumnHeader(index=1, title="Last Name", visible=true)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@ColumnHeader(index=2, title="Salary", visible=true)
	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName
				+ ", salary=" + salary + "]";
	}
	
	
}
