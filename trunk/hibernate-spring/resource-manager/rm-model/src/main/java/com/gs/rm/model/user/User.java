/**
 * 
 */
package com.gs.rm.model.user;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author Sabuj Das
 *
 */
@Entity
@Table(name="RM_USER")
public class User extends Person implements Serializable {

	/**
	 * Generated serialVersionUID = -5076399887689393031L
	 */
	private static final long serialVersionUID = -5076399887689393031L;

	private Long userId;
	private String userLoginId;
	private String password;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
