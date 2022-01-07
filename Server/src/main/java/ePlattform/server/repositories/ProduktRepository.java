package ePlattform.server.repositories;

import org.springframework.data.repository.CrudRepository;

import ePlattform.server.domainObjects.Produkt;

public interface ProduktRepository extends CrudRepository<Produkt, Integer> {
	
}
