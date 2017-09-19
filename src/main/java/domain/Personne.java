package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import domain.Pratique.Niveau;

@Entity
public class Personne {
	
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "personne", cascade = CascadeType.PERSIST)
    private List<Pratique> pratiques;
    
    @ManyToMany(mappedBy="personnes", cascade = CascadeType.PERSIST)
    private List<Lieu> lieux;


    public Personne() {
    	this.lieux = new ArrayList<Lieu>();
    }

    public Personne(String name) {
        this.name = name;
        this.lieux = new ArrayList<Lieu>();
        this.pratiques = new ArrayList<Pratique>();

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


	public List<Pratique> getPratiques() {
		return pratiques;
	}


	public void setPratiques(List<Pratique> pratiques) {
		this.pratiques = pratiques;
	}

	public List<Lieu> getLieux() {
		return lieux;
	}

	public void setLieux(List<Lieu> lieux) {
		this.lieux = lieux;
	}
	
	public void addLieu(Lieu lieu) throws IllegalArgumentException, IllegalStateException {
		
		if(lieu==null) throw new IllegalArgumentException("ERROR addLieu");
		if(lieux.contains(lieu)) throw new IllegalStateException("ERROR addLieu");
		if(lieu.getPersonnes().contains(this)) throw new IllegalStateException("ERROR addLieu");;
		lieux.add(lieu);
		lieu.getPersonnes().add(this);
		
	}
    
	public void removeLieu(Lieu lieu) throws IllegalArgumentException, IllegalStateException {
		
		if(!lieu.getPersonnes().contains(this)) throw new IllegalStateException("ERROR removeLieu");
		if(!lieux.contains(lieu)) throw new IllegalStateException("ERROR removeLieu");
		lieux.remove(lieu);
		lieu.getPersonnes().remove(this);

	}

    
}
