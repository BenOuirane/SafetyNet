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

import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.model.MedicalRecord;
import com.alerts.SafetyNet.service.MedicalrecordService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/medicalrecord")
@Log4j2
public class MedicalrecordController {

    private static final Logger log = LogManager.getLogger(MedicalrecordController.class.getName());
    @Autowired
    MedicalrecordService medicalrecordService;
   
    @GetMapping("/getMedicalrecords")
    public ResponseEntity<?> getMedicalrecords() {
    	log.info("Medicalrecord Controller GET Request start. ");
    	List<MedicalRecord> getMedicalrecords = medicalrecordService.getMedicalRecord();
		log.info("Medicalrecord Controller GET Request result : " + getMedicalrecords);
        return new ResponseEntity<>(getMedicalrecords, HttpStatus.OK);
    }
    
    /**
	 * POST method of URL "/medicalrecord/post"
	 * 
	 * @param MedicalRecord
	 * @return ResponseEntity with MedicalRecord creaedMedicalRecord and Http Status OK
	 */

    @PostMapping("/post")
    public ResponseEntity<?> createMedicalrecords(@RequestBody  MedicalRecord m){
	    	log.info("MedicalRecord Controller POST Request start. Param MedicalRecord = " + m);
	    	try {
	    	MedicalRecord creaedMedicalRecord = 	medicalrecordService.createMedicalRecord(m);
    		log.info("MedicalRecord Controller POST Request result : " + creaedMedicalRecord);
            return new ResponseEntity<>(creaedMedicalRecord, HttpStatus.OK);
	    	} catch (Exception e) {
	            log.error("Error occurred while creating MedicalRecord: " + e.getMessage(), e);
	            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
    }
    
    /**
   	 * UPDATE method of URL "/medicalrecord/put"
   	 * 
   	 * @param MedicalRecord
   	 * @return ResponseEntity with updated MedicalRecord and Http Status OK
   	 * @throws NotFoundException
   	 */
       
   	@PutMapping("/put")
   	public ResponseEntity<MedicalRecord> updateMedicalrecord(@RequestBody MedicalRecord medicalRecord) throws NotFoundException {
   		log.info("MedicalRecord Controller PUT Request start. Param Firestation = " + medicalRecord);
   	 try {
   		ResponseEntity<MedicalRecord> updatedMedicalRecord = new ResponseEntity<>(medicalrecordService.updateMedicalRecord(medicalRecord), HttpStatus.OK);
   		log.info("MedicalRecord Controller PUT Request result : " + updatedMedicalRecord);
   		return updatedMedicalRecord;
   	} catch (NotFoundException e) {
        log.error("MedicalRecord not found: " + e.getMessage(), e);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    } catch (Exception e) {
        log.error("Error occurred while updating MedicalRecord: " + e.getMessage(), e);
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
   	}
   	
   	/**
	 * DELETE method of URL "/medicalrecord/delete"
	 * 
	 * @param firstName
	 * @param lastName
	 * @return ResponseEntity with success message and Http Status OK
	 * @throws NotFoundException
	 */
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deletePerson(@RequestParam String firstName, @RequestParam String lastName)
			throws NotFoundException {
		log.info("MedicalRecord Controller DELETE Request start. Param firstName = " + firstName + " / lastName = " + lastName);
		try {

		medicalrecordService.deleteByName(firstName, lastName);
		ResponseEntity<String> deletedMedicalRecord  = ResponseEntity.status(HttpStatus.OK)
				.body("The MedicalRecord has been succesfully deleted");
		log.info("MedicalRecord Controller DELETE Request result : " + deletedMedicalRecord);
		return deletedMedicalRecord;
		} catch (NotFoundException e) {
	        log.error("MedicalRecord not found: " + e.getMessage(), e);
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("MedicalRecord not found");
	    } catch (Exception e) {
	        log.error("Error occurred while deleting MedicalRecord: " + e.getMessage(), e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the MedicalRecord");
	    }
	} 

}
