package com.alerts.SafetyNet.controller.url;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alerts.SafetyNet.dto.ChildAlertDto;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.url.UrlChildAlertService;

import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * URL "{@code /childAlert?address=<child_address>}" <br>
 * <br>
 * 
 *   Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins)
 *   habitant à cette adresse. La liste doit comprendre le prénom et le nom de famille de
 *   chaque enfant, son âge et une liste des autres membres du foyer. S'il n'y a pas
 *   d'enfant, cette url peut renvoyer une chaîne vide.
 * 
 * @author BEN OUIRANE Hajeur
 *
 */
@Log4j2
@RestController
public class UrlChildAlertController {

	private static final Logger log = LoggerFactory.getLogger(UrlChildAlertController.class);

    @Autowired
    private UrlChildAlertService urlChildAlertService;

    /**
 	 * get method of URL "/childAlert?address=<child_address>"
 	 * 
 	 * @param  address
 	 * @return ResponseEntity ChildAlertDto  with success message and Http Status OK
 	 * @throws NotFoundException
 	 */
    
    @GetMapping("/childAlert")
    public ResponseEntity<List<ChildAlertDto>> PersonnesCouvertesCaserne(@RequestParam String address)
            throws NotFoundException {
        try {
            log.info("UrlChildAlertController GET Request start. Param Address = " + address);
            List<ChildAlertDto> result = urlChildAlertService.getChildrenIntoAddress(address);
            log.info("UrlChildAlertController GET Request result : " + result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("Address not found: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}
