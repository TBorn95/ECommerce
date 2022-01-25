//package ePlattform.server.integrationTests;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import ePlattform.server.domainObjects.GewerbeNutzer;
//import ePlattform.server.domainObjects.Nutzer;
//import ePlattform.server.domainObjects.PrivatNutzer;
//import ePlattform.server.domainObjects.Produkt;
//import ePlattform.server.repositories.AngesehenesProduktRepository;
//import ePlattform.server.repositories.BestellungRepository;
//import ePlattform.server.repositories.NutzerRepository;
//import ePlattform.server.repositories.ProduktRepository;
//import ePlattform.server.services.ProduktService;
//
//@SpringBootTest
//@Transactional
//@AutoConfigureTestDatabase(replace = Replace.NONE)
//public class ProduktServiceTest {
//
//	ProduktRepository produktRepository;
//	NutzerRepository nutzerRepository;
//	BestellungRepository bestellungRepository;
//	AngesehenesProduktRepository aProduktRepository;
//	GewerbeNutzer anbieter;
//	Produkt produkt1, produkt2, produkt3, produkt4, produkt5, produkt6;
//	List<Produkt> hochzuladendeProdukte = new ArrayList<>();
//
//	//Test Object
//	ProduktService produktService;
//
//	@BeforeEach
//	public void setUp() {
//		anbieter = new GewerbeNutzer( "vorname", "nachname", "gewerbeanbieter", "email", "password"," gewerbename");
//		anbieter = nutzerRepository.save(anbieter);
//		produkt1 = new Produkt("produkt1","beschreibung",20,"bildstring","beschreibung","kategprie",anbieter);
//		produkt2 = new Produkt("produkt2","beschreibung",20,"bildstring","beschreibung","kategprie",anbieter);
//		produkt3 = new Produkt("produkt3","beschreibung",20,"bildstring","beschreibung","kategprie",anbieter);
//		produkt4 = new Produkt("produkt4","beschreibung",20,"bildstring","beschreibung","kategprie",anbieter);
//		produkt5 = new Produkt("produkt4","beschreibung",20,"bildstring","beschreibung","kategprie",anbieter);
//		produkt6 = new Produkt("produkt4","beschreibung",20,"bildstring","beschreibung","kategprie",anbieter);
//		hochzuladendeProdukte.add(produkt1);
//		hochzuladendeProdukte.add(produkt2);
//		hochzuladendeProdukte.add(produkt3);
//		hochzuladendeProdukte.add(produkt4);
//		hochzuladendeProdukte.add(produkt5);
//		hochzuladendeProdukte.add(produkt6);
//	}
//
//	@Test
//	//umbennenen
//	public void produktUpload() {
//		//test
//		produktService.uploadProdukt(hochzuladendeProdukte, anbieter.getBenutzername());
//		//check
//		Assertions.assertAll(
//				() -> Assertions.assertEquals(6, anbieter.getAngeboteneProdukte().size()),
//				() -> Assertions.assertEquals(6, produktRepository.count()));
//	}
//
//	@Test
//	public void produktAnsehen() {
//		Nutzer anseher = new PrivatNutzer("anseher","vorname","nachname","email","passwort");
//		anseher = nutzerRepository.save(anseher);
//		produkt1 = produktRepository.save(produkt1);
//		produkt2 = produktRepository.save(produkt2);
//		produkt3 = produktRepository.save(produkt3);
//		produkt4 = produktRepository.save(produkt4);
//		produkt5 = produktRepository.save(produkt5);
//		produkt6 = produktRepository.save(produkt6);
//		//test
//		produktService.produktAnsehen("anseher", produkt1);
//		produktService.produktAnsehen("anseher", produkt2);
//		produktService.produktAnsehen("anseher", produkt3);
//		produktService.produktAnsehen("anseher", produkt4);
//		produktService.produktAnsehen("anseher", produkt5);
//
//		Assertions.assertEquals(5, aProduktRepository.count());
//		Assertions.assertEquals(5, anseher.getAngeseheneProdukte().size());
//
//		produktService.produktAnsehen("anseher", produkt6);
//
//		Assertions.assertEquals(5, aProduktRepository.count());
//		Assertions.assertEquals(5, anseher.getAngeseheneProdukte().size());
//		Assertions.assertTrue(anseher.getAngeseheneProdukte().stream().filter(p -> p.getProdukt().equals(produkt6)).findFirst().isPresent());
//		Assertions.assertTrue(anseher.getAngeseheneProdukte().stream().filter(p->p.getProdukt().equals(produkt1)).findFirst().isEmpty());
//	}
//
//	@Test
//	public void produktkaufen() {
//		//setup
//		produkt1 = produktRepository.save(produkt1);
//		produkt2 = produktRepository.save(produkt2);
//		Nutzer kaeufer = new PrivatNutzer("kaeufer","vorname","nachname","email","passwort");
//		kaeufer.setSaldo(30);
//		kaeufer = nutzerRepository.save(kaeufer);
//		//test
//		Produkt gekauftesProdukt= produktService.produktKaufen("kaeufer", produkt1.getProduktId());
//		//check
//		Assertions.assertEquals(10, kaeufer.getSaldo());
//		Assertions.assertEquals(20, anbieter.getSaldo());
//		Assertions.assertEquals(produkt1, gekauftesProdukt);
//		Assertions.assertEquals(1, bestellungRepository.count());
//		//test
//		gekauftesProdukt = produktService.produktKaufen("kaeufer", produkt2.getProduktId());
//		//check
//		Assertions.assertEquals(10, kaeufer.getSaldo());
//		Assertions.assertEquals(20, anbieter.getSaldo());
//		Assertions.assertNull(gekauftesProdukt);
//		Assertions.assertEquals(1, bestellungRepository.count());
//	}
//
//	@Autowired
//	public ProduktServiceTest(ProduktRepository produktRepository, NutzerRepository nutzerRepository,
//			ProduktService produktService, AngesehenesProduktRepository aProduktRepository, BestellungRepository bestellungRepository) {
//		super();
//		this.produktRepository = produktRepository;
//		this.nutzerRepository = nutzerRepository;
//		this.produktService = produktService;
//		this.aProduktRepository = aProduktRepository;
//		this.bestellungRepository = bestellungRepository;
//	}
//
//}
