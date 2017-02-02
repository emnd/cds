package com.opengroup.res.core.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opengroup.res.core.CustomerServices;
import com.opengroup.res.core.domain.DomainCustomer;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.impl.mappers.CustomerMapper;
import com.opengroup.res.jpa.CustomerRepository;
import com.opengroup.res.jpa.entities.Customer;

/**
 * Implementation to manage customer service
 *
 * @author Open groupe
 * @since 1.0.0
 */
@Service
public class CustomerServicesImpl implements CustomerServices {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    @Transactional
    public void createCustomer(String nameCustomer) throws DomainException {
            DomainCustomer domainCustomer = DomainCustomer.createCustomer(nameCustomer);
            customerRepository.save(customerMapper.toOneEntity(domainCustomer));
       
    }

    @Override
    @Transactional
    public void updateCustomer(Long idCustomer, String nameCustomer) throws DomainException {
      
    	DomainCustomer domainCustomer = DomainCustomer.updateCustomer(idCustomer,nameCustomer);
        Customer customer = customerRepository.findOne(idCustomer);
        if (customer == null) {
            throw new DomainException("This customer does not exist");
        }
        customer.setNameCustomer(domainCustomer.getNameCustomer());

        customerRepository.save(customer);
    }

    
	@SuppressWarnings("unused")
	@Override
    @Transactional
    public void deleteCustomer(Long idCustomer) throws DomainException {
		DomainCustomer domainCustomer = DomainCustomer.deleteCustomer(idCustomer); 
                                      
        Customer existingCustomer = customerRepository.findOne(idCustomer);
       if (existingCustomer == null) {
            throw new DomainException("This project has not be found");}
       else
       {
    	   customerRepository.delete(existingCustomer);
       }
        
   }

  

    @Override
    @Transactional
    public Set<DomainCustomer> listAll() throws DomainException {
        return new HashSet<>(customerMapper.toDomains(customerRepository.findAll()));
    }

    @Override
    public <T extends DomainCustomer> void createCustomer(T typedCustomer) throws DomainException {
    	createCustomer(typedCustomer.getNameCustomer());
    }

    @Override
    public <T extends DomainCustomer> void updateCustomer(T typedCustomer) throws DomainException {
        updateCustomer(typedCustomer.getIdCustomer(), typedCustomer.getNameCustomer());
    }

    @Override
    public <T extends DomainCustomer> void deleteCustomer(T typedCustomer) throws DomainException {
    	deleteCustomer(typedCustomer.getIdCustomer());
    }
}