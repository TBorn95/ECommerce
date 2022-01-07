package ePlattform.Client.register;

import org.apache.http.client.utils.URIBuilder;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ePlattform.Client.connection.HttpConnection;
import ePlattform.Client.connection.HttpConnection.ContentType;
import ePlattform.Client.connection.HttpConnection.Type;
import ePlattform.Client.domainObjects.Adresse;
import ePlattform.Client.domainObjects.Nutzer;
import ePlattform.Client.helperClasses.AdressChecker;
import ePlattform.Client.helperClasses.CustomSerializer;
import ePlattform.Client.helperClasses.PasswordChecker;

@Component
public class RegisterModel {
	
	private Adresse nutzerAdresse;
	private String vorname;
	private String nachname;
	private String email;
	private String benutzername;
	private String passwort;
	private String gewerbename;
	private String base64Bild;
	private boolean gewerbe = false;
	private Gson gson = new GsonBuilder().registerTypeAdapter(Nutzer.class, new CustomSerializer()).create();
	


	public boolean register(Nutzer nutzer) {
		nutzer.getAdressen().add(nutzerAdresse);
		nutzer.setProfilbild(this.base64Bild);
		try {
			String uri = new URIBuilder().setHost("localhost").setScheme("http").setPort(8080).setPath("nutzer/register").build().toString();
			String response = HttpConnection.request(Type.POST, uri, gson.toJson(nutzer, Nutzer.class), ContentType.JSON, String.class);
			if(response.equals("true")) return true;
			else return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
				
	public boolean validateAdress(String strasse, String stadt, String plz, String hausnr, String land) {
		try {	
			if(AdressChecker.checkAdress(strasse, hausnr, plz, stadt, land)) {
				JSONObject checkedAdress = AdressChecker.getCheckedAdress();
				nutzerAdresse = new Adresse(checkedAdress.getString("Strasse"), checkedAdress.getString("Hausnummer"), checkedAdress.getString("PLZ"),
						checkedAdress.getString("Stadt"), checkedAdress.getString("Land"), checkedAdress.getDouble("lat"), checkedAdress.getDouble("long"));
				return true;
			}
		}catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
		return false;
	}
	
	
	public boolean getPersonalData(String vorname, String nachname, String email) {
		if(!email.contains("@")) {
			return false;
		}
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		return true;
	}
	
	
	
	public boolean checkPassword(String password, String passwordRepeat) {
		 if(PasswordChecker.check(password, passwordRepeat, 6, ",;.-?!§$%&/()=+*<>|^", true, true)) {
			 this.passwort = password;
			 return true;
		 }else {
			 return false;
		 }
	}


	public boolean isGewerbe() {
		return gewerbe;
	}


	public void setGewerbe(boolean gewerbe) {
		this.gewerbe = gewerbe;
	}

	public String getBase64Bild() {
		return base64Bild;
	}

	public void setBase64Bild(String base64Bild) {
		this.base64Bild = base64Bild;
	}
	
	
}
