package com.alerts.SafetyNet.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
/**
 * Contains Person Data, CRUD and advanced filter methods
 * 
 * @author : BEN OUIRANE Hajer
 *
 */
import org.springframework.stereotype.Repository;
import com.alerts.SafetyNet.configuration.FileHandling;
import com.alerts.SafetyNet.entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public interface PersonRepository{
	
    static final Logger logger = LogManager.getLogger(PersonRepository.class);


  
    /**
     * Get all the persons from the JSON source.
     *
     * @return a list of all persons, obtained from JSON source, duplicates are possible
     */
     public   List<Person> getPersons();
    	
    
	


}
