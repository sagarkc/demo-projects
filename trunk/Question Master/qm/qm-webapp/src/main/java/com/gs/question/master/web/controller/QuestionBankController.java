/**
 * 
 */
package com.gs.question.master.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gs.question.master.model.dto.QuestionBankDto;

/**
 * @author Sabuj Das | sabuj.das@gmail.com
 *
 */
@Controller
@RequestMapping("/question/bank")
public class QuestionBankController {

	@RequestMapping(method=RequestMethod.GET)
	public String showQuestionBankView(ModelMap modelMap){
		List<QuestionBankDto> allQuestionBanks = new ArrayList<>();
		for (int i = 0; i < 11; i++) {
			QuestionBankDto b = new QuestionBankDto();
			b.setBankId((long)i+1);
			b.setBankName("Bank - " + (i+1));
			b.setCreatedDate(new Date());
			allQuestionBanks.add(b);
		}
		modelMap.addAttribute("allQuestionBanks", allQuestionBanks);
		return "questionBankView";
	}
	
	
	@RequestMapping(value="/showAddForm", method=RequestMethod.GET)
	public String showQuestionBankAddFormView(ModelMap modelMap){
		
		modelMap.addAttribute("questionBank", new QuestionBankDto());
		return "questionBankAddFormView";
	}
	
	
	@RequestMapping(value="/showDetails/{bankId}", method=RequestMethod.GET)
	public String showQuestionBankDetailsView(ModelMap modelMap, @PathVariable("bankId") Long bankId){
		QuestionBankDto b = new QuestionBankDto();
		b.setBankId(1001L);
		b.setBankName("Bank - " + 1001);
		b.setCreatedDate(new Date());
		modelMap.addAttribute("questionBank", b);
		return "questionBankDetailsView";
	}
	
	@RequestMapping(value="/showEditForm/{bankId}", method=RequestMethod.GET)
	public String showQuestionBankEditView(ModelMap modelMap, @PathVariable("bankId") Long bankId){
		QuestionBankDto b = new QuestionBankDto();
		b.setBankId(1001L);
		b.setBankName("Bank - " + 1001);
		b.setCreatedDate(new Date());
		modelMap.addAttribute("questionBank", b);
		
		return "questionBankEditFormView";
	}
	
	@RequestMapping(value="/{bankId}/delete", method=RequestMethod.GET)
	public String deleteQuestionBank(ModelMap modelMap, @PathVariable("bankId") Long bankId){
		List<QuestionBankDto> allQuestionBanks = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			QuestionBankDto b = new QuestionBankDto();
			b.setBankId((long)i+1);
			b.setBankName("Bank - " + (i+1));
			b.setCreatedDate(new Date());
			allQuestionBanks.add(b);
		}
		modelMap.addAttribute("allQuestionBanks", allQuestionBanks);
		return "questionBankView";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveQuestionBank(QuestionBankDto questionBank, ModelMap modelMap){
		List<QuestionBankDto> allQuestionBanks = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			QuestionBankDto b = new QuestionBankDto();
			b.setBankId((long)i+1);
			b.setBankName("Bank - " + (i+1));
			b.setCreatedDate(new Date());
			allQuestionBanks.add(b);
		}
		allQuestionBanks.add(questionBank);
		modelMap.addAttribute("allQuestionBanks", allQuestionBanks);
		return "questionBankView";
	}
}
