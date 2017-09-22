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

@Controller
public class LieuController {
	  // Private fields

	  @Autowired
	  private LieuRepository lieuRepo;
	  
	  @RequestMapping("/lieu")
	  @ResponseBody
	  public String getAllLieu() {
		  List<Lieu> lieux;
		  String toDisplay = "";

		  try {
			  lieux = lieuRepo.findAll();
			  for(Lieu lieu : lieux) {
				  toDisplay = toDisplay + lieu.toString();
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
  @RequestMapping("/lieu/create/{name}")
  @ResponseBody
  public String create(@PathVariable String name) {
    String persId = "";
    try {
      Lieu pers = new Lieu(name);
      lieuRepo.save(pers);
      persId = String.valueOf(pers.getId());
    }
    catch (Exception ex) {
      return "Error creating the lieu: " + ex.toString();
    }
    return "lieu succesfully created with id = " + persId;
  }
  
  /**
   * GET /delete  --> Delete the user having the passed id.
   */
  @RequestMapping("/lieu/delete/{id}")
  @ResponseBody
  public String delete(@PathVariable long id) {
    try {
    	Lieu lieu = lieuRepo.findOne(id);
    	lieuRepo.delete(lieu);
    }
    catch (Exception ex) {
      return "Error deleting the lieu:" + ex.toString();
    }
    return "Lieu with id "+id+" successfully deleted!";
  }
  
  /**
   * GET /get-by-email  --> Return the id for the user having the passed
   * email.
   */
  @RequestMapping("/lieu/{id}")
  @ResponseBody
  public String get(@PathVariable long id) {
	Lieu lieu;
    try {
      lieu = lieuRepo.findOne(id);
    }
    catch (Exception ex) {
      return "Lieu with id "+id+" not found";
    }
    return lieu.toString(); 
  }
  
  
  @RequestMapping("/lieu/getByName/{name}")
  @ResponseBody
  public String get(@PathVariable String name) {
	Lieu lieu;
    try {
      lieu = lieuRepo.findByName(name);
    }
    catch (Exception ex) {
      return "Lieu with name'"+name+"' not found";
    }
    return lieu.toString(); 
  }
  
  
  @RequestMapping("/lieu/update/{id}/{name}")
  @ResponseBody
  public String update(@PathVariable long id, @PathVariable String name) {
    try {
      Lieu lieu = lieuRepo.findOne(id);
      lieu.setName(name);
      lieuRepo.save(lieu);
    }
    catch (Exception ex) {
      return "Error updating the lieu with id"+id+" : " + ex.toString();
    }
    return "Lieu with id "+id+" successfully updated with new name "+name;
  }


  
}