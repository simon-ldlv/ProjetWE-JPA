package jpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import domain.Sport;
import domain.Pratique.Niveau;
import domain.Lieu;
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

        	
           	Lieu l1 = new Lieu("Rennes");
           	Lieu l2 = new Lieu("Saint-Malo");
           	Lieu l3 = new Lieu("Paris");
           	Lieu l4 = new Lieu("Bordeaux");
           	Lieu l5 = new Lieu("Lille");
           	Lieu l6 = new Lieu("Strasbourg");
  
           	Pratique prat1 = new Pratique(p1,s1,Niveau.DEBUTANT);
           	Pratique prat2 = new Pratique(p2,s2,Niveau.INTERMÉDIAIRE);
           	Pratique prat3 = new Pratique(p3,s3,Niveau.CONFIRMÉ);

           	p1.addLieu(l1);
           	p2.addLieu(l2);
           	p3.addLieu(l3);
 
           	
        	manager.persist(p1);
        	manager.persist(p2);
        	manager.persist(p3);

        	manager.persist(s1);
        	manager.persist(s2);
        	manager.persist(s3);

        	
        	manager.persist(l1);
        	manager.persist(l2);
        	manager.persist(l3);
        	manager.persist(l4);
        	manager.persist(l5);
        	manager.persist(l6);
        	
        	manager.persist(prat1);
        	manager.persist(prat2);
        	manager.persist(prat3);


        	
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    tx.commit();
	    EntityManagerHelper.closeEntityManager();
		
	}
}
