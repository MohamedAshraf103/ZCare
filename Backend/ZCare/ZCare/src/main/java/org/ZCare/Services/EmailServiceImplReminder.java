package org.ZCare.Services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.ZCare.DTO.Mail;

public class EmailServiceImplReminder implements EmailService {
	
	@Override
	public void sendCodeByEmail(Mail mail) {
		String to=mail.getTo();
		final String from = "ZCare2023@gmail.com";
		final String password = "aounzmvobhqirysl";
		Properties prop = System.getProperties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.password", "password");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(prop, 
			    new javax.mail.Authenticator(){
			        protected PasswordAuthentication getPasswordAuthentication() {
			            return new PasswordAuthentication(
			               from,password);// Specify the Username and the PassWord
			        }
			});			
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			String content ="Please visit the Mobile Application for more details about the upcoming Tasks."
					+ "Thank you . We look forward to seeing you better soon!"
					+"ZCare";
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Reminder");
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(content, "text/html");
			MimeMultipart multiPart=new MimeMultipart();
			multiPart.addBodyPart(mimeBodyPart);
			message.setContent(multiPart);
			String emailhost="smtp.gmail.com";
			Transport transport=session.getTransport("smtp");
			transport.connect(emailhost,from,password);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
			
		}
 }

	}


