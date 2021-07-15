package bulkemail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

public class JavaEmail {
	

	Session newSession;


	public static void main(String[] args) throws IOException, AddressException, MessagingException {
		JavaEmail mail=new JavaEmail();
		mail.setupServerProperties();
		mail.prepareMail();
		
		}

    private MimeMessage prepareMail() throws IOException, AddressException, MessagingException {
		BufferedReader br = new BufferedReader(new FileReader("emails.txt"));
		Set<String> emailRecipient = new HashSet<String>();
		
		String line = null;
		while ((line = br.readLine()) != null) {
			emailRecipient.add(line);
			}
		br.close();
		
		MimeMessage message=new MimeMessage(newSession);
		
		for(String email:emailRecipient) {
			message.setSubject("Bulk Email...............");
			message.setRecipients(Message.RecipientType.TO,email);
			//name=email.substring(0, email.lastIndexOf("@"));
			
	
			message.setText("Dear "+email.substring(0, email.lastIndexOf("@"))+" ,\nGreetings to you.\nWelcome to my github Accoount."
					+ "\nGit hub URL:https://github.com/BinduVannemreddy97\nThanks& Regards,\nBindu");
			Transport.send(message);
			
			}
		
		
		System.out.println("Mail sent sucessfully");
		return message;
	}

	
	private void setupServerProperties() {
		System.out.println("Preparing for Email");
		Properties properties= System.getProperties();
		properties.put("mail.smtp.auth","true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String myAccount="binduvannemreddy97@gmail.com";
		String password="Test@123";
		
		newSession = Session.getDefaultInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(myAccount,password);
			}
		});
		
		
	}
	
	
}