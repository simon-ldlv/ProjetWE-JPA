package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import entity.Pratique;
import entity.Sport;
import jpa.EntityManagerHelper;

public class PratiqueDao implements IPratiqueDao {

private static PratiqueDao INSTANCE = null;
	
	public static PratiqueDao getInstance()
	{			
		if (INSTANCE == null)
		{ 	INSTANCE = new PratiqueDao();	
		}
		return INSTANCE;
	}
	
	public void persist(Pratique entity) {
		EntityManagerHelper.getEntityManager().persist(entity);
	}

	public void update(Pratique entity) {
		EntityManagerHelper.getEntityManager().merge(entity);
	}

	
	public Pratique findByUserIdSportId(long idu, long ids) {
		Query query = EntityManagerHelper.getEntityManager().createQuery
				("SELECT p FROM Pratique p WHERE personne.id = :monIdu AND sport.id = :monIds").setParameter("monIdu", idu).setParameter("monIds", ids);
		
		return (Pratique) query.getSingleResult();
	}
	
	public void delete(Pratique entity) {
		EntityManagerHelper.getEntityManager().remove(entity);

	}

	public void deleteByUserIdSportId(long idu, long ids) {

		Pratique pratiqueToDeleted = (Pratique) findByUserIdSportId(idu,ids);
		EntityManagerHelper.getEntityManager().remove(pratiqueToDeleted);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Pratique> findAll() {
		Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT p FROM Pratique p");
		
		return (List<Pratique>) query.getResultList();
	}

	public void deleteAll() {
		List<Pratique> pratiques =  findAll();
		for(Pratique myPratique : pratiques) {
			delete(myPratique);
		}
	}



}
