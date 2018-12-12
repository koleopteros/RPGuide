
package ja.project.comp3074.rpguide.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailHandler {
	
	public static void sendEmail(String fname, String lname, String email, String message) {
		final String name = fname+" "+lname;
		final String from = "servletmailtester@gmail.com";
		final String passwd = "testing4servlet";

		Properties prop = new Properties();
		prop.put("mail.smtp.auth","true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, passwd);
			}
		});
		
		try {
			System.out.println("Creating Email Message");
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			msg.setSubject("RPGuide - A friend just messaged you!");
			msg.setText(message);
			System.out.println("Finished building Email Message");
			Transport.send(msg);
			System.out.println("Sent out Email");
		}
		catch(MessagingException e) {
			e.printStackTrace();
		}
	}
}
