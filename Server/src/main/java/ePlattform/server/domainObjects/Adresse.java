package ePlattform.server.domainObjects;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "adresse")
public class Adresse {
	
	@Id @GeneratedValue
	private int idAdresse;
	private String strasse;
	private String hausnummer;
	private String postleitzahl;
	private String stadt;
	private String land;
	private boolean lieferadresse;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "geoDataId")
	private GeoData geodata;
	@ManyToMany(mappedBy = "adressen")
	private transient List<Nutzer> bewohner = new LinkedList<>();
	
	public Adresse() {
		
	}
	
	public Adresse(String strasse, String hausnummer, String postleitzahl, String stadt, String land) {
		super();
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.postleitzahl = postleitzahl;
		this.stadt = stadt;
		this.land = land;
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
	public int getIdAdresse() {
		return idAdresse;
	}
	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}
	public boolean isLieferadresse() {
		return lieferadresse;
	}
	public void setLieferadresse(boolean lieferadresse) {
		this.lieferadresse = lieferadresse;
	}
	public List<Nutzer> getBewohner() {
		return bewohner;
	}
	public void setBewohner(List<Nutzer> bewohner) {
		this.bewohner = bewohner;
	}

	public GeoData getGeodata() {
		return geodata;
	}

	public void setGeodata(GeoData geodata) {
		this.geodata = geodata;
	}
	
}
