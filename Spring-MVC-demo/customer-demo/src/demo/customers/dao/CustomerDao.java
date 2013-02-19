package demo.customers.dao;

import java.util.List;

import demo.customers.model.Customer;

public interface CustomerDao {

	List<Customer> getAllCustomers();
	
	Customer  getByID(Long id);
	
	Customer save(Customer customer);
	
	Long delete(Long id);
}
