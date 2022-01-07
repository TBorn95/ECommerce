package ePlattform.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import ePlattform.server.domainObjects.Adresse;

@Service
public interface AdresseRepository extends CrudRepository<Adresse, Integer> {
	
	public Adresse findByHausnummerAndStrasseAndPostleitzahl(String hausnummer, String strasse, String postleitzahl);
}
