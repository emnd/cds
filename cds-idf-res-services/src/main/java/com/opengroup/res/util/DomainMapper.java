package com.opengroup.res.util;

import com.opengroup.res.core.domain.DomainBean;
import com.opengroup.res.core.domain.DomainException;

/**
 * This is the contract to implements for each mapper implementation.<br/>
 * Bidirectional mapping is required here
 *
 * @author Open group
 * @since 1.0.0
 *
 * @param <D> Domain bean
 * @param <E> Entity bean
 */
public interface DomainMapper<D extends DomainBean, E extends EntityBean> {

    /**
     *
     * @param entity
     * @return
     * @throws DomainException
     */
    D toOneDomain(E entity) throws DomainException;

    /**
     *
     * @param domain
     * @return
     */
    E toOneEntity(D domain);
}
