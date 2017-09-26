package sample.data.jpa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sample.data.jpa.domain.Sport;
import sample.data.jpa.service.SportRepository;

@Controller
public class SportController {
	  // Private fields

	  @Autowired
	  private SportRepository sportRepo;
	  
	  @RequestMapping("/sport")
	  @ResponseBody
	  public String getAllsport() {
		  List<Sport> Sports;
		  String toDisplay = "";

		  try {
			  Sports = sportRepo.findAll();
			  for(Sport sport : Sports) {
				  toDisplay = toDisplay + sport.toString();
				  toDisplay = toDisplay+"<br />";
			  }
		  }
		  catch (Exception ex) {
			  return "Errors occur while get all Sport.";
	      }
		  return toDisplay;
	  }
	  
  /**
   * GET /create  --> Create a new user and save it in the database.
   */
  @RequestMapping("/sport/create/{name}")
  @ResponseBody
  public String create(@PathVariable String name, boolean inside) {
    String sportId = "";
    try {
      Sport sport = new Sport(name,inside);
      sportRepo.save(sport);
      sportId = String.valueOf(sport.getId());
    }
    catch (Exception ex) {
      return "Error creating the sport: " + ex.toString();
    }
    return "Sport succesfully created with id = " + sportId;
  }
  
  /**
   * GET /delete  --> Delete the user having the passed id.
   */
  @RequestMapping("/sport/delete/{id}")
  @ResponseBody
  public String delete(@PathVariable long id) {
    try {
    	Sport sport = sportRepo.findOne(id);
    	sportRepo.delete(sport);
    }
    catch (Exception ex) {
      return "Error deleting the sport:" + ex.toString();
    }
    return "Sport with id "+id+" successfully deleted!";
  }
  
  /**
   * GET /get-by-email  --> Return the id for the user having the passed
   * email.
   */
  @RequestMapping("/sport/{id}")
  @ResponseBody
  public String get(@PathVariable long id) {
	Sport sport;
    try {
      sport = sportRepo.findOne(id);
    }
    catch (Exception ex) {
      return "Sport with id "+id+" not found";
    }
    return sport.toString(); 
  }
  
  
  @RequestMapping("/sport/getByName/{name}")
  @ResponseBody
  public String get(@PathVariable String name) {
	Sport sport;
    try {
      sport = sportRepo.findByName(name);
    }
    catch (Exception ex) {
      return "Sport with name'"+name+"' not found";
    }
    return sport.toString(); 
  }
  
  
  @RequestMapping("/sport/update/{id}/{name}")
  @ResponseBody
  public String update(@PathVariable long id, @PathVariable String name) {
    try {
      Sport sport = sportRepo.findOne(id);
      sport.setName(name);
      sportRepo.save(sport);
    }
    catch (Exception ex) {
      return "Error updating the sport with id"+id+" : " + ex.toString();
    }
    return "Sport with id "+id+" successfully updated with new name "+name;
  }


  
}