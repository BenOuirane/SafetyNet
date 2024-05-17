package com.alerts.SafetyNet.service;

import java.util.List;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;

/**
 * Service that provides CRUD and advanced filter methods for person
 * 
 *  @author: BEN OUIRANE Hajer
 *
 */



public interface PersonService {
	
	List<Person> getPerson();
    Person    createPersons(Person p);
    Person    updatePerson(Person p) throws NotFoundException;
    void      delete(String firstName, String lastName) throws NotFoundException;
}
