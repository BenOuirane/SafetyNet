package com.alerts.SafetyNet.controller;

import java.util.List;
import java.util.Optional;

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
import com.alerts.SafetyNet.entity.Firestation;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.FirestationService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Endpoint "/firestation".<br>
 * 
 * "Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete
 * avec HTTP :<br>
 * ● ajouter une nouvelle firestation ; <br>
 * ● mettre à jour une firestation existante (pour le moment, 
 *  mettre à jour le numéro de la caserne de pompiers d'une adresse);<br>
 * ● supprimer une firestation (supprimer le mapping d'une caserne ou d'une adresse.)"
 * 
 * @author BEN OUIRANE Hajeur
 *
 */

@Log4j2
@RestController
@RequestMapping("/firestation")
public class FirestationController {
	
	private static final Logger log = LogManager.getLogger(FirestationController.class.getName());
    
    @Autowired
    FirestationService firestationService;
    
    @GetMapping("/getFirestations")
    public ResponseEntity<?> getFirestations() {
    	log.info("Firestation Controller GET Request start. ");
    	List<Firestation> getFirestation = firestationService.getFirestation();
		log.info("Firestation Controller GET Request result : " + getFirestation);
        return new ResponseEntity<>(getFirestation, HttpStatus.OK);
    }
    
	/**
	 * POST method of URL "/firestation/post"
	 * 
	 * @param Firestation
	 * @return ResponseEntity with Firestation createFirestation and Http Status OK
	 */


    @PostMapping("/post")
    public ResponseEntity<?> createFirestation(@RequestBody  Firestation f){
	    	log.info("Firestation Controller POST Request start. Param firestation = " + f);
	    	Firestation creaedFirestation = firestationService.createFirestations(f);
    		log.info("Firestation Controller POST Request result : " + creaedFirestation);
            return new ResponseEntity<>(creaedFirestation, HttpStatus.OK);
    }
    
    /**
   	 * UPDATE method of URL "/firestation/put"
   	 * 
   	 * @param Firestation
   	 * @return ResponseEntity with updated Firestation and Http Status OK
   	 * @throws NotFoundException
   	 */
       
   	@PutMapping("/put")
   	public ResponseEntity<Firestation> updatePerson(@RequestBody Firestation firestation) throws NotFoundException {
   		log.info("Firestation Controller PUT Request start. Param Firestation = " + firestation);
   		ResponseEntity<Firestation> updatedFirestation = new ResponseEntity<>(firestationService.updateFirestation(firestation), HttpStatus.OK);
   		log.info("Firestation Controller PUT Request result : " + updatedFirestation);
   		return updatedFirestation;
   	}
   	
   	/**
	 * DELETE method of URL "/firestation/delete"
	 * 
	 * @param address
	 * @param stationNumber
	 * @return ResponseEntity with a success message and Http Status OK
	 * @throws NotFoundException
	 */
   	
   	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteFirestations(@RequestParam Optional<String> address,
			@RequestParam Optional<Integer> stationNumber) throws NotFoundException {
		log.info("Firestation Controller DELETE Request start. Param address = " + address + " / stationNumber = " + stationNumber);
        String message = "";
        if(address.isPresent()) {
        	firestationService.deleteFirestationsByAddress(address.get());
        	message = "Firestation(s) successfully deleted";
        } else if (stationNumber.isPresent()) {
        	firestationService.deleteFirestationsByStationNumber(stationNumber.get());
        	message = "Firestation(s) successfully deleted";
        } else {
        	throw new IllegalArgumentException();
        }
		ResponseEntity<String> result = ResponseEntity.status(HttpStatus.OK).body(message);
		log.info("Firestation Controller DELETE Request result : " + result);
		return result;  
   	}


}
