package demo.customers.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.customers.dao.CustomerDao;
import demo.customers.model.Customer;
import demo.customers.service.CustomerService;
import demo.customers.vo.CustomerVo;
import demo.customers.vo.CustomerVoConverter;

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
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
