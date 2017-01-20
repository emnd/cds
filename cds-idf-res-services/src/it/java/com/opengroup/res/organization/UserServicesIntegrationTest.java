package com.opengroup.res.organization;

import com.opengroup.res.ldap.LdapUserRepository;
import com.opengroup.res.ldap.entities.Permission;
import com.opengroup.res.ldap.entities.Person;
import com.opengroup.res.ldap.entities.PersonPermission;
import com.opengroup.res.util.ZdaoException;
import com.opengroup.res.ldap.LdapPermissionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Integration test on user
 *
 * @author Open groupe
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServicesIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServicesIntegrationTest.class);

    @Autowired
    private LdapUserRepository ldapUser;

    @Autowired
    private LdapPermissionRepository ldapPermissionRepository;

    @Autowired
    private UserServices userServices;

    @Test
    public void getUserTest() {
    }

    @Test
    public void getUserDetailsTest() {
        String uid = "RCH11270";
        Person person;
        try {
            person = ldapUser.getPerson(uid);
            if (person != null) {
                LOGGER.info("A person has been found for uid = " + uid);
                LOGGER.info("dn = " + person.getDistinguishName());
                LOGGER.info("cn = " + person.getCommonName());
                LOGGER.info("sn = " + person.getName());
                LOGGER.info("gn = " + person.getGivenName());
                LOGGER.info("email = " + person.getEmail());
                LOGGER.info("uid = " + person.getUid());
            } else {
                LOGGER.info("No person's found for uid = " + uid);
            }
            List<PersonPermission> userPermissions = ldapUser.getUserPermissions(uid);
            if (userPermissions != null && !userPermissions.isEmpty()) {
                for (PersonPermission permission : userPermissions) {
                    LOGGER.info("Found permission = " + permission.getCommonName());
                }
            }
        } catch (ZdaoException e) {
            LOGGER.info("An error occurs while retrieving user, cause :");
        }
    }

    @Test
    public void getAllPermissionsTest() {
        List<Permission> allPermissions = ldapPermissionRepository.findAllPermissions();
        if (allPermissions != null && !allPermissions.isEmpty()) {
            for (Permission permission : allPermissions) {
                List<String> uniqueMembers = permission.getUniqueMembers();
                LOGGER.info("Permission " + permission.getCommonName() + " has " + (uniqueMembers != null ? uniqueMembers.size() : 0) + " member(s)");
            }
        }
    }

    @Test
    public void attachPersonToPermissionsTest() {
        try {
            ldapPermissionRepository.attachPerson("FINANCIALMODELMANAGER", "D123456");
        } catch (ZdaoException e) {
            LOGGER.info("Can't attach person to this permission, reason : ", e);
        }
    }

    @Test
    public void detachPersonToPermissionsTest() {
        try {
            ldapPermissionRepository.detachPerson("FINANCIALMODELMANAGER", "D123456");
        } catch (ZdaoException e) {
            LOGGER.info("Can't detach person to this permission, reason : ", e);
        }
    }
}
