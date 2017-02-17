package com.opengroup.res.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServices  {

	@Autowired
	private JavaMailSender javaMailSender;
	
	
	
	public void sendNotificationDecider(String firstName, String lastName, String loginOpen, String emailOpen) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("moussa.ndiaye@open-groupe.com");
		mail.setFrom("noreply@open-groupe.com");
		mail.setSubject("Demande d'autorisation au centre de service");
		mail.setText("Bonjour M. Sartre, vous avez une demande d'accès au centre de service pour le collaborateur : " + firstName + " "+ lastName +" "+ loginOpen );
		
		javaMailSender.send(mail);
		
		
	}
	public void sendNotificationDemander(String firstName, String lastName, String emailOpen) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emailOpen);
		mail.setFrom("noreply@open-groupe.com");
		mail.setSubject("Demande d'autorisation au centre de service");
		mail.setText("Bonjour" +" "+ firstName +" "+ lastName  + ", votre demande d'accès au centre de service a été envoyée");
		
		javaMailSender.send(mail);
		
		
	}
	
	public void sendNotificationDemanderAccepted(String firstName,String lastName ,String emailOpen) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emailOpen);
		mail.setFrom("noreply@open-groupe.com");
		mail.setSubject("Demande d'autorisation au centre de service : Acceptée");
		mail.setText("Bonjour" +" "+ firstName +" "+ lastName+" "+  ", votre demande d'accès au centre de service a été validée");
		
		javaMailSender.send(mail);
		
		
	}
	
	public void sendNotificationDemanderRefused(String firstName,String lastName ,String emailOpen) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emailOpen);
		mail.setFrom("noreply@open-groupe.com");
		mail.setSubject("Demande d'autorisation au centre de service : Refusée");
		mail.setText("Bonjour" +" "+ firstName +" "+ lastName+" "+  ", votre demande d'accès au centre de service a été refusée");
		
		javaMailSender.send(mail);
		
		
	}
}

