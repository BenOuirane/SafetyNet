package com.alerts.SafetyNet.controller.url;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alerts.SafetyNet.dto.FloodStationsDto;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.url.UrlFloodStationsService;
import lombok.extern.log4j.Log4j2;

/**
 * URL "{@code /flood/stations?stations=<a list of station_numbers>}" <br>
 * <br>
 * 
 *    Cette url doit retourner une liste de tous les foyers desservis par la caserne.
 *    Cette liste doit regrouper les personnes par adresse.
 *    Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, 
 *     et faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
 * 
 * @author BEN OUIRANE Hajeur
 *
 */
@Log4j2
@RestController
public class UrlFloodStationsController {
	
	private static final Logger log = LoggerFactory.getLogger(UrlFloodStationsController.class);

    @Autowired
    private UrlFloodStationsService urlFloodStationsService;
    
    /**
 	 * get method of URL "/flood/stations?stations=<a list of station_numbers>"
 	 * 
 	 * @param  stationNumbers
 	 * @return ResponseEntity FloodStationsDto  with success message and Http Status OK
 	 * @throws NotFoundException
 	 */
    
    @GetMapping("/flood/stations")
    public ResponseEntity<List<FloodStationsDto>> getFloodStations(@RequestParam List<Integer> stations)
            throws NotFoundException {
        try {
            log.info("UrlFloodStationsController GET Request start. Param Address = " + stations);
            List<FloodStationsDto> result = urlFloodStationsService.getFloodStationsService(stations);
            log.info("UrlFloodStationsController GET Request result : " + result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Address not found: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception ex) {
            log.error("An unexpected error occurred: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    
}
