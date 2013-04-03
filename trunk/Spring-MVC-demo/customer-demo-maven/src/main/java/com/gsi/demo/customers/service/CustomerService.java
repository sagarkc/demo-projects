package com.gsi.demo.customers.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gsi.demo.customers.vo.CustomerVo;

@Transactional(propagation=Propagation.REQUIRED)
public interface CustomerService {

	List<CustomerVo> getAllCustomers();
	
	CustomerVo getByID(Long id); 
	
	CustomerVo save(CustomerVo customer);
	
	Long delete(Long id);
}
