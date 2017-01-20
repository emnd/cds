package com.opengroup.res.util.mappers;

import com.opengroup.res.core.domain.DomainBean;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.util.FrontException;
import com.opengroup.res.util.RepresentationBean;

import java.util.*;

/**
 * The main mapper abstract class which provides method to transform model layer to another one.
 *
 * @author Open group
 * @since 1.0.0
 *
 * @param <D> Domain bean
 * @param <R> Representation bean
 */
public abstract class AbstractFrontMapper<D extends DomainBean, R extends RepresentationBean>  implements FrontMapper<D,R> {

    /**
     * Many to many mapping
     * @param representationBeans
     * @return
     * @throws DomainException
     */
    public List<D> toDomains(Collection<R> representationBeans) throws DomainException{
        List<D> domains = new ArrayList<>();
        if(representationBeans != null){
            for(R bean : representationBeans){
                domains.add(toOneDomain(bean));
            }
        }
        return domains;
    }

    /**
     * Many to many mapping
     * @param domainBeans
     * @return
     */
    public List<R> toRepresentations(Collection<D> domainBeans) {
        List<R> representations = new ArrayList<>();
        if(domainBeans != null){
            for(D bean : domainBeans){
                representations.add(toOneRepresentation(bean));
            }
        }
        return representations;
    }
}
