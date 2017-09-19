package dao;

import java.util.List;

import javax.persistence.Query;

import entity.Lieu;
import jpa.EntityManagerHelper;

public class LieuDao implements ILieuDao {

	private static LieuDao INSTANCE = null;
	
	public static LieuDao getInstance()
	{			
		if (INSTANCE == null)
		{ 	INSTANCE = new LieuDao();	
		}
		return INSTANCE;
	}
	
	@Override
	public void persist(Lieu entity) {
		EntityManagerHelper.getEntityManager().persist(entity);
	}

	@Override
	public void update(Lieu entity) {
		EntityManagerHelper.getEntityManager().merge(entity);
	}

	@Override
	public Lieu findById(long id) {
		Query query = EntityManagerHelper.getEntityManager().createQuery
				("SELECT s FROM Lieu s WHERE id = :monId").setParameter("monId", id);
		
		return (Lieu) query.getSingleResult();
	}
	
	@Override
	public void delete(Lieu entity) {
		EntityManagerHelper.getEntityManager().remove(entity);

	}

	@Override
	public void deleteById(long id) {


		Lieu lieuToDeleted = findById(id);
		EntityManagerHelper.getEntityManager().remove(lieuToDeleted);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Lieu> findAll() {
		Query query = EntityManagerHelper.getEntityManager().createQuery("SELECT l FROM Lieu l");
		
		return (List<Lieu>) query.getResultList();
	}

	@Override
	public void deleteAll() {
		List<Lieu> lieux =  findAll();
		for(Lieu myLieu : lieux) {
			delete(myLieu);
		}
	}

}
