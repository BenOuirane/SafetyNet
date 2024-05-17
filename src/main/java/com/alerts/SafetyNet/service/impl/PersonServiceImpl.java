package com.alerts.SafetyNet.service.impl;
/**
 * ServiceImpl that provides CRUD and advanced filter methods for person
 * 
 *  @author: BEN OUIRANE Hajer
 *
 */


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.repository.impl.PersonRepositoryImpl;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.PersonService;
/**
 * Service that provides CRUD and advanced filter methods for Person
 * 
 * @author BEN OUIRANE Hajeur
 *
 */

@Service
public class PersonServiceImpl implements PersonService  {

    @Autowired
	PersonRepositoryImpl personRepositoryimpl;

	@Override
	public List<Person> getPerson() {
		List<Person> persons = personRepositoryimpl.getPersons();
		return persons ;
	}

	@Override
	public Person createPersons(Person person)  {
				return  personRepositoryimpl.addPerson(person);
	}

	@Override
	public Person updatePerson(Person p) throws NotFoundException {
		return personRepositoryimpl.updatePerson(p);
	}

	@Override
	public void delete(String firstName, String lastName)
			throws NotFoundException {
		personRepositoryimpl.deletePersonByName(firstName, lastName);
	}

}
