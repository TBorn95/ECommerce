package ePlattform.server.services;



import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ePlattform.server.domainObjects.Gutschein;
import ePlattform.server.domainObjects.Nutzer;
import ePlattform.server.repositories.GutscheinRepository;
import ePlattform.server.repositories.NutzerRepository;

@Service
public class GutscheinService {
	
	private GutscheinRepository gutscheinRepository;
	private NutzerRepository nutzerRepository;
	
	
	@Transactional
	public Gutschein gutscheinAnfordern(String kaeuferName, double betrag) {
		Nutzer kaeufer = nutzerRepository.findByBenutzername(kaeuferName);
		
		if(kaeufer.getSaldo() < betrag) {
			return null;
			
		}else {				
			int code;
		
			do {
				code = this.generateCode();
			}while(!gutscheinRepository.existsByCode(code));
			
			Gutschein gutschein = new Gutschein(code, betrag);
			gutscheinRepository.save(gutschein);
			kaeufer.setSaldo(kaeufer.getSaldo() - betrag);
			return gutschein;
		}
	}
	
	
	@Transactional
	public double gutscheinEinloesen(String benutzerName, int code) {
		Nutzer einloeser = nutzerRepository.findByBenutzername("benutzerName");
		Gutschein gutschein = gutscheinRepository.findByCode(code);
		
		if(gutschein != null) {
			einloeser.setSaldo(einloeser.getSaldo() + gutschein.getWert());
			gutscheinRepository.delete(gutschein);
			return einloeser.getSaldo();
		}
		return 0;
	}
	

	@Autowired
	public GutscheinService(GutscheinRepository gutscheinRepository, NutzerRepository nutzerRepository) {
		super();
		this.gutscheinRepository = gutscheinRepository;
		this.nutzerRepository = nutzerRepository;
	}
	
	
	private int generateCode() {
		Random random = new Random();
		String code = "";
		for(int i = 0; i < 12; i++) {
			code.concat(random.nextInt(10) +"");
		}
		return Integer.parseInt(code);
	}
}
