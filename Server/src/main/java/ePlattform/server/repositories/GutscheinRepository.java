package ePlattform.server.repositories;

import org.springframework.data.repository.CrudRepository;

import ePlattform.server.domainObjects.Gutschein;

public interface GutscheinRepository extends CrudRepository<Gutschein, Integer> {
	
	public boolean existsByCode(int code);
	public Gutschein findByCode(int code);
}
