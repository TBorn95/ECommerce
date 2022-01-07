package ePlattform.server.repositories;

import org.springframework.data.repository.CrudRepository;

import ePlattform.server.domainObjects.Bestellung;
import ePlattform.server.domainObjects.Bewertung;

public interface BewertungRepository extends CrudRepository<Bewertung, Integer>{
	
	public boolean existsByBestellung(Bestellung bestellung);
}
