package ePlattform.Client.login;

import org.springframework.stereotype.Component;

import ePlattform.Client.domainObjects.Nutzer;

@Component
public class LoggedInUser {

	private static Nutzer nutzer;

	public static Nutzer getNutzer() {
		return nutzer;
	}
	
	private LoggedInUser() {
		
	}

	public static void setNutzer(Nutzer newNutzer) {
		nutzer = newNutzer;
	}


	
	
}
