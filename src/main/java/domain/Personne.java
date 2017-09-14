package domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Personne {
	
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "personne", cascade = CascadeType.PERSIST)
    private List<Pratique> pratiques;


    public Personne() {
    
    }

    public Personne(String name) {
        this.name = name;
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
    
    
    
}
