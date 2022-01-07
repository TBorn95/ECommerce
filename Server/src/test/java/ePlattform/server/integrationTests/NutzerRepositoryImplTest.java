package ePlattform.server.integrationTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import ePlattform.server.domainObjects.Adresse;
import ePlattform.server.domainObjects.GewerbeNutzer;
import ePlattform.server.domainObjects.Nutzer;
import ePlattform.server.domainObjects.PrivatNutzer;
import ePlattform.server.domainObjects.Produkt;
import ePlattform.server.repositories.NutzerRepository;
import ePlattform.server.repositories.NutzerRepositoryImpl;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class NutzerRepositoryImplTest {
	
	NutzerRepository nutzerRepository;

	//Repository to test
	NutzerRepositoryImpl nutzerRepositoryImpl;
	
	Nutzer testNutzer;
	
	@BeforeAll
	@Transactional
	public void setup() {
		Nutzer nutzer = new PrivatNutzer("benutzername","vorname","nachname","email","passwort");
		GewerbeNutzer anbieter = new GewerbeNutzer( "vorname", "nachname", "gewerbe", "email", "password"," gewerbename");
		Adresse adresse = new Adresse("strasse","hausnummer","plz","stadt","land");
		nutzer.getAdressen().add(adresse);
		
		Produkt produkt1 = new Produkt("produkt1","beschreibung",20,"bildstring","beschreibung","kategprie",anbieter);
		Produkt produkt2 = new Produkt("produkt2","beschreibung",20,"bildstring","beschreibung","kategprie",anbieter);
		Produkt produkt3 = new Produkt("produkt3","beschreibung",20,"bildstring","beschreibung","kategprie",anbieter);
		anbieter.produktAnbieten(produkt1);
		anbieter.produktAnbieten(produkt2);
		anbieter.produktAnbieten(produkt3);
		nutzerRepository.save(anbieter);
		
		nutzer.addAngesehenesProdukt(produkt1);
		nutzer.addAngesehenesProdukt(produkt2);
		nutzer.addAngesehenesProdukt(produkt3);		
		nutzerRepository.save(nutzer);
		
		testNutzer = nutzerRepositoryImpl.findByBenutzernameFetched("benutzername");	
	}

	
	@Test
	@Transactional
	public void testRepository() {
		//test
		//Nutzer testNutzer = nutzerRepositoryImpl.findByBenutzernameFetched("benutzername");	
		//check
		Assertions.assertAll(
				() -> Assertions.assertEquals(3, testNutzer.getAngeseheneProdukte().size()),
				() -> Assertions.assertEquals(1, testNutzer.getAdressen().size()),
				() -> Assertions.assertEquals("produkt1",testNutzer.getAngeseheneProdukte().get(0).getProdukt().getProduktname()));
	}
	
	
	//Clean up Database after this!
	@Test
	public void testRepositoryOutOfTransaction() {
	//	Nutzer testNutzer = nutzerRepositoryImpl.findByBenutzernameFetched("benutzername");	
		//check
		Assertions.assertAll(
				() -> Assertions.assertEquals(3, testNutzer.getAngeseheneProdukte().size()),
				() -> Assertions.assertEquals(1, testNutzer.getAdressen().size()),
				() -> Assertions.assertEquals("produkt1",testNutzer.getAngeseheneProdukte().get(0).getProdukt().getProduktname()));
	}
	
	
	
	
	
	@Autowired
	public NutzerRepositoryImplTest(NutzerRepositoryImpl nutzerRepositoryImpl, NutzerRepository nutzerRepository) {
		super();
		this.nutzerRepositoryImpl = nutzerRepositoryImpl;
		this.nutzerRepository = nutzerRepository;
	}		
}
