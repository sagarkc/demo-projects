package com.gsi.demo.customers.vo;

import com.gsi.demo.customers.model.Customer;

public class CustomerVoConverter {

	public static CustomerVo convertToVo(Customer customer){
		if(null == customer){
			return null;
		}
		CustomerVo vo = new CustomerVo();
		
		vo.setId(customer.getId());
		vo.setName(customer.getName());
		vo.setIncome(customer.getIncome());
		vo.setAddress(customer.getAddress());
		
		return vo;
	}
	
	public static Customer convertToModel(CustomerVo customerVo){
		if(null == customerVo){
			return null;
		}
		Customer vo = new Customer();
		
		vo.setId(customerVo.getId());
		vo.setName(customerVo.getName());
		vo.setIncome(customerVo.getIncome());
		vo.setAddress(customerVo.getAddress());
		
		return vo;
	}
	
}
