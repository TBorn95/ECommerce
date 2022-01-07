package ePlattform.server.services;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ePlattform.server.domainObjects.Nutzer;
import ePlattform.server.domainObjects.PrivatNutzer;
import ePlattform.server.repositories.NutzerRepository;

//@SpringJUnitConfig
//@ContextConfiguration(classes = {NutzerService.class})
@DataJpaTest
//@SpringBootTest
//@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class NutzerServiceTest {
	
//	@Autowired
//	private NutzerService nutzerService;	
	@Autowired
	private NutzerRepository nutzerRepository;
//	@MockBean()
//	private AdresseRepository adresseRepository;

//Test noch nicht existierender Nutzer ohne Bild
	
	
	@BeforeEach
	public void init() {
		Nutzer nutzer = new PrivatNutzer("benutzername1", "vorname", "nachname", "email" ,"passwort");
		nutzerRepository.save(nutzer);
	}
	
	
	@Test
	public void registerNutzer() throws IOException {

		
		//nutzerRepository.save(nutzer);
//		Adresse adresse = new Adresse("strasse","12", "41065","essen", "deutschland");
//		nutzer.getAdressen().add(adresse);
//		
//		when(nutzerRepository.existsByBenutzername("benutzername")).thenReturn(false);
//		when(adresseRepository.findByHausnummerAndStrasseAndPostleitzahl("strasse","12", "41065")).thenReturn(null);
//		when(nutzerRepository.save(nutzer)).thenReturn(nutzer);
//		
//		Nutzer returnedNutzer = nutzerService.registerNutzer(nutzer);
		//Assertions.assertEquals(false, exists);
	}
}
