package ePlattform.Client.domainObjects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class PrivatNutzer extends Nutzer {
	

	private List<Auktion> eigeneAuktionen = new ArrayList<>();

	private Set<Auktion> gemerkteAuktionen = new HashSet<>();
	
	private List<Bestellung> bestellungen = new ArrayList<>();
	
	private List<Gebot> gebote = new ArrayList<>();

	
	public PrivatNutzer() {
		super();
	}
	
	public PrivatNutzer(String benutzername, String vorname, String nachname, String email, String passwort) {
		super(benutzername, vorname, nachname, email, passwort);
	}
	
	
	public void addEigeneAuktion(Auktion auktion) {
		auktion.setAnbieter(this);
		this.eigeneAuktionen.add(auktion);
	}
	
	public void removeEigeneAukion(Auktion auktion) {
		auktion.setAnbieter(null);
		this.eigeneAuktionen.remove(auktion);
	}
	
	public Gebot addGebot(Auktion auktion, double einsatz) {
		Gebot gebot = new Gebot(this, auktion, einsatz);
		this.gebote.add(gebot);
		auktion.getGebote().add(gebot);
		return gebot;
	}
	
	public void addGemerkteAuktion(Auktion auktion) {
		this.gemerkteAuktionen.add(auktion);
		auktion.getMerker().add(this);
	}
	
	public void removeGemerkteAuktion(Auktion auktion) {
		this.gemerkteAuktionen.remove(auktion);
		auktion.getMerker().remove(this);
	}
	public void addBestellung(Produkt produkt) {
		Bestellung bestellung = new Bestellung(this, produkt);
		this.bestellungen.add(bestellung);
		produkt.getBestellungen().add(bestellung);
	}
	
	public List<Auktion> getEigeneAuktionen() {
		return eigeneAuktionen;
	}
	
	public void setEigeneAuktionen(List<Auktion> eigeneAuktionen) {
		this.eigeneAuktionen = eigeneAuktionen;
	}
	
	public Set<Auktion> getGemerkteAuktionen() {
		return gemerkteAuktionen;
	}
	
	public void setGemerkteAuktionen(Set<Auktion> gemerkteAuktionen) {
		this.gemerkteAuktionen = gemerkteAuktionen;
	}
	
	public List<Bestellung> getBestellungen() {
		return bestellungen;
	}
	
	public void setBestellungen(List<Bestellung> bestellungen) {
		this.bestellungen = bestellungen;
	}
	
	public List<Gebot> getGebote() {
		return gebote;
	}
	
	public void setGebote(List<Gebot> gebote) {
		this.gebote = gebote;
	}
	
}


