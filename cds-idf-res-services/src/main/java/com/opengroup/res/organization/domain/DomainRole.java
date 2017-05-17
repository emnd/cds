package com.opengroup.res.organization.domain;

/**
 * Defined all possible roles that an ODEADOM user have.<br/>
 * Notice tha all enumerations are prefixed with "ROLE_". Indeed it is a Spring prefix automatically add to the group declared in ldap.<br/>
 * Everything is also uppercase ! For instance if in LDAP a group has a name 'admin' so you must declare 'ROLE_ADMIN'
 *
 * @author Open groupe
 * @since 1.0.0
 */
public enum DomainRole {

	// le 15 fev 2017
    /*
     * Application role
     */
    ROLE_APPLICATION,ROLE_ADMIN,ROLE_CDSMANAGER,ROLE_USER,ROLE_ATTRIBUTE, ROLE_DP;
    //ROLE_ADMIN,ROLE_CDSMANAGER,ROLE_USER,ROLE_ATTRIBUTE, ROLE_DP;
    /*
    Feature roles

    ROLE_FINANCIALMODELMANAGER,
    ROLE_ACCOUNTANT,
    ROLE_FINANCIALASSISTANCEINITIATOR,
    ROLE_PAIEMENTPARAMETERSADMIN,
    ROLE_INPLACECONTROLLER,
    ROLE_DIPPRODUCTER,
    ROLE_ASSISTANCEREADER,
    ROLE_REFUNDEXECUTOR,
    ROLE_INPLACECONTROLLERMANAGER,
    ROLE_PAIEMENTRECORDVISA,
    ROLE_ASSISTANCEMANAGER,
    ROLE_PAIEMENTSCHEDULER,
    ROLE_LIQUIDATIONASSISTANCEUSER,
    ROLE_REFERENCESADMIN,
    ROLE_FINANCIALASSISTANCEINSTRUCTOR,
    ROLE_ASSISTANCESUPERVISOR,
    */
}
