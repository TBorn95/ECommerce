package ePlattform.server.domainObjects;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gebot")
public class Gebot {
	
	@Id @GeneratedValue
	private int gebotId;
	@ManyToOne(fetch = FetchType.LAZY)
	private Nutzer bieter;
	@ManyToOne(fetch = FetchType.LAZY)
	private Auktion auktion;
	private double gebot;
	private Timestamp timestamp;
	
	public Gebot(Nutzer bieter, Auktion auktion, double gebot) {
		super();
		this.bieter = bieter;
		this.auktion = auktion;
		this.gebot = gebot;
		timestamp = new Timestamp(System.currentTimeMillis());
	}

	public int getGebotId() {
		return gebotId;
	}

	public void setGebotId(int gebotId) {
		this.gebotId = gebotId;
	}

	public Nutzer getBieter() {
		return bieter;
	}

	public void setBieter(Nutzer bieter) {
		this.bieter = bieter;
	}

	public Auktion getAuktion() {
		return auktion;
	}

	public void setAuktion(Auktion auktion) {
		this.auktion = auktion;
	}

	public double getGebot() {
		return gebot;
	}

	public void setGebot(double gebot) {
		this.gebot = gebot;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
