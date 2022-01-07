package ePlattform.server.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ePlattform.server.domainObjects.Auktion;

public interface AuktionRepository extends CrudRepository<Auktion,Integer> {
	
	public List<Auktion> findByAuktionsEndeAfter(Timestamp timestamp);
	
}
