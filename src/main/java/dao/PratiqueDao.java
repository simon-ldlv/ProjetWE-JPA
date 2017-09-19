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
	
	@Override
	public void persist(Pratique entity) {
		EntityManagerHelper.getEntityManager().persist(entity);
	}

	@Override
	public void update(Pratique entity) {
		EntityManagerHelper.getEntityManager().merge(entity);
	}

	
	public Pratique findByUserIdSportId(long idu, long ids) {
		Query query = EntityManagerHelper.getEntityManager().createQuery
				("SELECT p FROM Pratique p WHERE personne.id = :monIdu AND sport.id = :monIds").setParameter("monIdu", idu).setParameter("monIds", ids);
		
		return (Pratique) query.getSingleResult();
	}
	
	@Override
	public void delete(Pratique entity) {
		EntityManagerHelper.getEntityManager().remove(entity);

	}

	@Override
	public void deleteByUserIdSportId(long idu, long ids) {

		Pratique pratiqueToDeleted = (Pratique) findByUserIdSportId(idu,ids);
		EntityManagerHelper.getEntityManager().remove(pratiqueToDeleted);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pratique> findAll() {
		Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT p FROM Pratique p");
		
		return (List<Pratique>) query.getResultList();
	}

	@Override
	public void deleteAll() {
		List<Pratique> pratiques =  findAll();
		for(Pratique myPratique : pratiques) {
			delete(myPratique);
		}
	}



}