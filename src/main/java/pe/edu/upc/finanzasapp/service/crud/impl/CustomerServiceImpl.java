package pe.edu.upc.finanzasapp.service.crud.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upc.finanzasapp.model.entity.Customer;
import pe.edu.upc.finanzasapp.model.repository.CustomerRepository;
import pe.edu.upc.finanzasapp.service.crud.CustomerService;

public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public JpaRepository<Customer, Integer> getRepository() {
		
		return customerRepository;
	}

}
