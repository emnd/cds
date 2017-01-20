package com.opengroup.res.ldap.impl;

import com.opengroup.res.ldap.entities.Permission;
import com.opengroup.res.ldap.entities.Person;
import com.opengroup.res.util.ZdaoException;
import com.opengroup.res.ldap.LdapPermissionRepository;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Repository;

import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * The main implementation of <code>LdapPermissionRepository.class</code>
 * Note that it depends of search base configuration on users and groups
 *
 * @Author Open group
 * @since 1.0.0
 */
@Repository
public class LdapPermissionRepositoryImpl extends AbstractLdapRepository implements LdapPermissionRepository {

    @Override
    public List<Permission> findAllPermissions() {
        return ldapTemplate.findAll(Permission.class);
    }

    @Override
    public Permission getPermission(String permissionCommonName) throws ZdaoException {
        LdapQuery query = query().base(zdaoConfiguration.getLdapDatasourceScmGroupSearchBase()).filter("(cn=" + permissionCommonName + ")");
        Permission permission = ldapTemplate.findOne(query, Permission.class);
        if (permission == null) {
            throw new ZdaoException("No permission has been found");
        }
        return permission;
    }

    @Override
    public void attachPerson(String permissionCommonName, String uid) throws ZdaoException {
        Permission permission = getPermission(permissionCommonName);
        Person person = super.getPerson(uid);
        List<String> uniqueMembers = permission.getUniqueMembers();
        String userDn = person.getDistinguishName().toString();
        if (uniqueMembers != null && !uniqueMembers.isEmpty() && uniqueMembers.contains(userDn)) {
            throw new ZdaoException("This permission is already granted to the user with dn = " + userDn);
        }
        ModificationItem item = new ModificationItem(DirContext.ADD_ATTRIBUTE, new BasicAttribute("uniqueMember", userDn));
        ModificationItem[] mods = {item};
        ldapTemplate.modifyAttributes(permission.getDistinguishName(), mods);
    }

    @Override
    public void detachPerson(String permissionCommonName, String uid) throws ZdaoException {
        Permission permission = getPermission(permissionCommonName);
        Person person = super.getPerson(uid);
        List<String> uniqueMembers = permission.getUniqueMembers();
        String userDn = person.getDistinguishName().toString();
        if (uniqueMembers != null && !uniqueMembers.isEmpty() && !uniqueMembers.contains(userDn)) {
            throw new ZdaoException("This permission is already not granted to the user with dn = " + userDn);
        }
        ModificationItem item = new ModificationItem(DirContext.REMOVE_ATTRIBUTE, new BasicAttribute("uniqueMember", userDn));
        ModificationItem[] mods = {item};
        ldapTemplate.modifyAttributes(permission.getDistinguishName(), mods);
    }
}
