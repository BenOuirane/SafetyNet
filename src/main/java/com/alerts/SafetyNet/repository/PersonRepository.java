package com.alerts.SafetyNet.repository;

/**
 * Contains Person Data, CRUD and advanced filter methods
 * 
 * @author : BEN OUIRANE Hajer
 *
 */

import com.alerts.SafetyNet.dto.PersonDto;
import com.alerts.SafetyNet.entity.Person;
import java.io.IOException;
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
     
  // Person getPersonById(long id) throws IOException;

     PersonDto  addPerson(PersonDto person) throws IOException;

  // void updatePerson(Person person) throws IOException;

  // void deletePerson(long id) throws IOException;
    	
    
	


}
