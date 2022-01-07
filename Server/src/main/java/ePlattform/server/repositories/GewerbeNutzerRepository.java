package ePlattform.server.repositories;

import org.springframework.data.repository.CrudRepository;

import ePlattform.server.domainObjects.GewerbeNutzer;

public interface GewerbeNutzerRepository extends CrudRepository<GewerbeNutzer, Integer>{
	public boolean existsByGewerbename(String gewerbename);
}
