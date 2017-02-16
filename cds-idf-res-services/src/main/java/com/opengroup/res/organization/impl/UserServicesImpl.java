package com.opengroup.res.organization.impl;

import com.opengroup.res.ldap.LdapUserRepository;
import com.opengroup.res.ldap.entities.Person;
import com.opengroup.res.ldap.LdapPermissionRepository;
import com.opengroup.res.core.domain.DomainException;
import com.opengroup.res.ldap.LdapAccess;
import com.opengroup.res.organization.UserServices;
import com.opengroup.res.organization.domain.*;
import com.opengroup.res.util.ZdaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Implementation of <code>UserServices</code>
 * Please adapat or remove ...
 * @author Open groupe
 * @since 1.0.0
 */
@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private LdapAccess ldapAccess;

    @Autowired
    private LdapUserRepository ldapUser;

    @Autowired
    private LdapPermissionRepository ldapPermissionRepository;

    @Override
    public void connect(Object authContext) throws DomainException {
        if (authContext instanceof AuthenticationManagerBuilder) {
            try {
                ldapAccess.authenticate((AuthenticationManagerBuilder) authContext);
            } catch (ZdaoException e) {
                throw new DomainException("A problem occurs while authenticating", e);
            }
        } else {
            throw new DomainException("Authentication context not managed");
        }
    }

    @Override
    public Object getContextSource() throws DomainException {
        try {
            return ldapAccess.getContext();
        } catch (ZdaoException e) {
            throw new DomainException("No source context found to retrieve user details", e);
        }
    }

    @Override
    public DomainUser get(Principal principal) throws DomainException {
        //Control parameter
        if (principal == null) {
            throw new DomainException("A principal object must be provided");
        }
        //Control that principal has roles
        UserDetails currentUser = (UserDetails) ((Authentication) principal).getPrincipal();
        Collection<? extends GrantedAuthority> authorities = currentUser.getAuthorities();
        if (authorities == null || authorities.isEmpty()) {
            throw new DomainException("");
        }
        //Change in a simple list of string
        List<String> roles = authorities.stream().map((Function<GrantedAuthority, String>) GrantedAuthority::getAuthority).collect(Collectors.toList());
        //Build a valid user
        try {
            Person person = ldapUser.getPerson(principal.getName());
            DomainIdentity identity = DomainIdentity.newInstance(currentUser.getUsername(), person.getCommonName(), person.getName(), person.getGivenName(), person.getEmail());
            DomainJob job = DomainJob.newInstance("Manager", "Direction");
            DomainCoordinate coordinate = DomainCoordinate.newInstance(person.getEmail(), "0125698745");
            return DomainUser.newInstance(identity, job, coordinate, roles);
        } catch (ZdaoException e) {
            throw new DomainException("A problem occurs while retrieving person details, reason :", e);
        }
    }

    @Override
    public DomainEmployee get(String uid) throws DomainException {
        try {
            Person person = ldapUser.getPerson(uid);
            DomainIdentity identity = DomainIdentity.newInstance(person.getUid(), person.getCommonName(), person.getName(), person.getGivenName(),person.getEmail());
            DomainJob job = DomainJob.newInstance("Manager", "Direction");
            DomainCoordinate coordinate = DomainCoordinate.newInstance(person.getEmail(), "0125698745");
            return DomainEmployee.newInstance(identity, job, coordinate);
        } catch (ZdaoException e) {
            throw new DomainException("A problem occurs", e);
        }
    }

    @Override
    public void grantPermission(DomainEmployee employee, DomainRole role) throws DomainException {
        try {
            ldapPermissionRepository.attachPerson(role.toString(), employee.getIdentity().getIdentifier());
        } catch (ZdaoException e) {
            throw new DomainException("Unable to grant permission", e);
        }

    }

    @Override
    public void revokePermission(DomainUser user, DomainRole role) throws DomainException {
        try {
            ldapPermissionRepository.detachPerson(role.toString(), user.getIdentity().getIdentifier());
        } catch (ZdaoException e) {
            throw new DomainException("Unable to revoke permission", e);
        }
    }

    @Override
    public DomainUser definePermissions(DomainEmployee employee, List<DomainRole> roles) throws DomainException {
        return null;
    }


}
