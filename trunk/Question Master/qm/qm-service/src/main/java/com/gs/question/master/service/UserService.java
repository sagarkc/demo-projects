/**
 * 
 */
package com.gs.question.master.service;

import com.gs.question.master.common.exception.ApplicationException;
import com.gs.question.master.model.dto.UserDto;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface UserService {

	Long registerUser(UserDto userDto) throws ApplicationException;
	
	Long activateUser(String activationCode) throws ApplicationException;
	
	
}
