package com.opengroup.res.business.impl;

import com.opengroup.res.business.ProductServices;
import com.opengroup.res.core.ParameterServices;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.business.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Service Implementation
 *
 * @author Open group
 * @since 1.0.0
 */
@Service
public class ProductServicesImpl implements ProductServices {

    @Autowired
    private ParameterServices parameterServices;

    @Override
    public void create(String code, String value, String userId) throws DomainException {
        parameterServices.createParameter(Product.newCreatedStateInstance(code, value, userId));
    }

    @Override
    public void update(String code, String value, String userId) throws DomainException {
        parameterServices.updateParameter(Product.newModifiedStateInstance(code, value, userId));
    }

    @Override
    public void delete(String code, String value, String userId) throws DomainException {
        parameterServices.deleteParameter(Product.newSuppressedStateInstance(code, value, userId));
    }

    @Override
    public List<Product> list() throws DomainException {
        return Collections.emptyList();
    }
}

