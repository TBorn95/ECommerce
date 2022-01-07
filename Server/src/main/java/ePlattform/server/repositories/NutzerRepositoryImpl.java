package ePlattform.server.repositories;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ePlattform.server.domainObjects.Nutzer;

@Component
public class NutzerRepositoryImpl {

	@PersistenceContext
	private EntityManager em;
	
	
	@Transactional
	public Nutzer findByBenutzernameFetched(String benutzername) {
		String jpql = "SELECT DISTINCT n FROM Nutzer n JOIN FETCH n.adressen WHERE n.benutzername =: benutzername";
		Nutzer nutzer = em.createQuery(jpql, Nutzer.class).setParameter("benutzername", benutzername)
				.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false).getSingleResult();
		
		jpql = "SELECT DISTINCT n from Nutzer n JOIN FETCH n.angeseheneProdukte WHERE n.idNutzer =: idNutzer";
		nutzer = em.createQuery(jpql, Nutzer.class).setParameter("idNutzer", nutzer.getIdNutzer()).
				setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false).getSingleResult();
		
		return nutzer;
	}
	
	
	public Nutzer findByBenutzernameFetchedEntity(String benutzername) {
		EntityGraph graph = this.em.getEntityGraph("graph.nutzer.login");
//		Map map = new HashMap();
//		map.put("javax.persistence.fetchgraph", graph);
		Nutzer nutzer = em.createQuery("SELECT n FROM Nutzer n WHERE n.benutzername = :benutzername", Nutzer.class)
				.setParameter("benutzername", benutzername)
				.setHint(QueryHints.HINT_FETCHGRAPH, graph)
				.getSingleResult();
		return nutzer;
		//return this.em.find(Nutzer.class, benutzername, map);
	}
	
}
