package dao;

import java.util.List;

import entity.Personne;

public interface IPersonneDao {


	public void persist(Personne entity);
	
	public void update(Personne entity);
	
	public Personne findById(long id);
	
	public void delete(Personne entity);
	
	public void deleteById(long id);
	
	public List<Personne> findAll();
	
	public void deleteAll();
	
}
