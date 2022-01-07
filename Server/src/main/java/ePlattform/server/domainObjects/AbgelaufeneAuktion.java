package ePlattform.server.domainObjects;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "abgelaufeneAuktionen")
public class AbgelaufeneAuktion {

	@Id 
	private int idAbgelaufeneAuktion;
	private String name;
	private String beschreibung;
	private String bild;
	private double startpreis;
	private double mindestgebot;
	private double endGebot;
	private boolean selbstabholung;
	private Timestamp auktionsEnde;
	
	public AbgelaufeneAuktion( String name, String beschreibung, String bild, double startpreis,
			double mindestgebot, double endGebot, boolean selbstabholung, Timestamp auktionsEnde) {
		super();
		this.name = name;
		this.beschreibung = beschreibung;
		this.bild = bild;
		this.startpreis = startpreis;
		this.mindestgebot = mindestgebot;
		this.endGebot = endGebot;
		this.selbstabholung = selbstabholung;
		this.auktionsEnde = auktionsEnde;
	}

	public int getIdAbgelaufeneAuktion() {
		return idAbgelaufeneAuktion;
	}

	public void setIdAbgelaufeneAuktion(int idAbgelaufeneAuktion) {
		this.idAbgelaufeneAuktion = idAbgelaufeneAuktion;
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

	public double getEndGebot() {
		return endGebot;
	}

	public void setEndGebot(double endGebot) {
		this.endGebot = endGebot;
	}

	public boolean isSelbstabholung() {
		return selbstabholung;
	}

	public void setSelbstabholung(boolean selbstabholung) {
		this.selbstabholung = selbstabholung;
	}

	public Timestamp getAuktionsEnde() {
		return auktionsEnde;
	}

	public void setAuktionsEnde(Timestamp auktionsEnde) {
		this.auktionsEnde = auktionsEnde;
	}			
}
