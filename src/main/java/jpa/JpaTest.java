package jpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Sport;
import domain.Pratique.Niveau;
import domain.Personne;
import domain.Pratique;

public class JpaTest {

	/**
	 * Classe de test pour peupler la base
	 */
    public static void main(String[] args) {
       
        	generateData();
    }

	private static void generateData() {
		EntityManager manager = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        
        
        tx.begin();
        try {
        	
        	Sport s1 = new Sport("Tennis");
          	Sport s2 = new Sport("Football");
        	Sport s3 = new Sport("Ping-pong");
        	
        	Personne p1 = new Personne("John");
        	Personne p2 = new Personne("Patrick");
        	Personne p3 = new Personne("Edouard");

        	Pratique p1s1 = new Pratique(p1, s1, Niveau.DEBUTANT);  	
           	Pratique p2s2 = new Pratique(p2,s2,Niveau.INTERMÉDIAIRE);
           	Pratique p3s3 = new Pratique(p3,s3,Niveau.CONFIRMÉ);
           	

        	manager.persist(p1);
        	manager.persist(p2);
        	manager.persist(p3);

        	manager.persist(s1);
        	manager.persist(s2);
        	manager.persist(s3);

        	manager.persist(p1s1);
        	manager.persist(p2s2);
        	manager.persist(p3s3);

        	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    tx.commit();
	    EntityManagerHelper.closeEntityManager();
		
	}
}
