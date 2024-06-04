package com.alerts.SafetyNet.controller.url;

import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alerts.SafetyNet.dto.PhoneAlertDto;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.url.UrlPhoneAlertService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
public class UrlPhoneAlertController {
	
	private static final Logger log = LoggerFactory.getLogger(UrlChildAlertController.class);

    @Autowired
    private UrlPhoneAlertService urlPhoneAlertService;

    /**
 	 * get method of URL "/phoneAlert?firestation=<firestation_number>"
 	 * 
 	 * @param  firestation
 	 * @return ResponseEntity PhoneAlertDto  with success message and Http Status OK
 	 * @throws NotFoundException
 	 */
    
    @GetMapping("/phoneAlert")
    public ResponseEntity<List<PhoneAlertDto>> getPhoneByFirestation(@RequestParam int firestation) {
        try {
            log.info("PhoneAlertController GET Request start. Param firestation = " + firestation);
            List<PhoneAlertDto> result = urlPhoneAlertService.getPhoneByFirestation(firestation);
            log.info("PhoneAlertController GET Request result : " + result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Firestation not found: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        } catch (Exception e) {
            log.error("Internal server error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }


}
