package demo.customers.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import demo.customers.service.CustomerService;
import demo.customers.vo.CustomerVo;

@Controller
@RequestMapping("/index")
public class HomeController {

	@Autowired
	private CustomerService customerService;
	
	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@RequestMapping(method=RequestMethod.GET)
	public String showHomePage(ModelMap modelMap){
		List<CustomerVo> l = getCustomerService().getAllCustomers();
		if(null != l){
			modelMap.addAttribute("allCustomers", l);
		} else {
			modelMap.addAttribute("allCustomers", new ArrayList<CustomerVo>());
		}
		return "home-page-view";
	}
	
}
