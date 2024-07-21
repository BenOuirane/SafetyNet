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
import com.alerts.SafetyNet.dto.FireDto;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.url.UrlFireService;
import lombok.extern.log4j.Log4j2;

/**
 * URL "{@code /fire?address=<person_address>}" <br>
 * <br>
 * 
 *    Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le
 *    numéro de la caserne de pompiers la desservant. La liste doit inclure le nom, le
 *    numéro de téléphone, l'âge et les antécédents médicaux (médicaments, posologie et
 *    allergies) de chaque personne.
 * 
 * @author BEN OUIRANE Hajeur
 *
 */
@Log4j2
@RestController
public class UrlFireController {
	

	private static final Logger log = LoggerFactory.getLogger(UrlFireController.class);

    @Autowired
    private UrlFireService urlFireService;

    /**
 	 * get method of URL "/fire?address=<person_address>"
 	 * 
 	 * @param  address
 	 * @return ResponseEntity FireDto  with success message and Http Status OK
 	 * @throws NotFoundException
 	 */
    
    @GetMapping("/fire")
    public ResponseEntity<List<FireDto>> getFire(@RequestParam String address)
            throws NotFoundException {
        try {
            log.info("UrlFireController GET Request start. Param Address = " + address);
            List<FireDto> result = urlFireService.getFireService(address);
            log.info("UrlFireController GET Request result : " + result);
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
