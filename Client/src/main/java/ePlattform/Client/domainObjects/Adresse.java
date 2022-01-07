package ePlattform.Client.domainObjects;

public class Adresse {
	
	private int idAdresse;
	private String strasse;
	private String hausnummer;
	private String postleitzahl;
	private String stadt;
	private String land;
	private boolean lieferadresse;
	private GeoData geodata;
	
	public Adresse() {
		
	}
	
	public Adresse(String strasse, String hausnummer, String postleitzahl, String stadt, String land, double latitude, double longitude) {
		super();
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.postleitzahl = postleitzahl;
		this.stadt = stadt;
		this.land = land;
		this.geodata = new GeoData(latitude, longitude);
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}
	public String getPostleitzahl() {
		return postleitzahl;
	}
	public void setPostleitzahl(String postleitzahl) {
		this.postleitzahl = postleitzahl;
	}
	public String getStadt() {
		return stadt;
	}
	public void setStadt(String stadt) {
		this.stadt = stadt;
	}
	public String getLand() {
		return land;
	}
	public void setLand(String land) {
		this.land = land;
	}
	public boolean isLieferadresse() {
		return lieferadresse;
	}
	public void setLieferadresse(boolean lieferadresse) {
		this.lieferadresse = lieferadresse;
	}

	public int getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	public GeoData getGeodata() {
		return geodata;
	}

	public void setGeodata(GeoData geodata) {
		this.geodata = geodata;
	}
	
}
