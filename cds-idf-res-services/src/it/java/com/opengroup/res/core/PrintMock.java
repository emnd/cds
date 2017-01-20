package com.opengroup.res.core;

import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.core.printbeans.*;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class Mock to print service
 *
 * @author Open group
 * @since 1.0.0
 */
public final class PrintMock {

    /**
     * Template JRXML
     */
    public static final String PATH_DEMANDE_AIDE_JRXML = new File("src/test/resources/templates/DemandeAide.jrxml").getPath();
    public static final String PATH_DEMANDE_AIDE_JRXML_NEW_ONE = new File("src/test/resources/templates/TemplateAll.jrxml").getPath();
    public static final String DEMANDEUR = "SCEA LES GALBAS";

    /**
     * .
     * get Object mock for print
     *
     * @return PrintRequestForAid
     * @throws DomainException
     */
    public static List<Object> getPrintRequestForAidMock() throws DomainException {

        List<ApplicantOrPartnerDeclarationCommitment> declarations = new ArrayList<>();
        declarations.add(ApplicantOrPartnerDeclarationCommitment.newInstacne("Ste Rose", BigDecimal.valueOf(19854), BigDecimal.valueOf(234.72), "1", "Belon"));
        declarations.add(ApplicantOrPartnerDeclarationCommitment.newInstacne("azdazda", BigDecimal.valueOf(45534), BigDecimal.valueOf(389.54), "1", "Belon"));
        declarations.add(ApplicantOrPartnerDeclarationCommitment.newInstacne("zdazdazdazd", BigDecimal.valueOf(73545), BigDecimal.valueOf(334.72), "1", "Belon"));
        declarations.add(ApplicantOrPartnerDeclarationCommitment.newInstacne("dazazsd", BigDecimal.valueOf(78373), BigDecimal.valueOf(434.72), "1", "Belon"));
        declarations.add(ApplicantOrPartnerDeclarationCommitment.newInstacne("azdazd a", BigDecimal.valueOf(34345), BigDecimal.valueOf(534.72), "1", "Belon"));
        declarations.add(ApplicantOrPartnerDeclarationCommitment.newInstacne("azdzzdse", BigDecimal.valueOf(84234), BigDecimal.valueOf(634.72), "1", "Belon"));
        declarations.add(ApplicantOrPartnerDeclarationCommitment.newInstacne("dadzd", BigDecimal.valueOf(23545), BigDecimal.valueOf(734.72), "1", "Belon"));
        declarations.add(ApplicantOrPartnerDeclarationCommitment.newInstacne("tyutyut", BigDecimal.valueOf(56453), BigDecimal.valueOf(834.72), "1", "Belon"));
        declarations.add(ApplicantOrPartnerDeclarationCommitment.newInstacne("ooipiop Rose", BigDecimal.valueOf(93432), BigDecimal.valueOf(934.72), "1", "Belon"));

        List<Object> requests = new ArrayList<>();

        requests.add(PrintRequestForAid.newInstance(DEMANDEUR, new Date(), declarations));

        return requests;
    }

    public static List<Object> getDeclarationDemandeList() throws DomainException {


        DeclarationRequest declarationDemande1 = DeclarationRequest.newInstance("Ste Rose", 19854, 233.4F, 1, "Belogn");
        DeclarationRequest declarationDemande2 = DeclarationRequest.newInstance("Ste Rose", 43254, 123.4F, 1, "Belogn");
        DeclarationRequest declarationDemande3 = DeclarationRequest.newInstance("Ste Rose", 13422, 543.3F, 1, "Belogn");
        DeclarationRequest declarationDemande4 = DeclarationRequest.newInstance("Ste Rose", 25543, 676.3F, 1, "Belogn");
        DeclarationRequest declarationDemande5 = DeclarationRequest.newInstance("Ste Rose", 45354, 543.5F, 1, "Belogn");
        DeclarationRequest declarationDemande6 = DeclarationRequest.newInstance("Ste Rose", 76553, 656.4F, 1, "Belogn");
        DeclarationRequest declarationDemande7 = DeclarationRequest.newInstance("Ste Rose", 25465, 242.7F, 1, "Belogn");
        DeclarationRequest declarationDemande8 = DeclarationRequest.newInstance("Ste Rose", 96555, 435.2F, 1, "Belogn");
        DeclarationRequest declarationDemande9 = DeclarationRequest.newInstance("Ste Rose", 54243, 845.7F, 1, "Belogn");
        DeclarationRequest declarationDemande10 = DeclarationRequest.newInstance("Ste Rose", 45654, 234.6F, 1, "Belogn");

        List<DeclarationRequest> declarationDemandeList = new ArrayList<>();

        declarationDemandeList.add(declarationDemande1);
        declarationDemandeList.add(declarationDemande2);
        declarationDemandeList.add(declarationDemande3);
        declarationDemandeList.add(declarationDemande4);
        declarationDemandeList.add(declarationDemande5);
        declarationDemandeList.add(declarationDemande6);
        declarationDemandeList.add(declarationDemande7);
        declarationDemandeList.add(declarationDemande8);
        declarationDemandeList.add(declarationDemande9);
        declarationDemandeList.add(declarationDemande10);

        List<Object> imprimerDeclarationDemande = new ArrayList<>();

        Date date = new Date();
        DetailsApplicant detailsDemande1 = DetailsApplicant.newInstance("En cours", date);

        List<DetailsApplicant> detailsDemandeList = new ArrayList<>();
        detailsDemandeList.add(detailsDemande1);

        imprimerDeclarationDemande.add(Applicant.newInstance("SCEA LES GABAS", "Chez Louis Theodore Roussel", "97129", "LAMENTIN", "0590942499", declarationDemandeList, detailsDemandeList));
        return imprimerDeclarationDemande;
    }
}
