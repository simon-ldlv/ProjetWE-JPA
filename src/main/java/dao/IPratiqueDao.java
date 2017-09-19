package dao;

import java.util.List;

import entity.Pratique;

public interface IPratiqueDao {


	public void persist(Pratique entity);
	
	public void update(Pratique entity);
	
	public Pratique findByUserIdSportId(long idu, long ids);
	
	public void delete(Pratique entity);
	
	public void deleteByUserIdSportId(long idu, long ids);
	
	public List<Pratique> findAll();
	
	public void deleteAll();
	
}
