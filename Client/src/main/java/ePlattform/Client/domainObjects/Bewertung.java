package ePlattform.Client.domainObjects;

public class Bewertung {

	private int bewertungId;
	private int score;
	private Bestellung bestellung;
	private Nutzer verkaeufer;
	
	public Bewertung(int score, Bestellung bestellung, Nutzer verkaeufer) {
		this.score = score;
		this.bestellung = bestellung;
		this.verkaeufer = verkaeufer;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Bestellung getBestellung() {
		return bestellung;
	}
	public void setBestellung(Bestellung bestellung) {
		this.bestellung = bestellung;
	}
	public int getBewertungId() {
		return bewertungId;
	}
	public void setBewertungId(int bewertungId) {
		this.bewertungId = bewertungId;
	}
	public Nutzer getVerkaeufer() {
		return verkaeufer;
	}
	public void setVerkaeufer(Nutzer verkaeufer) {
		this.verkaeufer = verkaeufer;
	}
	
	
	
}
