package com.opengroup.res.util;

import com.opengroup.res.core.domain.DomainBean;
import com.opengroup.res.core.domain.DomainException;

import java.util.Collection;

/**
 * This is the contract to implements for each mapper implementation.<br/>
 * You can use many different entity beans to build one domain bean or
 * you can use many different domain beans to build one entity bean.
 *
 * Note that it extends <code>DomainMapper</code> so implementations must override all methods
 *
 * @author Open group
 * @since 1.0.0
 *
 * @param <D> Domain bean
 * @param <E> Entity bean
 */
public interface DomainMultiSourceMapper<D extends DomainBean, E extends EntityBean> {

    /**
     *
     * @param entityBeans
     * @return
     * @throws DomainException
     */
    D toOneDomain(Collection<? extends EntityBean> entityBeans) throws DomainException;


    /**
     *
     * @param domainBeans
     * @return
     * @throws DomainException
     */
    E toOneEntity(Collection<? extends DomainBean> domainBeans) throws ZdaoException;
}
