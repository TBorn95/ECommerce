package ePlattform.server.repositories;

import org.springframework.data.repository.CrudRepository;

import ePlattform.server.domainObjects.Nachricht;

public interface NachrichtRepository extends CrudRepository<Nachricht, Integer> {
	
}
