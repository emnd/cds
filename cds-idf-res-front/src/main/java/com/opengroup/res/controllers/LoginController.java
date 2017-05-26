package com.opengroup.res.controllers;

import com.opengroup.res.ldap.LdapUserRepository;
import com.opengroup.res.organization.UserServices;
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
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingException;
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
            list.add(person.getEmail());
        }
        catch(ZdaoException e) {e.printStackTrace();}
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://10.170.23.196:389");
        env.put(Context.SECURITY_AUTHENTICATION,"user");
        env.put(Context.SECURITY_PRINCIPAL, "ou=groups"); //cn=application,ou=groups
        env.put(Context.SECURITY_CREDENTIALS, "1");

        DirContext dirContext = null;

        try {
           dirContext = new InitialDirContext(env);
            System.out.println("connected");
            //Object objet =  dirContext.lookup("cn=user,ou=groups");
            //System.out.println("champ = "+objet.toString());
           dirContext.close();
        } catch (NamingException e) {
            System.err.println("Erreur lors de l'acces au serveur LDAP" + e);
            e.printStackTrace();
        }



        return list;
    }
    // le 24-05-2017
}
