package jpa;


import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dao.LieuDao;
import dao.PersonneDao;
import dao.PratiqueDao;
import dao.SportDao;
import entity.Lieu;
import entity.Personne;
import entity.Pratique;
import entity.Sport;
import entity.Pratique.Niveau;

public class JpaTest {

	/**
	 * Classe de test pour peupler la base
	 */
    public static void main(String[] args) {
       
        	generateData();
        	
        	testFindAllPratiques();
        	
        	testDeleteOnePratique();
        	     	
        	testFindAllPratiques();

    }

	private static void testDeleteAllPratiques() {
    	EntityTransaction trans = EntityManagerHelper.getEntityManager().getTransaction();
    	trans.begin();
		PratiqueDao.getInstance().deleteAll();
    	trans.commit();		
		
	}

	private static void testFindAllPratiques() {
		
		ArrayList<Pratique> pratiques = (ArrayList<Pratique>) PratiqueDao.getInstance().findAll();
		for(Pratique myPrat : pratiques) {
			System.out.println(myPrat);
		}		
	}

	private static void testDeleteOnePratique() {
    	EntityTransaction trans = EntityManagerHelper.getEntityManager().getTransaction();
    	trans.begin();
    	PratiqueDao.getInstance().deleteByUserIdSportId(1,1);
    	trans.commit();		
	}

	private static void testDeleteAllPersonnes() {
    	EntityTransaction trans = EntityManagerHelper.getEntityManager().getTransaction();
    	trans.begin();
		PersonneDao.getInstance().deleteAll();
    	trans.commit();		
	}

	private static void testDeleteOnePersonne() {
    	EntityTransaction trans = EntityManagerHelper.getEntityManager().getTransaction();
    	trans.begin();
    	PersonneDao.getInstance().deleteById(1);
    	trans.commit();			
	}

	private static void testFindAllPersonnes() {
		ArrayList<Personne> personnes = (ArrayList<Personne>) PersonneDao.getInstance().findAll();
		for(Personne myPers : personnes) {
			System.out.println(myPers);
		}		
	}

	private static void testDeleteAllLieux() {
    	EntityTransaction trans = EntityManagerHelper.getEntityManager().getTransaction();
    	trans.begin();
		LieuDao.getInstance().deleteAll();
    	trans.commit();
		
	}

	private static void testDeleteOneLieu() {
    	EntityTransaction trans = EntityManagerHelper.getEntityManager().getTransaction();
    	trans.begin();
		LieuDao.getInstance().deleteById(1);
    	trans.commit();		
	}

	private static void testFindAllLieux() {
		ArrayList<Lieu> lieux = (ArrayList<Lieu>) LieuDao.getInstance().findAll();
		for(Lieu myLieu : lieux) {
			System.out.println(myLieu);
		}
	}

    
	private static void testFindOneLieu() {
		Lieu lieu = LieuDao.getInstance().findById(1);
		System.out.println(lieu);		
	}

	private static void testDeleteAllSports() {
    	EntityTransaction trans = EntityManagerHelper.getEntityManager().getTransaction();
    	trans.begin();
		SportDao.getInstance().deleteAll();
    	trans.commit();

		
	}

	private static void testDeleteOneSport() {
    	EntityTransaction trans = EntityManagerHelper.getEntityManager().getTransaction();
    	trans.begin();
		SportDao.getInstance().deleteById(1);
    	trans.commit();

	}

	private static void testFindOneSport() {
		Sport sport = SportDao.getInstance().findById(1);
		System.out.println(sport);
	}

	private static void testFindAllSport() {
			
			ArrayList<Sport> sports = (ArrayList<Sport>) SportDao.getInstance().findAll();
			for(Sport mySport : sports) {
				System.out.println(mySport);
			}
	}

	private static void generateData() {
		EntityManager manager = EntityManagerHelper.getEntityManager();
        EntityTransaction tx = manager.getTransaction();
        
        
        tx.begin();
        try {
        	
        	Sport s1 = new Sport("Tennis");
          	Sport s2 = new Sport("Football");
        	Sport s3 = new Sport("Ping-pong");
          	Sport s4 = new Sport("Ski");

        	
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
        	manager.persist(s4);
        	
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
