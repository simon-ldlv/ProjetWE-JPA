package dao;

import java.util.List;

import entity.Lieu;

public interface ILieuDao {


	public void persist(Lieu entity);
	
	public void update(Lieu entity);
	
	public Lieu findById(long id);
	
	public void delete(Lieu entity);
	
	public void deleteById(long id);
	
	public List<Lieu> findAll();
	
	public void deleteAll();
	
}
