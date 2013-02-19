package demo.customers.dao;

import java.util.List;

import demo.customers.model.Customer;

public interface CustomerDao {

	List<Customer> getAllCustomers();
	
}
