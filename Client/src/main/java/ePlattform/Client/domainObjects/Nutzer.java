package ePlattform.Client.domainObjects;

import java.util.ArrayList;
import java.util.List;


public abstract class Nutzer {
	
	private int idNutzer;
	private String benutzername;
	private String vorname;
	private String nachname;
	private String email;
	private String passwort;
	private String profilbild;
	private double saldo = 0.0;
	private boolean twoFA;
	
	private List<Adresse> adressen = new ArrayList<>();
	
	private List<AngesehenesProdukt> angeseheneProdukte = new ArrayList<>();

	private List<Nachricht> gesendeteNachrichten;

	private List<Nachricht> erhalteneNachrichten;
	
	
	
	public Nutzer(String benutzername, String vorname, String nachname, String email, String passwort) {
		super();
		this.benutzername = benutzername;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.passwort = passwort;
		this.twoFA = false;
	}
	public Nutzer() {
		
	}
	
	public void addNachricht(Nutzer empfaenger, String content) {
		Nachricht nachricht = new Nachricht(this, empfaenger, content);
		this.gesendeteNachrichten.add(nachricht);
		empfaenger.erhalteneNachrichten.add(nachricht);
	}
	
	public void removeNachricht(Nachricht nachricht) {
		nachricht.setEmpfaenger(null);
		nachricht.setSender(null);		
	}

	
	public int getIdNutzer() {
		return idNutzer;
	}
	public void setIdNutzer(int idNutzer) {
		this.idNutzer = idNutzer;
	}
	public List<Nachricht> getGesendeteNachrichten() {
		return gesendeteNachrichten;
	}
	public void setGesendeteNachrichten(List<Nachricht> gesendeteNachrichten) {
		this.gesendeteNachrichten = gesendeteNachrichten;
	}
	public List<Nachricht> getErhalteneNachrichten() {
		return erhalteneNachrichten;
	}
	public void setErhalteneNachrichten(List<Nachricht> erhalteneNachrichten) {
		this.erhalteneNachrichten = erhalteneNachrichten;
	}
	public String getBenutzername() {
		return benutzername;
	}
	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswort() {
		return passwort;
	}
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public double getSaldo() {
		return saldo;
	}
	
	public boolean isTwoFA() {
		return twoFA;
	}
	public void setTwoFA(boolean twoFA) {
		this.twoFA = twoFA;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public List<Adresse> getAdressen() {
		return adressen;
	}
	public void setAdressen(List<Adresse> adressen) {
		this.adressen = adressen;
	}
	public String getProfilbild() {
		return profilbild;
	}
	public void setProfilbild(String profilbild) {
		this.profilbild = profilbild;
	}
	public void addAngesehenesProdukt(Produkt produkt) {
		AngesehenesProdukt angesehenesProdukt = new AngesehenesProdukt(this, produkt);
		this.angeseheneProdukte.add(angesehenesProdukt);
		produkt.getAngeseheneProdukte().add(angesehenesProdukt);
	}
	public void removeAngesehenesProdukt(Produkt produkt) {
		AngesehenesProdukt aProdukt = this.getAngeseheneProdukte().stream()
				.filter(p ->p.getProdukt().equals(produkt)).findFirst().get();
		this.angeseheneProdukte.remove(aProdukt);
		aProdukt.getProdukt().getAngeseheneProdukte().remove(aProdukt);
		aProdukt.setProdukt(null);
		aProdukt.setNutzer(null);
	}

	public List<AngesehenesProdukt> getAngeseheneProdukte() {
	return angeseheneProdukte;
	}	

	public void setAngeseheneProdukte(List<AngesehenesProdukt> angeseheneProdukte) {
		this.angeseheneProdukte = angeseheneProdukte;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Nutzer)) return false;
		return benutzername != null && benutzername.equals(((Nutzer)o).getBenutzername());
	}
	
}
