/**
 * 
 */
package com.gs.question.master.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gs.question.master.common.exception.ApplicationException;
import com.gs.question.master.common.utils.DateUtil;
import com.gs.question.master.dao.UserDao;
import com.gs.question.master.model.dto.UserDto;
import com.gs.question.master.model.entity.User;
import com.gs.question.master.service.UserService;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	/* (non-Javadoc)
	 * @see com.gs.question.master.service.UserService#registerUser(com.gs.question.master.model.dto.UserDto)
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	@Override
	public Long registerUser(UserDto userDto) throws ApplicationException {
		userDto.setActivationExpireBy(DateUtil.addSeconds(new Date(), 3600));
		User user = userDto.getEntity();
		
		return userDao.saveUser(user);
	}

	/* (non-Javadoc)
	 * @see com.gs.question.master.service.UserService#activateUser(java.lang.Long, java.lang.String)
	 */
	@Override
	public boolean activateUser(Long userId, String activationCode)
			throws ApplicationException {
		User user = userDao.getUserById(userId);
		if(null != user 
				&& null != user.getActivationCode() 
				&& user.getActivationCode().equals(activationCode)
				&& null != user.getActivationExpireBy()
				&& user.getActivationExpireBy().compareTo(new Date()) >= 0){
			user.setActivated(true);
			user.setActivationCode(null);
			user.setActivationExpireBy(null);
			userDao.updateUser(user);
			return true;
		}
		return false;
	}

}
