package com.gsi.demo.customers.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsi.demo.customers.dao.CustomerDao;
import com.gsi.demo.customers.model.Customer;
import com.gsi.demo.customers.service.CustomerService;
import com.gsi.demo.customers.vo.CustomerVo;
import com.gsi.demo.customers.vo.CustomerVoConverter;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDao customerDao;
	
	
	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public List<CustomerVo> getAllCustomers() {
		List<Customer> models = getCustomerDao().getAllCustomers();
		if(null != models){
			List<CustomerVo> vos = new ArrayList<CustomerVo>();
			for (Customer customer : models) {
				vos.add(CustomerVoConverter.convertToVo(customer));
			}
			return vos;
		}
		return null;
	}

	@Override
	public CustomerVo getByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerVo save(CustomerVo customer) {
		Customer cust = CustomerVoConverter.convertToModel(customer);
		getCustomerDao().save(cust);
		return customer;
	}

	@Override
	public Long delete(Long id) {
		getCustomerDao().delete(id);
		return null;
	}
	
	
}
