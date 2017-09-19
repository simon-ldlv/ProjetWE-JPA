package dao;

import java.util.List;

import javax.persistence.Query;

import entity.Personne;
import jpa.EntityManagerHelper;

public class PersonneDao implements IPersonneDao {

	private static PersonneDao INSTANCE = null;
	
	public static PersonneDao getInstance()
	{			
		if (INSTANCE == null)
		{ 	INSTANCE = new PersonneDao();	
		}
		return INSTANCE;
	}
	
	@Override
	public void persist(Personne entity) {
		EntityManagerHelper.getEntityManager().persist(entity);
	}

	@Override
	public void update(Personne entity) {
		EntityManagerHelper.getEntityManager().merge(entity);
	}

	@Override
	public Personne findById(long id) {
		Query query = EntityManagerHelper.getEntityManager().createQuery
				("SELECT s FROM Personne s WHERE id = :monId").setParameter("monId", id);
		
		return (Personne) query.getSingleResult();
	}
	
	@Override
	public void delete(Personne entity) {
		EntityManagerHelper.getEntityManager().remove(entity);

	}

	@Override
	public void deleteById(long id) {


		Personne personneToDeleted = findById(id);
		EntityManagerHelper.getEntityManager().remove(personneToDeleted);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Personne> findAll() {
		Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT p FROM Personne p");
		
		return (List<Personne>) query.getResultList();
	}

	@Override
	public void deleteAll() {
		List<Personne> personnes =  findAll();
		for(Personne myPersonne : personnes) {
			delete(myPersonne);
		}
	}

}
