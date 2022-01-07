package ePlattform.Client.domainObjects;

import java.sql.Timestamp;


public class Nachricht {

	private int nachrichtId;

	private Nutzer sender;

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
