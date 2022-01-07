package ePlattform.Client.domainObjects;

import java.sql.Timestamp;



public class AngesehenesProdukt {

	private int letzteProdukteId;
	
	private Nutzer nutzer;	

	private Produkt produkt;
	
	private Timestamp timestamp;

	public AngesehenesProdukt(Nutzer nutzer, Produkt produkt) {
		super();
		this.nutzer = nutzer;
		this.produkt = produkt;
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}
	
	public AngesehenesProdukt() {
		
	}

	public int getLetzteProdukteId() {
		return letzteProdukteId;
	}

	public void setLetzteProdukteId(int letzteProdukteId) {
		this.letzteProdukteId = letzteProdukteId;
	}

	public Nutzer getNutzer() {
		return nutzer;
	}

	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}

	public Produkt getProdukt() {
		return produkt;
	}

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		
		if(o == null || getClass() != o.getClass())
			return false;
		AngesehenesProdukt aProdukt = (AngesehenesProdukt) o;
		return aProdukt.getTimestamp().equals(this.getTimestamp()) &&
				aProdukt.getNutzer().equals(this.getNutzer()) &&
				aProdukt.getProdukt().equals(this.getProdukt());
	}
	@Override
	public String toString() {
		return "ID: " + this.letzteProdukteId +",Nutzer: " + this.getNutzer() + 
				", produkt: " + this.getProdukt();
	}
	
}
