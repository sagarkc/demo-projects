/**
 * 
 */
package com.gs.question.master.model.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.gs.question.master.model.entity.User;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1658465486546L;

	private Long userId;

	@NotNull
	@Size(min = 6, max = 100)
	private String userName;

	@NotNull
	@Size(min = 6, max = 25)
	private String password;

	@NotNull 
	@Email
	@Size(min = 6, max = 100)
	private String email;

	@NotNull
	@Size(min = 1, max = 100)
	private String firstName;

	@Size(max = 100)
	private String middleName;

	@NotNull
	@Size(min = 1, max = 100)
	private String lastName;

	private boolean activated;
	private boolean emailIsUserName;
	private String activationCode;
	private Date activationExpireBy;
	/**
	 * 
	 */
	public UserDto() {
		// TODO Auto-generated constructor stub
	}

	public Date getActivationExpireBy() {
		return activationExpireBy;
	}

	public void setActivationExpireBy(Date activationExpireBy) {
		this.activationExpireBy = activationExpireBy;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public boolean isEmailIsUserName() {
		return emailIsUserName;
	}

	public void setEmailIsUserName(boolean emailIsUserName) {
		this.emailIsUserName = emailIsUserName;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	public final User getEntity(){
		User user = new User();
		user.setUserName(userName);
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setMiddleName(middleName);
		
		user.setActivated(activated);
		user.setActivationCode(activationCode);
		user.setActivationExpireBy(activationExpireBy);
		
		return user;
	}
}
