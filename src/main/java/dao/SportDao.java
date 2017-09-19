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
	
	public void persist(Sport entity) {
		EntityManagerHelper.getEntityManager().persist(entity);
	}

	public void update(Sport entity) {
		EntityManagerHelper.getEntityManager().merge(entity);
	}

	public Sport findById(long id) {
		Query query = EntityManagerHelper.getEntityManager().createQuery
				("SELECT s FROM Sport s WHERE id = :monId").setParameter("monId", id);
		
		return (Sport) query.getSingleResult();
	}
	
	public void delete(Sport entity) {
		EntityManagerHelper.getEntityManager().remove(entity);

	}

	public void deleteById(long id) {


		Sport sportToDeleted = findById(id);
		EntityManagerHelper.getEntityManager().remove(sportToDeleted);
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Sport> findAll() {
		Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT s FROM Sport s");
		
		return (List<Sport>) query.getResultList();
	}

	public void deleteAll() {
		List<Sport> sports =  findAll();
		for(Sport mySp : sports) {
			delete(mySp);
		}
	}

}
