package com.opengroup.res.core.impl;

import com.opengroup.res.core.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Default implementation of EmailServices with Spring JavaMailSender tool
 *
 * @author Open group
 * @since 1.0.0
 */
@Service
public class EmailServicesImpl implements EmailServices {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(String to, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        InternetAddress internetAddress = new InternetAddress();
        internetAddress.setAddress("root@open.fr");
        Address[] addreses = new Address[]{internetAddress};
        message.addFrom(addreses);
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, true);
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(body, true);
        javaMailSender.send(message);
    }
}
