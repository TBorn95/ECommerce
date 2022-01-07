package ePlattform.server.domainObjects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bewertung" ) 
public class Bewertung {
	
	@Id @GeneratedValue
	private int bewertungId;
	private int score;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bestellungId")
	private Bestellung bestellung;
	@ManyToOne(fetch = FetchType.LAZY)
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
