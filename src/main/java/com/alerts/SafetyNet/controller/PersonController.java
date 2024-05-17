package com.alerts.SafetyNet.controller;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.PersonService;
import lombok.extern.log4j.Log4j2;

/**
 * Endpoint "/person".<br>
 * 
 * "Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete
 * avec HTTP :<br>
 * ● ajouter une nouvelle personne ; <br>
 * ● mettre à jour une personne existante (pour le moment, supposons que le
 * prénom et le nom de famille ne changent pas, mais que les autres champs
 * peuvent être modifiés) ;<br>
 * ● supprimer une personne (utilisez une combinaison de prénom et de nom comme
 * identificateur unique)"
 * 
 * @author BEN OUIRANE Hajeur
 *
 */



@RestController
@RequestMapping("/person")
@Log4j2
public class PersonController {
	
    private static final Logger log = LogManager.getLogger(PersonController.class.getName());
    
    @Autowired
    PersonService personService;
   
    
    
    @GetMapping("/getPersons")
    public ResponseEntity<?> getUser() {
    	List<Person> getUser = personService.getPerson();
        return new ResponseEntity<>(getUser, HttpStatus.OK);
    }

	/**
	 * POST method of URL "/person/post"
	 * 
	 * @param person
	 * @return ResponseEntity with Person creaedPerson and Http Status OK
	 */


    @PostMapping("/post")
    public ResponseEntity<?> createPerson(@RequestBody  Person p){
	    	log.info("Person Controller POST Request start. Param person = " + p);
    		Person creaedPerson = 	personService.createPersons(p);
    		log.info("Person Controller POST Request result : " + creaedPerson);
            return new ResponseEntity<>(creaedPerson, HttpStatus.OK);
    }
    
    
    /**
	 * UPDATE method of URL "/person/put"
	 * 
	 * @param person
	 * @return ResponseEntity with updated Person and Http Status OK
	 * @throws NotFoundException
	 */
    
	@PutMapping("/put")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) throws NotFoundException {
		log.info("Person Controller PUT Request start. Param person = " + person);
		ResponseEntity<Person> result = new ResponseEntity<>(personService.updatePerson(person), HttpStatus.OK);
		log.info("Person Controller PUT Request result : " + result);
		return result;
	}
	
	/**
	 * DELETE method of URL "/person/delete"
	 * 
	 * @param firstName
	 * @param lastName
	 * @return ResponseEntity with success message and Http Status OK
	 * @throws NotFoundException
	 */
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deletePerson(@RequestParam String firstName, @RequestParam String lastName)
			throws NotFoundException {
		log.info("Person Controller DELETE Request start. Param firstName = " + firstName + " / lastName = " + lastName);
		personService.delete(firstName, lastName);
		ResponseEntity<String> result = ResponseEntity.status(HttpStatus.OK)
				.body("The Person has been succesfully deleted");
		log.info("Person Controller DELETE Request result : " + result);
		return result;
	}
	

}
