package ePlattform.server.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ePlattform.server.domainObjects.AngesehenesProdukt;
import ePlattform.server.domainObjects.GewerbeNutzer;
import ePlattform.server.domainObjects.Nutzer;
import ePlattform.server.domainObjects.PrivatNutzer;
import ePlattform.server.domainObjects.Produkt;
import ePlattform.server.repositories.NutzerRepository;
import ePlattform.server.repositories.ProduktRepository;

@Service
public class ProduktService {
	
	ProduktRepository produktRepository;
	NutzerRepository nutzerRepository;
	
	public ProduktService(ProduktRepository produktRepository, NutzerRepository nutzerRepository) {
		super();
		this.produktRepository = produktRepository;
		this.nutzerRepository = nutzerRepository;
	}
	
// Rückgabewert bearbeiten
	@Transactional
	public List<Produkt> uploadProdukt(List<Produkt> produkte, String benutzername) {
		GewerbeNutzer nutzer = (GewerbeNutzer) nutzerRepository.findByBenutzername(benutzername);
		List<Produkt> failedProducts = new ArrayList<>();
		for(int i = 0; i < produkte.size(); i++) {
			Produkt produkt = produkte.get(i);
			nutzer.produktAnbieten(produkt);
		}
		return failedProducts;
	}
	
	@Transactional
	public void preisReduktion(int produktId, double betrag) {
		Produkt produkt = produktRepository.findById(produktId).get();
		produkt.setAngebotspreis(betrag);		
	}
	
	@Transactional
	public void endPreisReduktion(int produktId) {
		Produkt produkt = produktRepository.findById(produktId).get();
		produkt.setAngebotspreis(-1);
	}
	
	@Transactional
	public void produktAnsehen(String benutzername, Produkt produkt) {
		Nutzer nutzer = nutzerRepository.findByBenutzername(benutzername);
		List<AngesehenesProdukt> angeseheneProdukte = nutzer.getAngeseheneProdukte();
		if(angeseheneProdukte.size() >= 5) {
			nutzer.removeAngesehenesProdukt(Collections.min(angeseheneProdukte, Comparator.comparing(AngesehenesProdukt::getTimestamp)).getProdukt());
		}
		nutzer.addAngesehenesProdukt(produkt);
	}
	
	@Transactional
	public Produkt produktKaufen(String benutzername, int produktId) {
		PrivatNutzer kaeufer = (PrivatNutzer)nutzerRepository.findByBenutzername(benutzername);
		Produkt produkt = produktRepository.findById(produktId).get();
		Nutzer anbieter = produkt.getAnbieter();
		if(kaeufer.getSaldo() < produkt.getPreis()) {
			return null;
		}
		kaeufer.setSaldo(kaeufer.getSaldo() - produkt.getPreis());
		anbieter.setSaldo(anbieter.getSaldo() + produkt.getPreis());
		kaeufer.addBestellung(produkt);
		return produkt;
	}
	
}
