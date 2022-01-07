package ePlattform.server.services;

import javax.transaction.Transactional;

import ePlattform.server.domainObjects.Nutzer;
import ePlattform.server.repositories.NachrichtRepository;
import ePlattform.server.repositories.NutzerRepository;

public class NachrichtService {
	
	private NachrichtRepository nachrichtRepository;
	private NutzerRepository nutzerRepository;
	
	@Transactional
	public void sendMessage(int senderId, int empfaengerId, String content) {
		Nutzer sender = nutzerRepository.findById(senderId).get();
		Nutzer empfaenger = nutzerRepository.findById(empfaengerId).get();
		sender.addNachricht(empfaenger, content);
	}
	
	
	public NachrichtService(NachrichtRepository nachrichtRepository, NutzerRepository nutzerRepository) {
		super();
		this.nachrichtRepository = nachrichtRepository;
		this.nutzerRepository = nutzerRepository;
	}
	

	public NachrichtRepository getNachrichtRepository() {
		return nachrichtRepository;
	}

	
	public void setNachrichtRepository(NachrichtRepository nachrichtRepository) {
		this.nachrichtRepository = nachrichtRepository;
	}

	
	public NutzerRepository getNutzerRepository() {
		return nutzerRepository;
	}

	
	public void setNutzerRepository(NutzerRepository nutzerRepository) {
		this.nutzerRepository = nutzerRepository;
	}
	
	
}
