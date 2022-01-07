package ePlattform.server.domainObjects;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "nachricht")
public class Nachricht {

	@Id @GeneratedValue
	private int nachrichtId;
	
	@ManyToOne
	private Nutzer sender;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Nutzer empfaenger;
	
	private String content;
	
	private Timestamp timestamp;

	public Nachricht(Nutzer sender, Nutzer empfaenger, String content) {
		super();
		this.sender = sender;
		this.empfaenger = empfaenger;
		this.content = content;
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null || o.getClass() != this.getClass()) return false;
		Nachricht nachricht = (Nachricht)o;
		return nachricht.getNachrichtId() == this.getNachrichtId();
	}
	public int getNachrichtId() {
		return nachrichtId;
	}

	public void setNachrichtId(int nachrichtId) {
		this.nachrichtId = nachrichtId;
	}

	public Nutzer getSender() {
		return sender;
	}

	public void setSender(Nutzer sender) {
		this.sender = sender;
	}

	public Nutzer getEmpfaenger() {
		return empfaenger;
	}

	public void setEmpfaenger(Nutzer empfaenger) {
		this.empfaenger = empfaenger;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
