package domain;

import java.util.List;

import javax.persistence.*;



@Entity
public class Sport {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    
    @OneToMany(mappedBy = "sport", cascade = CascadeType.PERSIST)
    private List<Pratique> pratiques;

    public Sport() {
        super();
    }

    public Sport(String name) {
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
