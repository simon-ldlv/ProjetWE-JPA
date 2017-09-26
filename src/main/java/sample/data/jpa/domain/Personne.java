package sample.data.jpa.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Personne {
	
    @Id
    @GeneratedValue
    private long id;

    private String name;

    
    @ManyToMany(mappedBy="personnes", cascade = CascadeType.MERGE)
    private Set<Lieu> lieux;
    private Set<Sport> sport;


    public Personne() {
    	this.lieux = new HashSet<Lieu>();
    }

    public Personne(String name) {
        this.name = name;
        this.lieux = new HashSet<Lieu>();
    }

    
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set<Lieu> getLieux() {
		return lieux;
	}

	public void setLieux(HashSet<Lieu> lieux) {
		this.lieux = lieux;
	}

	public Set<Sport> getSport() {
		return sport;
	}

	public void setSport(Set<Sport> sport) {
		this.sport = sport;
	}

	public String toString() {
		String toDisplay = "Personne [id=" + id + ", name=" + name + ", lieux = ";
		
		for(Lieu lieu : lieux) {
			toDisplay = toDisplay + lieu.getName() + " / ";
		}
		
		toDisplay = toDisplay + "]";
		
		return toDisplay;
	}
	
	public String toJson() {
		String toDisplay = "Personne [id=" + id + ", name=" + name + ", lieux = ";
		
		for(Lieu lieu : lieux) {
			toDisplay = toDisplay + lieu.getName() + " / ";
		}
		
		toDisplay = toDisplay + "]";
		
		return toDisplay;
	}

	
	
    
}
