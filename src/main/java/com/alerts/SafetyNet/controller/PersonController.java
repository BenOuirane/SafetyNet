package com.alerts.SafetyNet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.repository.PersonRepository;
import com.alerts.SafetyNet.service.PersonService;
import com.alerts.SafetyNet.service.impl.PersonServiceImpl;

import lombok.extern.log4j.Log4j2;



@RestController
@RequestMapping("/person")
@Log4j2
public class PersonController {
	
    private static final Logger logger = LogManager.getLogger(PersonController.class.getName());
    
    @Autowired
    PersonService personService;
   
    
    
    @GetMapping("/getPersons")
    public ResponseEntity<?> getUser() {
    	List<Person> getUser = personService.getPerson();
        return new ResponseEntity<>(getUser, HttpStatus.OK);
        /*
    	try {
            List<Person> getUser = personService.getPerson();
            return new ResponseEntity<>(getUser, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            return new ResponseEntity<>("An error occurred while retrieving user data.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        */
    }
    
    
    

    
    
    
    /*

    @PostMapping("/post")
    public ResponseEntity<Person> createPerson(@RequestBody @Valid Person p){
    	Person creaedPerson = personService.createPerson(p);
        return new ResponseEntity<>(creaedPerson, HttpStatus.OK);
    }
    
    
    @PostMapping("/post")
    public ResponseEntity<?> createPerson(@RequestBody Person p, BindingResult result) {
        if (result.hasErrors()) {
            // If there are validation errors, return a response with error details
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "Validation failed");
            errorResponse.put("errors", result.getAllErrors());
            return ResponseEntity.badRequest().body(errorResponse);
        } else {
            // If no validation errors, proceed to create the person
            Person createdPerson = personService.createPerson(p);
            return ResponseEntity.ok().body(createdPerson);
        }
    }
    

    @PutMapping("/put/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long id,@RequestBody Person p){
    	Person updatedPerson = personService.updatePerson(id, p);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }
    

    @DeleteMapping("/delete/{firstName}/{lastName}")
    public ResponseEntity<String> deletePerson(@PathVariable("firstName")  String firstName, @PathVariable("lastName")  String lastName){
    	 logger.info("Received request to delete person: {} {}", firstName, lastName);
           personService.deletePerson(firstName, lastName);
    }
   
    
    
    */
    

}
