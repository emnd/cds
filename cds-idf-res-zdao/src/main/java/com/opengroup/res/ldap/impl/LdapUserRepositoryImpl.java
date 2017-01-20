package com.opengroup.res.ldap.impl;

import com.opengroup.res.ldap.LdapUserRepository;
import com.opengroup.res.ldap.entities.Person;
import com.opengroup.res.ldap.entities.PersonPermission;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * Implementation of LdapUserRepository in charge to provide additional information about a user
 *
 * @author Open groupe
 * @since 1.0.0
 */
@Repository
public class LdapUserRepositoryImpl extends AbstractLdapRepository implements LdapUserRepository {

    @Override
    public List<PersonPermission> getUserPermissions(String uid) {
        LdapQuery query = query().base(zdaoConfiguration.getLdapDatasourceScmGroupSearchBase()).filter("(uniqueMember=uid=" + uid + ",ou=partners,o=PSA)");
        return ldapTemplate.find(query, PersonPermission.class);
    }

    @Override
    public List<Person> findAllUsers() {
        return ldapTemplate.findAll(Person.class);
    }

}
