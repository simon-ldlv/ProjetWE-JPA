package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Lieu {
	
	    @Id
	    @GeneratedValue
	    private Long id;

	    private String name;
	    
	    @ManyToMany
	    private List<Personne> personnes;
	    
	    public Lieu() {
	    	this.personnes = new ArrayList<Personne>();
	    }
	    
	    public Lieu(String name) {
	    	this.name = name;
	    	this.personnes = new ArrayList<Personne>();
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Personne> getPersonnes() {
			return personnes;
		}

		public void setPersonnes(List<Personne> personnes) {
			this.personnes = personnes;
		}

		public void addPersonne(Personne pers) throws IllegalArgumentException, IllegalStateException {

			if(pers==null) throw new IllegalArgumentException("ERROR addPersonne");
			if(personnes.contains(pers))  throw new IllegalStateException("ERROR addPersonne");
			if(pers.getLieux().contains(this)) throw new IllegalStateException("ERROR addPersonne");
			pers.getLieux().add(this);
			personnes.add(pers);
		
		}
		
		public void removePersonne(Personne pers) throws IllegalArgumentException, IllegalStateException {
			
			if(!pers.getLieux().contains(this)) throw new IllegalStateException("ERROR removePersonne");
			if(!personnes.contains(pers)) throw new IllegalStateException("ERROR removePersonne");
			personnes.remove(pers);
			pers.getLieux().remove(this);
			
		}

		@Override
		public String toString() {
			return "Lieu [id=" + id + ", name=" + name + "]";
		}
	    
		

}
