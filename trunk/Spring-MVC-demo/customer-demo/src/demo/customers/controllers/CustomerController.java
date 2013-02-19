package demo.customers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.customers.vo.CustomerVo;

@Controller
@RequestMapping("/cust")
public class CustomerController {

	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String showAddPage(){
		return "add-customer-view";
	}
	
	@RequestMapping(value="/update/${customerId}", method=RequestMethod.GET)
	public String showUpdatePage(@PathVariable Long customerId){
		return "update-customer-view";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(CustomerVo customer){
		return "redirect:/index.htm";
	}
	
	@RequestMapping(value="/delete/${customerId}", method=RequestMethod.GET)
	public String delete(@PathVariable Long customerId){
		return "redirect:/index.htm";
	}
}
