package com.opengroup.res.core;

import com.opengroup.res.core.domain.DomainException;

import java.util.List;

/**
 * Service To print PDF
 *
 * @author Open group
 * @since 1.0.0
 */
public interface PrintPdfService {

    /**
     *
     * @param beanToPrint
     * @param templatePdf
     * @return
     * @throws DomainException
     */
    byte[] printPdf(List<Object> beanToPrint, String templatePdf) throws DomainException;
}
