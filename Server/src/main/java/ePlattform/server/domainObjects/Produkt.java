package ePlattform.server.domainObjects;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="produkt")
public class Produkt {
	
	@Id @GeneratedValue
	private int produktId;
	private String produktname;
	private String produktbeschreibung;
	private double preis;
	private double angebotspreis;
	private String bild;
	private String beschreibung;
	private String kategorie;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Nutzer anbieter;
	
	@OneToMany(mappedBy = "produkt",
			cascade = CascadeType.ALL)
	private List<Bestellung> bestellungen = new ArrayList<>();
	
	@OneToMany(mappedBy = "produkt",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<AngesehenesProdukt> angeseheneProdukte = new ArrayList<>();	
	
	public Produkt() {	
	}
		
	public Produkt(String produktname, String produktbeschreibung, double preis, String bild, String beschreibung,
			String kategorie, Nutzer anbieter) {
		super();
		this.produktname = produktname;
		this.produktbeschreibung = produktbeschreibung;
		this.preis = preis;
		this.bild = bild;
		this.beschreibung = beschreibung;
		this.kategorie = kategorie;
		this.anbieter = anbieter;
		this.angebotspreis = -1;
	}
	public Produkt(int produktId,String produktname, String produktbeschreibung, double preis, String bild, String beschreibung,
			String kategorie, Nutzer anbieter) {
		super();
		this.produktId = produktId;
		this.produktname = produktname;
		this.produktbeschreibung = produktbeschreibung;
		this.preis = preis;
		this.bild = bild;
		this.beschreibung = beschreibung;
		this.kategorie = kategorie;
		this.anbieter = anbieter;
		this.angebotspreis = -1;
	}
	
	public List<Bestellung> getBestellungen() {
		return bestellungen;
	}
	public void setBestellungen(List<Bestellung> bestellungen) {
		this.bestellungen = bestellungen;
	}
	public int getProduktId() {
		return produktId;
	}
	public void setProduktId(int produktId) {
		this.produktId = produktId;
	}
	public String getProduktname() {
		return produktname;
	}
	public void setProduktname(String produktname) {
		this.produktname = produktname;
	}
	public String getProduktbeschreibung() {
		return produktbeschreibung;
	}
	public void setProduktbeschreibung(String produktbeschreibung) {
		this.produktbeschreibung = produktbeschreibung;
	}
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
	public String getBild() {
		return bild;
	}
	public void setBild(String bild) {
		this.bild = bild;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public String getKategorie() {
		return kategorie;
	}
	public void setKategorie(String kategorie) {
		this.kategorie = kategorie;
	}
	public double getAngebotspreis() {
		return angebotspreis;
	}
	public void setAngebotspreis(double angebotspreis) {
		this.angebotspreis = angebotspreis;
	}
	public Nutzer getAnbieter() {
		return anbieter;
	}
	public void setAnbieter(Nutzer anbieter) {
		this.anbieter = anbieter;
	}

	public List<AngesehenesProdukt> getAngeseheneProdukte() {
		return angeseheneProdukte;
	}

	public void setAngeseheneProdukte(List<AngesehenesProdukt> angeseheneProdukte) {
		this.angeseheneProdukte = angeseheneProdukte;
	}
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(o == null || o.getClass() != this.getClass()) return false;
		else {
			Produkt p = (Produkt)o;
			return p.getProduktId()==this.getProduktId() && p.getProduktname().equals(this.getProduktname())
					&& this.getAnbieter().equals(p.getAnbieter()) && this.getBeschreibung().equals(p.getBeschreibung());
		}
	}
	
}
