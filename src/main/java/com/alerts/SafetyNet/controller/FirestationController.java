package com.alerts.SafetyNet.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alerts.SafetyNet.entity.Firestation;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.service.FirestationService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Log4j2
@RestController
@RequestMapping("/firestation")
public class FirestationController {
	
	private static final Logger log = LogManager.getLogger(FirestationController.class.getName());
    
    @Autowired
    FirestationService firestationService;
    
    @GetMapping("/getPersons")
    public ResponseEntity<?> getFirestations() {
    	log.info("Firestation Controller GET Request start. ");
    	List<Firestation> getFirestation = firestationService.getFirestation();
		log.info("Firestation Controller GET Request result : " + getFirestation);
        return new ResponseEntity<>(getFirestation, HttpStatus.OK);
    }
    
	/**
	 * POST method of URL "/firestation/post"
	 * 
	 * @param person
	 * @return ResponseEntity with Person creaedPerson and Http Status OK
	 */


    @PostMapping("/post")
    public ResponseEntity<?> createFirestation(@RequestBody  Firestation f){
	    	log.info("Firestation Controller POST Request start. Param firestation = " + f);
	    	Firestation creaedFirestation = firestationService.createFirestations(f);
    		log.info("Firestation Controller POST Request result : " + creaedFirestation);
            return new ResponseEntity<>(creaedFirestation, HttpStatus.OK);
    }
    
    
    


}
