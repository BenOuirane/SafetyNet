package com.alerts.SafetyNet.repository.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
	public List<Person> getPersonsByAddresses(List<String> addresses) throws NotFoundException {
		return listPersons.stream().filter(p -> addresses.stream().anyMatch(a -> a.equals(p.getAddress())))
				.collect(Collectors.toList());
	}

	@Override
	public List<Person> getPersonsByLastName(List<String> lastname)
			throws NotFoundException {
		return listPersons.stream().filter(p -> lastname.stream().anyMatch(a -> a.equals(p.getLastName())))
				.collect(Collectors.toList());
	}

	@Override
	public List<Person> getPersonsByAddresse(String addresse)
			throws NotFoundException {
		return listPersons.stream().filter(p -> p.getAddress().equals(addresse))
				.collect(Collectors.toList());
	}
	
	
	

}
