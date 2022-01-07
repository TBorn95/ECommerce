package ePlattform.server.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ePlattform.server.domainObjects.Adresse;
import ePlattform.server.domainObjects.Bestellung;
import ePlattform.server.domainObjects.GewerbeNutzer;
import ePlattform.server.domainObjects.Nutzer;
import ePlattform.server.helperClasses.FileDecoder;
import ePlattform.server.repositories.AdresseRepository;
import ePlattform.server.repositories.BewertungRepository;
import ePlattform.server.repositories.GewerbeNutzerRepository;
import ePlattform.server.repositories.NutzerRepository;

@Service
public class NutzerService {
	
	NutzerRepository nutzerRepository;
	AdresseRepository adresseRepository;
	BewertungRepository bewertungRepository;
	GewerbeNutzerRepository gewerbeNutzerRepository;
	FileDecoder fileDecoder;
	
	
	public NutzerService(NutzerRepository nutzerRepository, AdresseRepository adresseRepository, BewertungRepository bewertungRepository, GewerbeNutzerRepository gewerbeNutzerRepository,
			FileDecoder fileDecoder) {
		this.nutzerRepository= nutzerRepository;
		this.adresseRepository = adresseRepository;
		this.bewertungRepository = bewertungRepository;
		this.gewerbeNutzerRepository = gewerbeNutzerRepository;
		this.fileDecoder = fileDecoder;

	}
	
	
	@Transactional
	public boolean registerNutzer(Nutzer nutzer) {
		if(nutzer instanceof GewerbeNutzer) {
			if(gewerbeNutzerRepository.existsByGewerbename(((GewerbeNutzer)nutzer).getGewerbename())){
				return false;
			}
		}
		if(!nutzerRepository.existsByBenutzername(nutzer.getBenutzername())) {
			nutzer = this.fetchAdress(nutzer);
			nutzer.setProfilbild(fileDecoder.storePicToDisk(nutzer.getProfilbild(), nutzer.getBenutzername()));
			nutzer = nutzerRepository.save(nutzer);
			return true;
		}
		return false;
	}
	
	
	@Transactional
	public boolean checkNutzername(String benutzername) {
		return nutzerRepository.existsByBenutzername(benutzername) ? true : false;
	}

	
	// make public for testing
	public Nutzer fetchAdress(Nutzer nutzer) {
		Adresse adresse = nutzer.getAdressen().get(0);
		Adresse dbAdresse = this.adresseRepository.findByHausnummerAndStrasseAndPostleitzahl(adresse.getHausnummer(), adresse.getStrasse(), adresse.getPostleitzahl());
		if(dbAdresse != null) {
			nutzer.getAdressen().set(0, dbAdresse);
		}
		return nutzer;
	}
	
	
	@Transactional
	public boolean updateNutzer(Nutzer nutzer) {
		try {
			if(!nutzer.getProfilbild().equals(fileDecoder.getFilePath())) {
				nutzer.setProfilbild(fileDecoder.storePicToDisk(nutzer.getProfilbild(), nutzer.getBenutzername()));
			}
			nutzerRepository.save(nutzer);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}
	
	
	@Transactional
	public boolean rateNutzer(int score, Bestellung bestellung, String anbieterName) {		
		GewerbeNutzer anbieter = (GewerbeNutzer) nutzerRepository.findByBenutzername(anbieterName);
		if(!bewertungRepository.existsByBestellung(bestellung)) {
			anbieter.addBewertung(score, bestellung);
			return true;
		}else {
			return false;
		}
	}
	
	
	@Transactional
	public Nutzer loginNutzer(String benutzerName, String passwort) {
		//Nutzer loggingNutzer = nutzerRepository.findByBenutzername(benutzerName);
		Nutzer loggingNutzer = nutzerRepository.findByBenutzernameFetchedEntity(benutzerName);
		//Nutzer loggingNutzer = nutzerRepository.findByBenutzernameFetched(benutzerName);
		return (loggingNutzer != null && loggingNutzer.getPasswort().equals(passwort)) ? loggingNutzer : null; 				
	}
		

}