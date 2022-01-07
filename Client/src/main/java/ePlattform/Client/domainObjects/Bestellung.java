package ePlattform.Client.domainObjects;

import java.sql.Timestamp;


public class Bestellung {
	

	private int bestellungId;

	private Nutzer kaeufer;

	private Produkt produkt;
	private Timestamp bestellzeitpunkt;

	public Bestellung(Nutzer kaeufer, Produkt produkt) {
		this.kaeufer = kaeufer;
		this.produkt = produkt;		
		this.bestellzeitpunkt = new Timestamp(System.currentTimeMillis());
	}

	public int getBestellungId() {
		return bestellungId;
	}

	public void setBestellungId(int bestellungId) {
		this.bestellungId = bestellungId;
	}

	public Nutzer getKaeufer() {
		return kaeufer;
	}

	public void setKaeufer(Nutzer kaeufer) {
		this.kaeufer = kaeufer;
	}

	public Produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

	public Timestamp getBestellzeitpunkt() {
		return bestellzeitpunkt;
	}

	public void setBestellzeitpunkt(Timestamp bestellzeitpunkt) {
		this.bestellzeitpunkt = bestellzeitpunkt;
	}
	
}
