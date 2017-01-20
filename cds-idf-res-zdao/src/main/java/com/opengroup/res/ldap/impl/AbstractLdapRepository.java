package com.opengroup.res.ldap.impl;

import com.opengroup.res.ldap.entities.Person;
import com.opengroup.res.util.ZdaoConfiguration;
import com.opengroup.res.util.ZdaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * The abstract root of LDAP repository services
 *
 * @author Open group
 * @since 1.0.0
 */
public abstract class AbstractLdapRepository {

    @Autowired
    protected LdapTemplate ldapTemplate;

    @Autowired
    protected ZdaoConfiguration zdaoConfiguration;

    /**
     * Retrieve a person based on his uid - Note that base node is parametrized
     *
     * @param uid
     * @return Person object
     * @throws ZdaoException
     */
    public Person getPerson(String uid) throws ZdaoException {
        LdapQuery query = query().base(zdaoConfiguration.getLdapDatasourceScmUserSearchBase()).filter("(uid=" + uid + ")");
        Person person = ldapTemplate.findOne(query, Person.class);
        if (person == null) {
            throw new ZdaoException("No user has been found");
        }
        return person;
    }
}
