package com.alerts.SafetyNet.service.impl;
/**
 * ServiceImpl that provides CRUD and advanced filter methods for person
 * 
 *  @author: BEN OUIRANE Hajer
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.repository.PersonRepository;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.service.PersonService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonServiceImpl  implements PersonService {
	
	@Autowired
	private PersonRepository personRepository;

	@Override
	public Person createPerson(Person p) {
        return personRepository.save(p);
	}

	@Override
	public Person updatePerson(long id, Person p) {
		Person existingPerson = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person with ID " + id + " Not found"));
		existingPerson.setAddress(p.getAddress());
		existingPerson.setCity(p.getCity());
		existingPerson.setZip(p.getZip());
		existingPerson.setPhone(p.getPhone());
		existingPerson.setEmail(p.getEmail());
	    personRepository.save(existingPerson);
	       return existingPerson;
	}

}
