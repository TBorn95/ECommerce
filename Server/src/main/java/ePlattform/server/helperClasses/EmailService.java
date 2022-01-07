package ePlattform.server.helperClasses;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	private String senderMail;
	private String password;
	
	public EmailService() {

	}
	
	public void sendMail(String content, String kundenMail) {
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setSSLOnConnect(true);
		email.setAuthenticator(new DefaultAuthenticator(senderMail,password));
		try {
			email.setFrom(senderMail);
			email.setMsg(content);
			email.addTo(kundenMail);
			email.send();
		}catch(EmailException e) {
			e.printStackTrace();
		}
	}
	
	
	public String generateAnbieterTextAuktionen(String anbietername, String auktionsname, double gebot, String ersteigerer) {
		return "Sehr geehrter Herr/Frau " + anbietername +"," + System.lineSeparator() +
					 "Ihre Auktion " + auktionsname + " ist soeben abgelaufen." + System.lineSeparator() +
					 "Sie haben " + gebot + "€ durch den Nutzer " + ersteigerer +" verdient.";
	}
	
	
	public String generateErsteigererTextAuktionen(String ersteigerername, String auktionsName, double gebot) {
		return "Sehr geehrter Herr/Frau " + ersteigerername +"," + System.lineSeparator() +
				 "Sie sind als Hoechstbietender aus der Auktion " + auktionsName + " hervorgegangen, bei einem Hoechstgebot von " + gebot +"€.";		
	}
	
	
	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}
	

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

