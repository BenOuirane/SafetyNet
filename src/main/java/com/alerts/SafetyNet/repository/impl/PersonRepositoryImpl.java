package com.alerts.SafetyNet.repository.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.PersonRepository;


@Service
public class PersonRepositoryImpl implements PersonRepository{

	private List<Person> listPersons = new ArrayList<>();

	@Override
	public List<Person> getPersons() {
		return listPersons;
	}
	
	
	@Override
    public Person addPerson(Person person)  {
        listPersons.add(person);
		return person;

    }


	@Override
	public Person updatePerson(Person person) throws NotFoundException {
		Optional<Person> personToUpdate = listPersons.stream()
				.filter(p -> p.getFirstName().equals(person.getFirstName()))
				.filter(p -> p.getLastName().equals(person.getLastName())).findFirst();

		if (personToUpdate.isPresent()) {
			listPersons.set(listPersons.indexOf(personToUpdate.get()), person);
			return person;
		} else {
			throw new NotFoundException();
		}
	}



	@Override
	public void deletePerson(Person person) throws NotFoundException {
		if (!listPersons.remove(person)) {
			throw new NotFoundException();
		}		
	}


	@Override
	public void deletePersonByName(String firstName, String lastName) throws NotFoundException {
		
		Optional<Person> personToDelete = listPersons.stream().filter(p -> p.getFirstName().equals(firstName))
				.filter(p -> p.getLastName().equals(lastName)).findFirst();

		if (personToDelete.isPresent()) {
			deletePerson(personToDelete.get());
		} else {
			throw new NotFoundException();
		}
              
		
	}


	@Override
	public List<Person> getPersonsByName(String firstName, String lastName)
			throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
