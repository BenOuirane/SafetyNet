package com.alerts.SafetyNet.controller.url;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alerts.SafetyNet.dto.UrlPersonnesCouvertesCaserneDTO;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.url.UrlPersonnesCouvertesCaserneService;
import lombok.extern.log4j.Log4j2;

/**
 * URL "{@code /firestation?stationNumber=<station_number>}" <br>
 * <br>
 * 
 *  Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers
 *  correspondante. Donc, si le numéro de station = 1, elle doit renvoyer les habitants
 *  couverts par la station numéro 1. La liste doit inclure les informations spécifiques
 *  suivantes : prénom, nom, adresse, numéro de téléphone. De plus, elle doit fournir un
 *  décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou
 *  moins) dans la zone desservie.
 * 
 * @author BEN OUIRANE Hajeur
 *
 */
@RestController
@Log4j2
public class UrlPersonnesCouvertesCaserneController {
	
    private static final Logger log = LogManager.getLogger(UrlPersonnesCouvertesCaserneController.class.getName());

    @Autowired
	UrlPersonnesCouvertesCaserneService  urlPersonnesCouvertesCaserneService;
   
    /**
	 * get method of URL "/firestation?stationNumber=<station_number>"
	 * 
	 * @param numberOfStation
	 * @return ResponseEntity with success message and Http Status OK
	 * @throws NotFoundException
	 */
	
	@GetMapping("/firestation")
	public ResponseEntity<UrlPersonnesCouvertesCaserneDTO> PersonnesCouvertesCaserne(@RequestParam int numberOfStation)
			throws NotFoundException {
		try {
		log.info("UrlPersonnesCouvertesCaserneController GET Request start. Param Station number = " + numberOfStation);
		ResponseEntity<UrlPersonnesCouvertesCaserneDTO> result = new ResponseEntity<>
		( urlPersonnesCouvertesCaserneService.urlPersonnesCouvertesCaserneService(numberOfStation),HttpStatus.OK);

		log.info("UrlPersonnesCouvertesCaserneController GET Request result : " + result);
		    return result;
	  } catch (NotFoundException e) {
		  log.error("Person not found: " + e.getMessage());
	      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
	}
	
}
