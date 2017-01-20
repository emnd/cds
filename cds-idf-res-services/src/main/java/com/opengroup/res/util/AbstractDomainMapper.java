package com.opengroup.res.util;

import com.opengroup.res.core.domain.DomainBean;
import com.opengroup.res.core.domain.DomainException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The main mapper abstract class which provides method to transform model layer to another one.
 *
 * @author Open group
 * @since 1.0.0
 *
 * @param <D> Domain bean
 * @param <E> Entity bean
 */
public abstract class AbstractDomainMapper<D extends DomainBean, E extends EntityBean> implements DomainMapper<D,E> {

    /**
     * Many to many mapping
     * @param entityBeans
     * @return
     * @throws DomainException
     */
    public List<D> toDomains(Collection<E> entityBeans) throws DomainException{
        List<D> domains = new ArrayList<>();
        if(entityBeans != null){
            for(E bean : entityBeans){
                domains.add(toOneDomain(bean));
            }
        }
        return domains;
    }

    /**
     * Many to many mapping
     * @param domainBeans
     * @return
     * @throws DomainException
     */
    public List<E> toEntities(Collection<D> domainBeans) {
        List<E> representations = new ArrayList<>();
        if(domainBeans != null){
            for(D bean : domainBeans){
                representations.add(toOneEntity(bean));
            }
        }
        return representations;
    }
}
