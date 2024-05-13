package com.alerts.SafetyNet.service;

import java.util.List;

import com.alerts.SafetyNet.dto.PersonDto;
import com.alerts.SafetyNet.entity.Person;

/**
 * Service that provides CRUD and advanced filter methods for person
 * 
 *  @author: BEN OUIRANE Hajer
 *
 */



public interface PersonService {
	
	
	List<Person> getPerson();
    PersonDto  createPerson(PersonDto p);
    PersonDto     createPersons(PersonDto p);

    
    
	/*
    Person  updatePerson(long personId, Person p);
    void    deletePerson(Person p);
    */

}
