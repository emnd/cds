package com.opengroup.res.ldap;

import com.opengroup.res.ldap.entities.Person;
import com.opengroup.res.ldap.entities.PersonPermission;
import com.opengroup.res.util.ZdaoException;

import java.util.List;

/**
 * An interface to define all needed interaction with ldap on Users purpose
 *
 * @author Open groupe
 * @since 1.0.0
 */
public interface LdapUserRepository {

    /**
     * @param uid
     * @return a specific Person entity
     */
    Person getPerson(String uid) throws ZdaoException;

    /**
     * @param uid
     * @return
     */
    List<PersonPermission> getUserPermissions(String uid);

    /**
     * @return
     */
    List<Person> findAllUsers();
}
