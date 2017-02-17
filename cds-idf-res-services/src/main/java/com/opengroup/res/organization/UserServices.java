package com.opengroup.res.organization;

import com.opengroup.res.organization.domain.DomainRole;
import com.opengroup.res.organization.domain.DomainUser;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.organization.domain.DomainEmployee;

import java.security.Principal;
import java.util.List;

/**
 * The <code>UserServices</code> interface to define all operation permit with a <code>User</code>
 *
 * @author Open groupe
 * @since 1.0.0
 */

//test
public interface UserServices {

    /**
     * Abstract the connexion process of a user - Used by main configuration
     *
     * @param authContext
     * @throws DomainException
     */
    void connect(Object authContext) throws DomainException;

    /**
     * Give a context source to retrieve a user - Used by main configuration
     *
     * @return
     */
    Object getContextSource() throws DomainException;

    /**
     * Get a complete valid user instance
     *
     * @param principal
     * @return
     * @throws DomainException
     */
    DomainUser get(Principal principal) throws DomainException;

    /**
     * Get an employee from its unique identifier
     *
     * @param uid
     * @return an <code>Employee</code> or a <code>User</code> instance
     * @throws DomainException
     * @apiNote If employee has at least one role to access application so it is a User instance
     */
    DomainEmployee get(String uid) throws DomainException;

    /**
     *
     * @param employee
     * @param role
     * @throws DomainException
     */
    void grantPermission(DomainEmployee employee, DomainRole role) throws DomainException;

    /**
     *
     * @param user
     * @param role
     * @throws DomainException
     */
    void revokePermission(DomainUser user, DomainRole role) throws DomainException;

    /**
     * Define and store all permissions a employee user can have. It becomes a User.
     * Roles must not be empty
     *
     * @param employee
     * @param roles
     * @return an User instance
     * @throws DomainException
     */
    DomainUser definePermissions(DomainEmployee employee, List<DomainRole> roles) throws DomainException;
}
