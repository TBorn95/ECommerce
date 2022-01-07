package ePlattform.Client.domainObjects;

import java.util.ArrayList;
import java.util.List;


public class GewerbeNutzer extends Nutzer{
	
	private String gewerbename;
	private List<Produkt> angeboteneProdukte = new ArrayList<>();
	private List<Bewertung> bewertungen= new ArrayList<>();
	
	public GewerbeNutzer(String vorname, String nachname, String benutzername,String email, String password,String gewerbename) {
		super(benutzername, vorname, nachname, email, password);
		this.gewerbename = gewerbename;
	}
	public GewerbeNutzer() {
		super();
	}

	
	public List<Bewertung> getBewertungen() {
		return bewertungen;
	}
	public void setBewertungen(List<Bewertung> bewertungen) {
		this.bewertungen = bewertungen;
	}
	public void setAngeboteneProdukte(List<Produkt> angeboteneProdukte) {
		this.angeboteneProdukte = angeboteneProdukte;
	}
	public String getGewerbename() {
		return gewerbename;
	}
	public void setGewerbename(String gewerbename) {
		this.gewerbename = gewerbename;
	}
	public List<Produkt> getAngeboteneProdukte() {
		return angeboteneProdukte;
	}
	public void produktAnbieten(Produkt produkt) {
		produkt.setAnbieter(this);
		this.angeboteneProdukte.add(produkt);
	}
	
}
