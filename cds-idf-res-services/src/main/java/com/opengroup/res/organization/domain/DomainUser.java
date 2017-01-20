package com.opengroup.res.organization.domain;

import com.opengroup.res.core.domain.DomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User model. A user is an identified person approved by the organization and this application of assistance management.<br/>
 * It's important to know the required attributes of a such user and so control its creation.
 * <p>
 * User must have a required role to access the application.
 *
 * @author Open groupe
 * @since 1.0.0
 */
public final class DomainUser extends DomainEmployee {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainUser.class);

    private Set<DomainRole> roles;

    private DomainUser(DomainIdentity identity, DomainJob job, DomainCoordinate coordinate, List<String> roleNames) throws DomainException {
        super(identity, job, coordinate);

        if (roleNames == null || roleNames.isEmpty()) {
            constraintsErrors.add("There is no roles configured for this user identifier : " + identity);
        }

        this.roles = new HashSet<>();

        for (String roleName : roleNames) {
            // Take care if he has others roles on others resources
            try {
                DomainRole role = DomainRole.valueOf(roleName);
                this.roles.add(role);
            } catch (IllegalArgumentException e) {
                //Just log in debug mode because it's a current situation, a user can have others roles in SI
                LOGGER.debug("Role not managed", e);
            }
        }

        if (!this.roles.contains(DomainRole.ROLE_APPLICATION)) {
            constraintsErrors.add("No roles on application for user");
        }

        checkIfValid();
    }

    /**
     * Build and control that user can access the application. It contains the required attributes to create a user
     *
     * @param identity
     * @param job
     * @param coordinate
     * @param roleNames
     * @return
     * @throws DomainException
     */
    public static DomainUser newInstance(DomainIdentity identity, DomainJob job, DomainCoordinate coordinate, List<String> roleNames) throws DomainException {
        return new DomainUser(identity, job, coordinate, roleNames);
    }

    /**
     * Gets roles.
     *
     * @return Value of roles.
     */
    public Set<DomainRole> getRoles() {
        return roles;
    }
}
