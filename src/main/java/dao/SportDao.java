package dao;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entity.Sport;
import jpa.EntityManagerHelper;

public class SportDao implements ISportDao {

	private static SportDao INSTANCE = null;
	
	public static SportDao getInstance()
	{			
		if (INSTANCE == null)
		{ 	INSTANCE = new SportDao();	
		}
		return INSTANCE;
	}
	
	@Override
	public void persist(Sport entity) {
		EntityManagerHelper.getEntityManager().persist(entity);
	}

	@Override
	public void update(Sport entity) {
		EntityManagerHelper.getEntityManager().merge(entity);
	}

	@Override
	public Sport findById(long id) {
		Query query = EntityManagerHelper.getEntityManager().createQuery
				("SELECT s FROM Sport s WHERE id = :monId").setParameter("monId", id);
		
		return (Sport) query.getSingleResult();
	}
	
	@Override
	public void delete(Sport entity) {
		EntityManagerHelper.getEntityManager().remove(entity);

	}

	@Override
	public void deleteById(long id) {


		Sport sportToDeleted = findById(id);
		EntityManagerHelper.getEntityManager().remove(sportToDeleted);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Sport> findAll() {
		Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT s FROM Sport s");
		
		return (List<Sport>) query.getResultList();
	}

	@Override
	public void deleteAll() {
		List<Sport> sports =  findAll();
		for(Sport mySp : sports) {
			delete(mySp);
		}
	}

}
