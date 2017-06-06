package com.opengroup.res.controllers;

import com.opengroup.res.ldap.LdapUserRepository;
import com.opengroup.res.organization.UserServices;
import com.opengroup.res.organization.domain.DomainUser;
import com.opengroup.res.util.ZdaoConfiguration;
import com.opengroup.res.util.ZdaoConfigurationImpl;
import com.opengroup.res.util.ZdaoException;
import org.springframework.beans.factory.annotation.Autowired;
import com.opengroup.res.ldap.entities.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Base64;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by MND15281 on 24/05/2017.
 */
@RestController
public class LoginController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private LdapUserRepository ldapUser;

    @Autowired
    ZdaoConfiguration zdaoConfiguration;
    // le 24-05-2017
    @RequestMapping(value="/services/user/{username}/{password}", method= RequestMethod.GET)
    public List<String> userInfos(@PathVariable String username, @PathVariable String password)
    {
        List<String> list = new ArrayList<String>();
        System.out.println("Je recherche "+username+" "+password);
        try{
            Person person = ldapUser.getPerson(username);
            System.out.println("Email : "+person.getEmail());
            System.out.println("commonName : "+person.getCommonName());
            String pwd = person.getUserPassword();
            System.out.println("userPassword : "+person.getUserPassword());
            char charAscii = password.charAt(0);
            int pwdEncod = (int) charAscii;
            System.out.println("Password saisi : "+password+" codé en ASCII : "+pwdEncod);
            if(person.getUserPassword().equals(Integer.toString(pwdEncod)))
            {
                System.out.println("OK");
                list.add(username);
                list.add(password);
                list.add(person.getEmail());
            }
            else
            {    System.out.println("KO");}

        }
        catch(ZdaoException e) {e.printStackTrace();}


        return list;
    }
    // le 24-05-2017
}
