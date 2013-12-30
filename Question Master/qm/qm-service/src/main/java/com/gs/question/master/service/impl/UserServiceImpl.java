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
	 * @see com.gs.question.master.service.UserService#activateUser(java.lang.String)
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	@Override
	public Long activateUser(String activationCode) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
