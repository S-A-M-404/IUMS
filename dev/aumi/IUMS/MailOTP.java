package dev.aumi.IUMS;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;





public class MailOTP {
	
	public static void sendMail(String recepients, String name) {
		
		Properties p = new Properties();
		
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "587");
		
		String myAccount = "sam404.iums@gmail.com";
		String password = "mustaviCG4.00";
		
		Session s = Session.getInstance(p, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(myAccount, password);
			}
		});
		
		Message m = prepareMessage(s, myAccount, recepients, name);
		try {
			
			
			Transport.send(m);
		} catch (MessagingException e) {
			
			
		}
	}

	private static Message prepareMessage(Session s, String myAccount, String recepients, String name) {
		
		try {
			
			Random num =  new Random();
			
			int OTP = 10000000 + num.nextInt(99999999);
			
			StudentRegistrationPage.setOTPChecker(Integer.toString(OTP));
			TeacherRegistrationPage.setOTPChecker(Integer.toString(OTP));
			ForgotPasswordPage.setOTPChecker(Integer.toString(OTP));
			
			
			
			String msg = "<html>Hey <b>" + name + ",</b><br/>Your account varrification code is: " + "<b>" + OTP + "</b>" + "</html>";
			
			Message m = new MimeMessage(s);
			m.setFrom(new InternetAddress(myAccount));
			m.setRecipient(Message.RecipientType.TO, new InternetAddress(recepients));
			m.setSubject("Varrification mail from IUMS");
			m.setContent(msg, "text/html");
			
			return m;
			
		} catch (Exception e) {
		
		} 
		
		return null;
	}
	
	

}
