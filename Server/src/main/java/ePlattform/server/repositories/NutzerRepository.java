package ePlattform.server.repositories;

import org.springframework.data.repository.CrudRepository;

import ePlattform.server.domainObjects.Nutzer;

public interface NutzerRepository extends CrudRepository<Nutzer, Integer> {
	
	public boolean existsByBenutzername(String benutzername);
	public Nutzer findByBenutzername(String benutzername);
	public Nutzer findByBenutzernameFetched(String benutzername);
	public Nutzer findByBenutzernameFetchedEntity(String benutzername);
	
	

}
