package ePlattform.server.domainObjects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("privat")
public class PrivatNutzer extends Nutzer {
	
	@OneToMany(
			mappedBy = "anbieter",
			cascade = CascadeType.ALL)
	private List<Auktion> eigeneAuktionen = new ArrayList<>();
	
	@ManyToMany(cascade = {
			CascadeType.PERSIST,
			CascadeType.MERGE}
			)
	@JoinTable(name = "gemerkteAuktionen",
	joinColumns = @JoinColumn(name = "idNutzer"),
	inverseJoinColumns = @JoinColumn(name = "idAuktion"))
	private Set<Auktion> gemerkteAuktionen = new HashSet<>();
	
	@OneToMany(mappedBy = "kaeufer",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Bestellung> bestellungen = new ArrayList<>();
	
	@OneToMany(mappedBy = "bieter",
			cascade = CascadeType.ALL)
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


