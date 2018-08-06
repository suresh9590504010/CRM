package com.project.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	
	public List<Customer> getCustomers() {
		
		//hibernate stuff.......
	Session currentSession= sessionFactory.getCurrentSession();
	
	Query<Customer> theQuery=currentSession.createQuery("from Customer order by firstName", Customer.class);
	
	List<Customer> customers=theQuery.getResultList();

	return customers;
	}

	@Override
	
	public void saveCustomer(Customer thecustomer) {
		Session currentSession=sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(thecustomer);
	}

	@Override
	public Customer getCustomers(int theId) {
		Session currentSession=sessionFactory.getCurrentSession();
		Customer thecustomer=currentSession.get(Customer.class, theId);
		return thecustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		Session currentSession=sessionFactory.getCurrentSession();
Query theQuery=currentSession.createQuery("delete from Customer where id=:customerId");
theQuery.setParameter("customerId",theId);
theQuery.executeUpdate();
	}



}
