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
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.url.UrlCommunityEmailService;
import lombok.extern.log4j.Log4j2;

/**
 * URL "{@code /communityEmail?city=<city>}" <br>
 * <br>
 * 
 *    Cette url doit retourner les adresses mail de tous les habitants de la ville.
 * 
 * @author BEN OUIRANE Hajeur
 *
 */
@Log4j2
@RestController
public class UrlCommunityEmailController {
	
	private static final Logger log = LoggerFactory.getLogger(UrlFireController.class);

    @Autowired
    private UrlCommunityEmailService urlCommunityEmailService;

    /**
 	 * get method of URL "/communityEmail?city=<city>"
 	 * 
 	 * @param  city
 	 * @return ResponseEntity List<String>  with success message and Http Status OK
 	 * @throws NotFoundException
 	 */
    
    @GetMapping("/communityEmail")
    public ResponseEntity<List<String>> getCommunityEmail(@RequestParam String city)
            throws NotFoundException {
        try {
            log.info("UrlCommunityEmailController GET Request start. Param Address = " + city);
            List<String> result = urlCommunityEmailService.getCommunityEmailService(city);
            log.info("UrlCommunityEmailController GET Request result : " + result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("City not found: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception ex) {
            log.error("An unexpected error occurred: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
