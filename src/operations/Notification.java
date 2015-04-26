package operations;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  



public class Notification {
	
	public void sendEmail(String to, String text) {		 
		
		final String username = "";
		final String password = "";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	 
	      // Setup mail server
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				  });
		 
				try {
		 
					Message message = new MimeMessage(session);
					message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(to));
					message.setSubject("Notificación- acción sobre formato");
					message.setText(text);
		 
					Transport.send(message);
		 
					
		 
				} catch (MessagingException e) {
					throw new RuntimeException(e);
				}
	   }
	public static void main (String [ ] args) {
		 
        Notification notif= new Notification();
       
}
		
	}


