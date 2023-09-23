package org.ZCare;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
	public static void main(String[] args) throws IOException {
		getIt();
		// Send();
	}

	/**
	 * Method handling HTTP GET requests. The returned object will be sent to the
	 * client as "text/plain" media type.
	 *
	 * @return String that will be returned as a text/plain response.
	 */
	/*
	 * @GET
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public static int Send() { int res=0; String
	 * to = "my1379@fayoum.edu.eg"; final String from = "ZCare2023@gmail.com"; final
	 * String password = "aounzmvobhqirysl"; Properties prop =
	 * System.getProperties(); prop.put("mail.smtp.host", "smtp.gmail.com");
	 * prop.put("mail.smtp.password", "password"); prop.put("mail.smtp.port",
	 * "587"); prop.put("mail.smtp.auth", "true");
	 * prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
	 * prop.put("mail.smtp.starttls.enable", "true");
	 * prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	 * 
	 * // prop.put("mail.smtp.socketFactory.port", "465"); //
	 * prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	 * 
	 * Session session = Session.getDefaultInstance(prop, new
	 * javax.mail.Authenticator(){ protected PasswordAuthentication
	 * getPasswordAuthentication() { return new PasswordAuthentication(
	 * from,password);// Specify the Username and the PassWord } });
	 * 
	 * 
	 * try { MimeMessage message = new MimeMessage(session); message.setFrom(new
	 * InternetAddress(from));
	 * message.setText("Congratulations you have been selected for our internship");
	 * message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	 * message.setSubject("Message From ZCare"); String msg =
	 * "This msg to test OTP"; MimeBodyPart mimeBodyPart = new MimeBodyPart();
	 * mimeBodyPart.setContent(msg, "text/html"); String emailhost="smtp.gmail.com";
	 * Transport transport=session.getTransport("smtp");
	 * transport.connect(emailhost,from,password); Transport.send(message);
	 * //System.out.println("Mail send Succesful"); res=1;
	 * 
	 * } catch (MessagingException e) { e.printStackTrace();
	 * 
	 * } return res; }
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public static String getIt() {
		String s = System.getProperty("java.runtime.version");
		return s;
	}

}
