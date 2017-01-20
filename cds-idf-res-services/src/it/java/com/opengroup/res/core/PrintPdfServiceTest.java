package com.opengroup.res.core;


import com.opengroup.res.core.domain.DomainException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

/**
 * Class test service print pdf
 *
 * @author Open group
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class PrintPdfServiceTest {
    /**
     * Path to put generated PDF
     */
    public static final String SRC_TEST_GENERATED_DDE_AIDE_PDF = "target/pdf/DemandeAide.pdf";

    @Autowired
    private PrintPdfService printPdfService;

    @Test
    public void testRecuperationPdf() {

        try {
            File file = new File(SRC_TEST_GENERATED_DDE_AIDE_PDF);
            FileUtils.writeByteArrayToFile(file, printPdfService.printPdf(PrintMock.getDeclarationDemandeList(), PrintMock.PATH_DEMANDE_AIDE_JRXML_NEW_ONE));
            Assert.assertTrue(file.exists());
            Assert.assertTrue(file.length() > 0);
            Assert.assertTrue(file.canRead());
        } catch (IOException | DomainException e) {
            Assert.fail("Exception IOException|ScmDomainException " + e.getMessage());
        }
    }

    /**
     * Setter PrintServcie
     *
     * @param printPdfService
     */
    public void setPrintPdfService(PrintPdfService printPdfService) {
        this.printPdfService = printPdfService;
    }

    /**
     * scan all package necessary to run test
     * SpringConfig
     */
    @Configuration
    @ComponentScan("com.opengroup.res.business")
    public static class SpringConfig {

    }
}
