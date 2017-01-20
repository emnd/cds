package com.opengroup.res.util.mappers;

import com.opengroup.res.core.domain.DomainBean;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.util.FrontException;
import com.opengroup.res.util.RepresentationBean;

/**
 * This is the contract to implements for each mapper implementation.<br/>
 * Bidirectional mapping is required here
 *
 * @author Open group
 * @since 1.0.0
 *
 * @param <D> Domain bean
 * @param <R> Representation bean
 *
 */
interface FrontMapper<D extends DomainBean, R extends RepresentationBean> {

    /**
     *
     * @param representation
     * @return
     * @throws DomainException
     */
    D toOneDomain(R representation) throws DomainException;

    /**
     *
     * @param domain
     * @return
     */
    R toOneRepresentation(D domain);
}
