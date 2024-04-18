package com.alerts.SafetyNet.service;

import com.alerts.SafetyNet.entity.Person;

/**
 * Service that provides CRUD and advanced filter methods for person
 * 
 *  @author: BEN OUIRANE Hajer
 *
 */


public interface PersonService {
	
    Person  createPerson(Person p);
    Person  updatePerson(long personId, Person p);



}
