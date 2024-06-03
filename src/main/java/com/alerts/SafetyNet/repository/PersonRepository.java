package com.alerts.SafetyNet.repository;

/**
 * Contains Person Data, CRUD and advanced filter methods
 * 
 * @author : BEN OUIRANE Hajer
 *
 */

import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
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

     Person  addPerson(Person person) ;

     Person  updatePerson(Person person) throws NotFoundException;
     
     void    deletePerson(Person person) throws NotFoundException;
     
     void    deletePersonByName(String firstName, String lastName) throws NotFoundException; 
     
   //  List<Person> getPersonsByName(String firstName, String lastName)  throws NotFoundException;
     
     public List<Person> getPersonsByAddresses(List<String> addresses) throws NotFoundException;
    	    
     
	


}
