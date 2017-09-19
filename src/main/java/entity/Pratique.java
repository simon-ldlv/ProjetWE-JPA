package entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity @IdClass(KeyPratique.class)
public class Pratique {
    @Id 
    @ManyToOne
    Personne personne;
    
    @Id
    @ManyToOne
    Sport sport;
   
    public static enum Niveau {DEBUTANT, INTERMÉDIAIRE, CONFIRMÉ} ; 
    @Enumerated(EnumType.STRING)
    Niveau niveau;
    ;
    
	public Pratique() {
    }


	public Pratique(Personne personne, Sport sport, Niveau niveau) {
		this.personne = personne;
		this.sport = sport;
		this.niveau = niveau;

	}


	public Personne getPersonne() {
		return personne;
	}


	public void setPersonne(Personne personne) {
		this.personne = personne;
	}


	public Sport getSport() {
		return sport;
	}


	public void setSport(Sport sport) {
		this.sport = sport;
	}
	
    public Niveau getNiveau() {
		return niveau;
	}


	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}


	@Override
	public String toString() {
		return "Pratique [personne=(" +personne.getId()+")"+personne.getName() + ", sport=("+sport.getId() +")"+ sport.getName() + ", niveau=" + niveau + "]";
	}

	

}
