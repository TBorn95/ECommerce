package ePlattform.server.services;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ePlattform.server.domainObjects.AbgelaufeneAuktion;
import ePlattform.server.domainObjects.Auktion;
import ePlattform.server.domainObjects.Gebot;
import ePlattform.server.domainObjects.Nutzer;
import ePlattform.server.domainObjects.PrivatNutzer;
import ePlattform.server.helperClasses.EmailService;
import ePlattform.server.repositories.AbgelaufeneAuktionRepository;
import ePlattform.server.repositories.AuktionRepository;
import ePlattform.server.repositories.NutzerRepository;

@Service
@EnableAsync
public class AuktionService {
	
	private AuktionRepository auktionRepository;
	private NutzerRepository nutzerRepository;
	private EmailService emailService;
	private AbgelaufeneAuktionRepository abgelaufeneAuktionRepository;
	
	
	@Transactional
	public Auktion auktionErstellen(String erstellerName, Auktion auktion) {
		PrivatNutzer ersteller = (PrivatNutzer) nutzerRepository.findByBenutzername(erstellerName);
		ersteller.addEigeneAuktion(auktion);
		return auktion;
	}
	
	
	@Transactional
	public boolean bieten(String bieterName, int auktionId, double betrag) {
		Timestamp bietStamp = new Timestamp(System.currentTimeMillis());
		Auktion auktion = auktionRepository.findById(auktionId).get();
		PrivatNutzer bieter = (PrivatNutzer) nutzerRepository.findByBenutzername(bieterName);
		if(auktion == null) return false;
		else {			
			if(auktion.getAuktionsEnde().after(bietStamp) 
					|| auktion.getAktuellesGebot() + auktion.getMindestgebot() >= betrag) {
				return false;
			}else {
				auktion.setAktuellesGebot(betrag);
				bieter.addGebot(auktion, betrag);
				return true;
			}
		}		
	}
	
	
	@Transactional
	public Auktion auktionMerken(String benutzername, int auktionId) {
		Auktion auktion = auktionRepository.findById(auktionId).get();
		if(auktion != null) {
		PrivatNutzer nutzer = (PrivatNutzer) nutzerRepository.findByBenutzername(benutzername);
		nutzer.addGemerkteAuktion(auktion);
		return auktion;
		}
		return null;
	}
	
	
	@Transactional
	public void auktionEntmerken(String benutzername, int auktionId) {
		Auktion auktion = auktionRepository.findById(auktionId).get();
		PrivatNutzer nutzer = (PrivatNutzer) nutzerRepository.findByBenutzername(benutzername);
		nutzer.removeGemerkteAuktion(auktion);
	}
			
	
	@Async
	@Transactional
	public void checkAuktionen() {
		while(true) {
			
			try {
				Thread.sleep(20000);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			List<Auktion> abgelaufeneAuktionen = auktionRepository.findByAuktionsEndeAfter(new Timestamp(System.currentTimeMillis()));
			abgelaufeneAuktionen.forEach(auktion -> {
				Gebot hoechstGebot = Collections.max(auktion.getGebote(), Comparator.comparing(Gebot::getGebot));
				Nutzer hoechstBieter = hoechstGebot.getBieter();
				Nutzer anbieter = auktion.getAnbieter();
				emailService.sendMail(emailService.generateAnbieterTextAuktionen(anbieter.getNachname(), auktion.getName(),
						hoechstGebot.getGebot(), hoechstBieter.getBenutzername()), anbieter.getEmail());
				emailService.sendMail(emailService.generateErsteigererTextAuktionen(hoechstBieter.getNachname(), auktion.getName(),
						hoechstGebot.getGebot()), anbieter.getEmail());
				abgelaufeneAuktionRepository.save(new AbgelaufeneAuktion(auktion.getName(), auktion.getBeschreibung(), auktion.getBild(), auktion.getStartpreis(),
						auktion.getMindestgebot(), auktion.getAktuellesGebot(), auktion.isSelbstabholung(), auktion.getAuktionsEnde()));
				auktionRepository.delete(auktion);				
			});
		}
	}
	
			
	@Autowired
	public AuktionService(AuktionRepository auktionRepository, NutzerRepository nutzerRepository, EmailService emailService,AbgelaufeneAuktionRepository abgelaufeneAuktionRepository) {
		super();
		this.auktionRepository = auktionRepository;
		this.nutzerRepository = nutzerRepository;
		this.emailService = emailService;
		this.abgelaufeneAuktionRepository = abgelaufeneAuktionRepository;
	}		
}
