package dao;

import java.util.List;

import entity.Sport;

public interface ISportDao {


	public void persist(Sport entity);
	
	public void update(Sport entity);
	
	public Sport findById(long id);
	
	public void delete(Sport entity);
	
	public void deleteById(long id);
	
	public List<Sport> findAll();
	
	public void deleteAll();
	
}
