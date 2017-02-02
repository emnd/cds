package com.opengroup.res.core.impl.mappers;

import org.springframework.stereotype.Component;

import com.opengroup.res.core.domain.DomainCustomer;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.jpa.entities.Customer;
import com.opengroup.res.util.AbstractDomainMapper;

/**
 * A project mapper
 */
@Component
public class CustomerMapper extends AbstractDomainMapper<DomainCustomer, Customer> {

    @SuppressWarnings("unused")
	@Override
    public DomainCustomer toOneDomain(Customer entity) throws DomainException {
        Long idCustomer = entity.getIdCustomer();
        String nameCustomer = entity.getNameCustomer();
        
       
        return toOneDomain(entity);
		//return DomainProject(nameProject, periodStart, periodEnd);

    }
 
	@Override
    public Customer toOneEntity(DomainCustomer domain) {
       
		Customer customer = new Customer(
                domain.getIdCustomer(),
                domain.getNameCustomer());
      
        return customer;
    }
}
