/**
 * 
 */
package com.gs.question.master.service;

import java.util.List;

import com.gs.question.master.common.exception.ApplicationException;
import com.gs.question.master.model.entity.Question;
import com.gs.question.master.model.entity.User;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
public interface QuestionService {

	List<Question> getLatestQuestions(User user) throws ApplicationException;
	
	
}
