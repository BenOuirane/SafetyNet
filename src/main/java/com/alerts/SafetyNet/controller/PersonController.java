package com.alerts.SafetyNet.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alerts.SafetyNet.SafetyNetApplication;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.service.PersonService;


@RestController
@RequestMapping("/person")
public class PersonController {
	
    private static final Logger logger = LogManager.getLogger(PersonController.class.getName());
    
    
    
    
    @Autowired
    PersonService personService;

    @PostMapping("/post")
    public Person createPerson(@RequestBody Person p){
        return  personService.createPerson(p);
    }
    
    @PutMapping("/put/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") long id,@RequestBody Person p){
    	Person updatedPerson = personService.updatePerson(id, p);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }
    
    


}
