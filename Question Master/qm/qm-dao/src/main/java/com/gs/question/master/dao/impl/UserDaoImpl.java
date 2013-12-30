/**
 * 
 */
package com.gs.question.master.dao.impl;

import org.springframework.stereotype.Repository;

import com.gs.question.master.dao.UserDao;
import com.gs.question.master.model.entity.User;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

	@Override
	public Long saveUser(User user) {
		persist(user);
		flush();
		return user.getUserId();
	}

	
}
