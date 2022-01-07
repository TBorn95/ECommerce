package ePlattform.server.integrationTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import ePlattform.server.domainObjects.Adresse;
import ePlattform.server.domainObjects.Bestellung;
import ePlattform.server.domainObjects.GewerbeNutzer;
import ePlattform.server.domainObjects.Nutzer;
import ePlattform.server.domainObjects.PrivatNutzer;
import ePlattform.server.domainObjects.Produkt;
import ePlattform.server.repositories.AdresseRepository;
import ePlattform.server.repositories.BestellungRepository;
import ePlattform.server.repositories.BewertungRepository;
import ePlattform.server.repositories.NutzerRepository;
import ePlattform.server.repositories.ProduktRepository;
import ePlattform.server.services.NutzerService;


@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class NutzerServiceTest {
	
	NutzerRepository nutzerRepository;
	AdresseRepository adresseRepository;
	ProduktRepository produktRepository;
	BestellungRepository bestellungRepository;
	BewertungRepository bewertungRepository;
	Adresse adresse;
	long rowsBefore;
	int idBefore;
	Nutzer nutzer;
	
	// test object
	NutzerService nutzerService;
	
	
	@BeforeEach
	public void setup() {
	    adresse = new Adresse("strasse", "11", "41065", "essen","de");
		adresse = adresseRepository.save(adresse);
		rowsBefore = adresseRepository.count();
		idBefore = adresse.getIdAdresse();
		nutzer = new PrivatNutzer("benutzername",  "vorname",  "nachname",  "email",  "passwort");
	}
	
	//fetchAdressTest where the adress already exists
	@Test
	public void fetchExistingAdressTest() {
		Adresse existingAdress = new Adresse("strasse", "11", "41065", "essen","de");
		nutzer.getAdressen().add(existingAdress);
		
		nutzer = nutzerService.fetchAdress(nutzer);
		int idAfter = nutzer.getAdressen().get(0).getIdAdresse();
		
		Assertions.assertEquals(idBefore, idAfter);
	}
	
	@Test
	public void fetchNewAdressTest() {
		Adresse NotExistingAdress = new Adresse("strasse", "12", "41065", "essen","de");
		nutzer.getAdressen().add(NotExistingAdress);
		
		nutzer = nutzerService.fetchAdress(nutzer);
		int idAfter = nutzer.getAdressen().get(0).getIdAdresse();	
		
		Assertions.assertNotEquals(idBefore, idAfter);
	}	
	
	@Test
	public void registerNotExistingUserNotExistingAdress() {
		//setup
		Nutzer newNutzer = new PrivatNutzer("benutzername2",  "vorname",  "nachname",  "email",  "passwort");
		Adresse newAdresse = new Adresse("strasse22", "12", "41065", "essen","de");
		newNutzer.getAdressen().add(newAdresse);				
		//test
		boolean registered = nutzerService.registerNutzer(newNutzer);
		//check
		long rowsAfter = adresseRepository.count();
		Assertions.assertAll(
				() -> Assertions.assertTrue(rowsBefore+1 == rowsAfter),
				() -> Assertions.assertEquals(true, registered)
				);	
	}
	
	@Test
	public void registerExisitingUserExistingAdress() {
		nutzerRepository.save(nutzer);
		//setup
		nutzer.getAdressen().add(adresse);
		//test
		boolean registered = nutzerService.registerNutzer(nutzer);
		//check
		long rowsAfter = adresseRepository.count();
		Assertions.assertAll(
				() -> Assertions.assertFalse(registered),
				() -> Assertions.assertEquals(rowsBefore, rowsAfter)
				);		
	}
	
	@Test
	public void registerExisitingUserNotExistingAdress() {
		nutzerRepository.save(nutzer);
		//setup
		Adresse newAdresse = new Adresse("strasse22", "12", "41065", "essen","de");
		Nutzer newNutzer = new PrivatNutzer("benutzername",  "vorname",  "nachname",  "email",  "passwort");
		newNutzer.getAdressen().add(newAdresse);
		//test
		boolean registered = nutzerService.registerNutzer(newNutzer);
		//check
		long rowsAfter = adresseRepository.count();
		Assertions.assertAll(
				() -> Assertions.assertFalse(registered),
				() -> Assertions.assertEquals(rowsBefore, rowsAfter)
				);	
	}
	
//	 gültige Bewertung nach getätigter Bestellung
	@Test
	public void rateNutzer() {
		//setup
		nutzer = nutzerRepository.save(nutzer);
		GewerbeNutzer anbieter = new GewerbeNutzer( "vorname", "nachname", "gewerbeanbieter", "email", "password"," gewerbename");
		anbieter = nutzerRepository.save(anbieter);
		Produkt produkt = new Produkt("produkt","beschreibung",20,"bildstring","beschreibung","kategprie",anbieter);
		produkt = produktRepository.save(produkt);
		Bestellung bestellung = new Bestellung(nutzer, produkt);
		bestellung = bestellungRepository.save(bestellung);
		//test
		boolean bewertet = nutzerService.rateNutzer(7, bestellung, "gewerbeanbieter");
		//check
		Assertions.assertTrue(bewertet);
		Assertions.assertEquals(1, bewertungRepository.count());
		Assertions.assertEquals(1, anbieter.getBewertungen().size());
		
		//erneute Bewertung
		boolean erneutBewertet = nutzerService.rateNutzer(7, bestellung, "gewerbeanbieter");
		Assertions.assertFalse(erneutBewertet);
		Assertions.assertEquals(1, bewertungRepository.count());
		Assertions.assertEquals(1, anbieter.getBewertungen().size());
	}

	
	@Autowired
	public NutzerServiceTest(NutzerRepository nutzerRepository, AdresseRepository adresseRepostory, NutzerService nutzerService,
			BestellungRepository bestellungRepository, ProduktRepository produktRepository,BewertungRepository bewertungRepository) {
		super();
		this.nutzerRepository = nutzerRepository;
		this.adresseRepository = adresseRepostory;
		this.nutzerService = nutzerService;
		this.produktRepository = produktRepository;
		this.bestellungRepository = bestellungRepository;
		this.bewertungRepository = bewertungRepository;
	}
	
}
