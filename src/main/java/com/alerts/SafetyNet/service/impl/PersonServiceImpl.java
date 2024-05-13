package com.alerts.SafetyNet.service.impl;
/**
 * ServiceImpl that provides CRUD and advanced filter methods for person
 * 
 *  @author: BEN OUIRANE Hajer
 *
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.repository.PersonRepository;
import com.alerts.SafetyNet.repository.impl.PersonRepositoryImpl;
import com.alerts.SafetyNet.dto.PersonDto;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class PersonServiceImpl implements PersonService  {
	
    private static  Logger logger = LoggerFactory.getLogger(PersonService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

  
    @Autowired
	PersonRepositoryImpl personRepositoryimpl;

	@Override
	public List<Person> getPerson() {
		List<Person> persons = personRepositoryimpl.getPersons();
		return persons ;
	
	}

	@Override
	public PersonDto createPerson(PersonDto p) {
			try {
				 personRepositoryimpl.addPerson(p);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return p;
	}

    private List<PersonDto> persons = new ArrayList<>();

    
	@Override
	public PersonDto createPersons(PersonDto person) {
		logger.debug("Adding person: {} {}.", person.getFirstName(), person.getFirstName());
        persons.add(person);
        logger.info("Person added successfully: {} {}.", person.getFirstName(), person.getFirstName());

        return person;
	}



    
    
 

    

    /*

	@Override
	public Person createPerson(Person p) {
		try {
	        return personRepository.save(p);
	    } catch (DataAccessException ex) {
	        // Log the exception or perform any necessary error handling
	        throw new RuntimeException("Failed to create person", ex);	
				
	    }
	}
	
	@Override
	public Person updatePerson(long id, Person p) throws EntityNotFoundException{
		try {
	        Person existingPerson = personRepository.findById(id)
	        	     .orElseThrow(() ->
	                 new EntityNotFoundException("Person with ID " + id + EntityNotFoundEnumeration.NOT_FOUND));
	        existingPerson.setAddress(p.getAddress());
	        existingPerson.setCity(p.getCity());
	        existingPerson.setZip(p.getZip());
	        existingPerson.setPhone(p.getPhone());
	        existingPerson.setEmail(p.getEmail());
	        return personRepository.save(existingPerson);
	    } catch (Exception ex) {
	        // Log the exception or perform any necessary error handling
	        throw new RuntimeException("Failed to update person", ex);
	    }
	}

	@Override
	public void deletePerson(Person p) throws EntityNotFoundException {
    
		personRepository.delete(p);
    }
    
    */

}
