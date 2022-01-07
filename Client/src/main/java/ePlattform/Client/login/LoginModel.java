package ePlattform.Client.login;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.stereotype.Component;

import ePlattform.Client.connection.HttpConnection;
import ePlattform.Client.connection.HttpConnection.ContentType;
import ePlattform.Client.connection.HttpConnection.Type;
import ePlattform.Client.domainObjects.GewerbeNutzer;
import ePlattform.Client.domainObjects.Nutzer;

@Component
public class LoginModel {
	

	public Nutzer login(String benutzername, String passwort) throws Exception {
		String uri = new URIBuilder().setHost("localhost").setScheme("http").setPort(8080).setPath("nutzer/login")
				.addParameter("benutzername", benutzername).addParameter("passwort", passwort).build().toString();
		return HttpConnection.request(Type.GET, uri, null, ContentType.JSON, GewerbeNutzer.class);
	}
	
}
