package com.opengroup.res.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

/**
 * Integration test on sending mail usage
 *
 * @author Open groupe
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MailServicesIntegrationTest {

    @Autowired
    private EmailServices emailServices;

    @Test
    public void sendEmailTest() throws MessagingException {
        emailServices.send("rch11270@dc2s.localdomain", "[OBJECT 2]", "Dear CHIR");
    }
}
