package demo.customers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.customers.service.CustomerService;
import demo.customers.vo.CustomerVo;

@Controller
@RequestMapping("/cust")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String showAddPage(ModelMap modelMap){
		modelMap.addAttribute("customer", new CustomerVo());
		return "add-customer-view";
	}
	
	@RequestMapping(value="/update/{customerId}", method=RequestMethod.GET)
	public String showUpdatePage(@PathVariable Long customerId){
		return "update-customer-view";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(CustomerVo customer){
		getCustomerService().save(customer);
		return "redirect:/index.htm";
	}
	
	@RequestMapping(value="/delete/{customerId}", method=RequestMethod.GET)
	public String delete(@PathVariable Long customerId){
		getCustomerService().delete(customerId);
		return "redirect:/index.htm";
	}
}
