package com.opengroup.res.ldap;

import com.opengroup.res.ldap.entities.Permission;
import com.opengroup.res.util.ZdaoException;

import java.util.List;

/**
 * An interface to define all needed interaction with ldap on Permission purpose
 *
 * @author Open groupe
 * @apiNote Every research are executed inside node define in application.properties
 * @since 1.0.0
 */
public interface LdapPermissionRepository {

    /**
     * Based on group search base configuration
     *
     * @return all configured ldap permission
     */
    List<Permission> findAllPermissions();

    /**
     * Retrieve a permission based on its cn
     *
     * @param permissionCommonName
     * @return
     * @throws ZdaoException
     */
    Permission getPermission(String permissionCommonName) throws ZdaoException;

    /**
     * Bind an existing user person to a group permission
     *
     * @param permissionCommonName
     * @param uid
     * @throws ZdaoException if permission or user doesn't exist or if users is already attached to this permission
     */
    void attachPerson(String permissionCommonName, String uid) throws ZdaoException;

    /**
     * Remove an existing user person from a group permission
     *
     * @param permissionCommonName
     * @param uid
     * @throws ZdaoException if permission or user doesn't exist or if users is already attached to this permission
     */
    void detachPerson(String permissionCommonName, String uid) throws ZdaoException;
}
