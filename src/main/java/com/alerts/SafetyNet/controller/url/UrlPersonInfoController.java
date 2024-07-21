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
import com.alerts.SafetyNet.dto.PersonInfoDto;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.url.UrlPersonInfoService;

import lombok.extern.log4j.Log4j2;

/**
 * URL "{@code /personInfo?lastName=<lastName>}" <br>
 * <br>
 * 
 *   Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents
 *   médicaux (médicaments, posologie et allergies) de chaque habitant. Si plusieurs
 *   personnes portent le même nom, elles doivent toutes apparaître.
 * 
 * @author BEN OUIRANE Hajeur
 *
 */
@Log4j2
@RestController
public class UrlPersonInfoController {
	
	private static final Logger log = LoggerFactory.getLogger(UrlPersonInfoController.class);

    @Autowired
    private UrlPersonInfoService urlPersonInfoService;
    
    /**
 	 * get method of URL "/personInfo?lastName=<lastName>"
 	 * 
 	 * @param  lastName
 	 * @return ResponseEntity PersonInfoDto  with success message and Http Status OK
 	 * @throws NotFoundException
 	 */
    
    @GetMapping("/personInfo")
    public ResponseEntity<List<PersonInfoDto>> getPersonInfo(@RequestParam String lastName)
            throws NotFoundException {
        try {
            log.info("UrlPersonInfoController GET Request start. Param Address = " + lastName);
            List<PersonInfoDto> result = urlPersonInfoService.getPersonInfoService(lastName);
            log.info("UrlPersonInfoController GET Request result : " + result);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("lastName not found: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception ex) {
            log.error("An unexpected error occurred: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
