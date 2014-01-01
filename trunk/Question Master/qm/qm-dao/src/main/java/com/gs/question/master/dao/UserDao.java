/**
 * 
 */
package com.gs.question.master.dao;

import com.gs.question.master.model.entity.User;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 * 
 */
public interface UserDao {
	User getUserById(Long userId);

	Long updateUser(User user);

	Long saveOrUpdateUser(User user);

	Long saveUser(User user);

	void deleteUser(User user);
}
