package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import entity.Pratique.Niveau;



@Entity
public class Sport {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    
    @OneToMany(mappedBy = "sport", cascade = CascadeType.REMOVE)
    private List<Pratique> pratiques;

    public Sport() {
        super();
    }

    public Sport(String name) {
        this.name = name;
        pratiques = new ArrayList<Pratique>();
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

	@Override
	public String toString() {
		return "Sport [id=" + id + ", name=" + name + "]";
	}

	
	
}
