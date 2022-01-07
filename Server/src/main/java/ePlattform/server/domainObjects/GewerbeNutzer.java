package ePlattform.server.domainObjects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.google.gson.annotations.Expose;

@Entity
@DiscriminatorValue("gewerbe")
public class GewerbeNutzer extends Nutzer{
	
	@Expose
	private String gewerbename;
	@OneToMany(mappedBy = "anbieter",
			cascade = CascadeType.ALL)
	private  List<Produkt> angeboteneProdukte = new ArrayList<>();
	@OneToMany(	mappedBy = "verkaeufer",
	cascade = CascadeType.ALL,
	orphanRemoval = true)
	private  List<Bewertung> bewertungen= new ArrayList<>();
	
	public GewerbeNutzer(String vorname, String nachname, String benutzername,String email, String password,String gewerbename) {
		super(benutzername, vorname, nachname, email, password);
		this.gewerbename = gewerbename;
	}
	public GewerbeNutzer() {
		
	}
	
	public void addBewertung(int score, Bestellung bestellung) {
		Bewertung bewertung = new Bewertung(score, bestellung, this);
		this.bewertungen.add(bewertung);
	}
	
	public void RemoveBewertung(Bewertung bewertung) {
		this.bewertungen.remove(bewertung);
		bewertung.setVerkaeufer(null);
		bewertung.setBestellung(null);
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
