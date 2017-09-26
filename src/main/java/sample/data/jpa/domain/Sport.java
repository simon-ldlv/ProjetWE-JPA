package sample.data.jpa.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Sport {
	
	    @Id
	    @GeneratedValue
	    private long id;

	    @Column(unique=true)
	    private String name;
	    
	    @Column
	    private boolean inside;
	    
	    @ManyToMany
	    private Set<Personne> personnes;
	    
	    public Sport() {
	    	this.personnes = new HashSet<Personne>();
	    }
	    
	    public Sport(String name, boolean inside) {
	    	this.name = name;
	    	this.inside = inside;
	    	this.personnes = new HashSet<Personne>();
	    }

		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}
		
		public boolean isInside() {
			return inside;
		}

		public void setInside(boolean inside) {
			this.inside = inside;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Set<Personne> getPersonnes() {
			return personnes;
		}

		public void setPersonnes(HashSet<Personne> personnes) {
			this.personnes = personnes;
		}


		@Override
		public String toString() {
			return "Sport [id=" + id + ", name=" + name + "]";
		}
	    
		

}
