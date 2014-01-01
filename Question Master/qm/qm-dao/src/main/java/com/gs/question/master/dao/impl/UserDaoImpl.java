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

	public User getUserById(Long userId ){
		return getEntityManager().find(User.class, userId);
	}

	@Override
	public Long updateUser(User user) {
		merge(user);
		flush();
		return user.getUserId();
	}

	@Override
	public Long saveOrUpdateUser(User user) {
		merge(user);
		flush();
		return user.getUserId();
	}
	
	/* (non-Javadoc)
	 * @see com.gs.question.master.dao.UserDao#deleteUser(com.gs.question.master.model.entity.User)
	 */
	@Override
	public void deleteUser(User user) {
		delete(user);
		flush();
	}
	
	
}
