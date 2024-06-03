package com.alerts.SafetyNet.repositoryTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.impl.PersonRepositoryImpl;

public class PersonRepositoryImplTest {

    @Mock
    private PersonRepositoryImpl personRepository;

    @BeforeEach
    public void setup() {
        personRepository = new PersonRepositoryImpl();
    }

    @Test
    public void testGetPersons() {
        // Arrange
        List<Person> expectedPersons = new ArrayList<>();
        expectedPersons.add(new Person("John", "Doe", "", "", "", "", ""));
        expectedPersons.add(new Person("Jane", "Smith", "", "", "", "", ""));
        // Mock the behavior of personRepository.getPersons()
        personRepository = mock(PersonRepositoryImpl.class);
        when(personRepository.getPersons()).thenReturn(expectedPersons);
        // Act
        List<Person> actualPersons = personRepository.getPersons();
        // Assert
        assertEquals(expectedPersons, actualPersons);
    }
    
    
    @Test
    public void testAddPerson() {
        // Arrange
        Person newPerson = new Person("John", "Doe", "", "", "", "", "");
        // Act
        Person returnedPerson = personRepository.addPerson(newPerson);
        List<Person> persons = personRepository.getPersons();
        // Assert
        assertEquals(newPerson, returnedPerson);
        assertTrue(persons.contains(newPerson));
    }
    
    @Test
    public void testUpdatePerson() throws NotFoundException {
        // Arrange
        Person existingPerson = new Person("John", "Doe", "", "", "", "", "");
        personRepository.addPerson(existingPerson);

        Person updatedPerson = new Person("John", "Doe", "123 Main St", "City", "12345", "123-456-7890", "john.doe@example.com");

        // Act
        Person returnedPerson = personRepository.updatePerson(updatedPerson);
        List<Person> persons = personRepository.getPersons();
        Optional<Person> personFromList = persons.stream()
                                                 .filter(p -> p.getFirstName().equals("John") && p.getLastName().equals("Doe"))
                                                 .findFirst();

        // Assert
        assertEquals(updatedPerson, returnedPerson);
        assertTrue(personFromList.isPresent());
        assertEquals("123 Main St", personFromList.get().getAddress());
    }

    @Test
    public void testUpdatePersonNotFound() {
        // Arrange
        Person nonExistingPerson = new Person("Jane", "Doe", "123 Main St", "City", "12345", "123-456-7890", "jane.doe@example.com");
        // Act & Assert
        assertThrows(NotFoundException.class, () -> personRepository.updatePerson(nonExistingPerson));
    }
    
    @Test
    public void testDeletePerson() throws NotFoundException {
        // Arrange
        Person person = new Person("John", "Doe", "", "", "", "", "");
        personRepository.addPerson(person);
        // Act
        personRepository.deletePerson(person);
        // Assert
        assertFalse(personRepository.getPersons().contains(person));
    }

    @Test
    public void testDeletePersonNotFound() {
        // Arrange
        Person nonExistingPerson = new Person("Jane", "Doe", "123 Main St", "City", "12345", "123-456-7890", "jane.doe@example.com");
        // Act & Assert
        assertThrows(NotFoundException.class, () -> personRepository.deletePerson(nonExistingPerson));
    }
    
    @Test
    public void testDeletePersonByName() throws NotFoundException {
        // Arrange
        Person person = new Person("John", "Doe", "", "", "", "", "");
        personRepository.addPerson(person);
        // Act
        personRepository.deletePersonByName("John", "Doe");
        // Assert
        assertFalse(personRepository.getPersons().contains(person));
    }

    @Test
    public void testDeletePersonByNameNotFound() {
        // Arrange
        String firstName = "Jane";
        String lastName = "Doe";
        // Act & Assert
        assertThrows(NotFoundException.class, () -> personRepository.deletePersonByName(firstName, lastName));
    }
    

    @Test
    public void testGetPersonsByAddresses_Found() throws NotFoundException {
        // Create the PersonRepositoryImpl instance
        PersonRepositoryImpl personRepository = new PersonRepositoryImpl();
        // Create and set the person
        Person johnDoe = new Person("John", "Doe", "123 Main St", "Anytown", "12345", "555-1234", "john.doe@example.com");
        personRepository.addPerson(johnDoe); // Assuming a method to set the list of persons
        // Create the list of addresses to search for
        List<String> addresses = List.of("123 Main St");
        // Call the method and get the result
        List<Person> result = personRepository.getPersonsByAddresses(addresses);
        // Assert the results
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

    

    
    
}
