package ePlattform.Client.domainObjects;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Auktion {
	

	private int idAuktion;
	private String name;
	private String beschreibung;
	private String bild;
	private double startpreis;
	private double mindestgebot;
	private double aktuellesGebot;
	private boolean selbstabholung;
	private Timestamp auktionsEnde;
	
	private PrivatNutzer anbieter;

	private Set<PrivatNutzer> merker = new HashSet<>();
	
	private List<Gebot> gebote = new ArrayList<>();
	
	public Auktion(String name, String beschreibung, String bild, double startpreis, double mindestgebot,
			boolean selbstabholung, PrivatNutzer anbieter) {
		super();
		this.name = name;
		this.beschreibung = beschreibung;
		this.bild = bild;
		this.startpreis = startpreis;
		this.mindestgebot = mindestgebot;
		this.selbstabholung = selbstabholung;
		this.anbieter = anbieter;
	}
	public int getIdAuktion() {
		return idAuktion;
	}
	public void setIdAuktion(int idAuktion) {
		this.idAuktion = idAuktion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public String getBild() {
		return bild;
	}
	public void setBild(String bild) {
		this.bild = bild;
	}
	public double getStartpreis() {
		return startpreis;
	}
	public void setStartpreis(double startpreis) {
		this.startpreis = startpreis;
	}
	public double getMindestgebot() {
		return mindestgebot;
	}
	public void setMindestgebot(double mindestgebot) {
		this.mindestgebot = mindestgebot;
	}
	public boolean isSelbstabholung() {
		return selbstabholung;
	}
	public void setSelbstabholung(boolean selbstabholung) {
		this.selbstabholung = selbstabholung;
	}
	public PrivatNutzer getAnbieter() {
		return anbieter;
	}
	public void setAnbieter(PrivatNutzer anbieter) {
		this.anbieter = anbieter;
	}
	public double getAktuellesGebot() {
		return aktuellesGebot;
	}
	public void setAktuellesGebot(double aktuellesGebot) {
		this.aktuellesGebot = aktuellesGebot;
	}
	public List<Gebot> getGebote() {
		return gebote;
	}
	public void setGebote(List<Gebot> gebote) {
		this.gebote = gebote;
	}
	public Timestamp getAuktionsEnde() {
		return auktionsEnde;
	}
	public void setAuktionsEnde(Timestamp auktionsEnde) {
		this.auktionsEnde = auktionsEnde;
	}
	public Set<PrivatNutzer> getMerker() {
		return merker;
	}
	public void setMerker(Set<PrivatNutzer> merker) {
		this.merker = merker;
	}
	
	
}
