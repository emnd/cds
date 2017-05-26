package com.opengroup.res.controllers;

import com.opengroup.res.organization.UserServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.security.Principal;

/**
 * This controller contains logic to deliver the single page application.<br>
 * Before delivering the main page the user who has been authenticated must be controlled to have the required roles to access the app
 * or any logic such as "does user have an email ?" or something like this
 *
 * @author Open groupe
 * @since 1.0.0
 */
@Controller
public class FrontController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FrontController.class);

    @Autowired
    private UserServices userServices;

    /**
     * Post authentication controller, in charge to control access of the authenticated user and redirect him to an error or the master page
     *
     * @param principal
     * @return the name of the return page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String master(Principal principal) {
        try {
            userServices.get(principal);
            return "index.html";
        } catch (Exception e) {
            LOGGER.error("A problem occurs while controlling the authenticated user", e);
            return "auth-error.html";
        }
    }


}