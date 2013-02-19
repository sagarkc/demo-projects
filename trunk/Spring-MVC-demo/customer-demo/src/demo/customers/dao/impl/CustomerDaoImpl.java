package demo.customers.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import demo.customers.dao.CustomerDao;
import demo.customers.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao{

	private EntityManager entityManager;
	
	@PersistenceContext(unitName = "customer.persistence.uinit", type = PersistenceContextType.TRANSACTION)
	public void setEntityManager(EntityManager enmgr) {
		entityManager = enmgr;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public List<Customer> getAllCustomers() {
		Query query = this.entityManager.createQuery(
				"from "
				+ Customer.class.getName());
		return query.getResultList();
	}

	@Override
	public Customer getByID(Long id) {
		return getEntityManager().find(Customer.class, id);
	}

	@Override
	public Customer save(Customer customer) {
		return getEntityManager().merge(customer);
	}

	@Override
	public Long delete(Long id) {
		getEntityManager().remove(getByID(id));
		return id;
	}
	
	
	
	
}
