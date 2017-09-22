package sample.data.jpa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.Lieu;
import sample.data.jpa.domain.Personne;
import sample.data.jpa.service.LieuRepository;
import sample.data.jpa.service.PersonneRepository;

@Controller
public class PersonneController {
	  // Private fields

	  @Autowired
	  private PersonneRepository personneRepo;
	  @Autowired
	  private LieuRepository lieuRepo;

	  
	  
	  @RequestMapping("/personne")
	  @ResponseBody
	  public String getAllPersonne() {
		  List<Personne> personnes;
		  String toDisplay = "";

		  try {
			  personnes = personneRepo.findAll();
			  for(Personne pers : personnes) {
				  toDisplay = toDisplay + pers.toString();
				  toDisplay = toDisplay+"<br />";
			  }
		  }
		  catch (Exception ex) {
			  return "Errors occur while get all personnes.";
	      }
		  return toDisplay;
	  }
	  
  /**
   * GET /create  --> Create a new user and save it in the database.
   */
  @RequestMapping("/personne/create/{name}")
  @ResponseBody
  public String create(@PathVariable String name) {
    String persId = "";
    try {
      Personne pers = new Personne(name);
      personneRepo.save(pers);
      persId = String.valueOf(pers.getId());
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "Personne successfully named "+name+" created with id = " + persId;
  }
  
  @RequestMapping("/personne/delete/{id}")
  @ResponseBody
  public String delete(@PathVariable long id) {
    try {
    	Personne pers = personneRepo.findOne(id);
       	personneRepo.delete(pers);
    }
    catch (Exception ex) {
      return "Error deleting the personne:" + ex.toString();
    }
    return "Personne with id "+id+" successfully deleted!";
  }

  
  /**
   * GET /get-by-email  --> Return the id for the user having the passed
   * email.
   */	
  @RequestMapping("/personne/{id}")
  @ResponseBody
  public String getById(@PathVariable long id) {
	Personne pers;
    try {
      pers = personneRepo.findOne(id);
    }
    catch (Exception ex) {
      return "Personne with id "+id+" not found";
    }
    return pers.toString(); 
  }
  
  @RequestMapping("/personne/getByName/{name}")
  @ResponseBody
  public String getByName(@PathVariable String name) {
	Personne pers;
    try {
      pers = personneRepo.findByName(name);
    }
    catch (Exception ex) {
      return "Personne with name'"+name+"' not found";
    }
    return pers.toString(); 
  }
  
  /**
   * GET /update  --> Update the email and the name for the user in the 
   * database having the passed id.
   *
   */
  @RequestMapping("/personne/update/{id}/{name}")
  @ResponseBody
  public String updatePersonne(@PathVariable long id, @PathVariable String name) {
    try {
      Personne pers = personneRepo.findOne(id);
      pers.setName(name);
      personneRepo.save(pers);
    }
    catch (Exception ex) {
    	return "Error updating the user: " + ex.toString();
    }
    return "Personne with id "+id+" successfully updated with new name "+name;
  }
  
  @RequestMapping("/personne/{idp}/addLieu/{idl}")
  @ResponseBody
  public String addLieu(@PathVariable long idp, @PathVariable long idl) {
    try {
      Personne pers = personneRepo.findOne(idp);
      if(pers==null) {
    	  return "ERROR : no personne with id "+idp+" exists.";
      }
      
      Lieu lieu = lieuRepo.findOne(idl);
      if(lieu==null) {
    	  return "ERROR : no lieu with id "+idl+" exists.";
      }

      for(Lieu lieuToTest : pers.getLieux()) {
    	  if(lieu.getName().equals(lieuToTest.getName())) {
    		  return "ERROR : the lieu "+lieu.getName()+" already exist for the personne "+pers.getName()+".";
    	  }
      }
      lieu.getPersonnes().add(pers);
      lieuRepo.saveAndFlush(lieu);

    }
    catch (Exception ex) {
      return "Error adding the lieu (id="+idl+") to the personne (id="+idp+") : " + ex.toString();
    }
    return "The Lieu with id "+idl+" successfully adding to personne with id "+idp;
  }
  



  
}