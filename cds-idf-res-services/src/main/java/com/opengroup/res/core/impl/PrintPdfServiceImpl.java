package com.opengroup.res.core.impl;

import com.opengroup.res.core.PrintPdfService;
import com.opengroup.res.core.domain.DomainException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IReport template implementation for generate PDF with Jasper reports
 *
 * @author Open group
 * @since 1.0.0
 */
@Service
public class PrintPdfServiceImpl implements PrintPdfService {

    public static final String PDF = ".pdf";
    public static final String STR_DOR = ".";
    public static final int ZERO = 0;

    @Override
    public byte[] printPdf(List<Object> beanToPrint, String templatePdf) throws DomainException {
        try {
            JasperReport jasperReport ;
            JasperPrint jasperPrint ;
            JasperDesign jasperDesign ;
            Map parameters = new HashMap();
            jasperDesign = JRXmlLoader.load(templatePdf);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(beanToPrint));
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new DomainException("Exception",e);
        }
    }
}
