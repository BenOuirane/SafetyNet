package com.alerts.SafetyNet.repositoryTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.PersonRepository;
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
    public void testGetPersonsByAddresses() throws NotFoundException {
        // Arrange
        List<String> addresses = Arrays.asList("123 Main St", "789 Maple Ave");
        List<Person> expectedPersons = Arrays.asList(
                new Person("John", "Doe", "123 Main St", "Springfield", "12345", "123-456-7890", "john@example.com"),
                new Person("Alice", "Johnson", "789 Maple Ave", "Springfield", "12345", "456-789-0123", "alice@example.com")
        );

        // Mocking the personRepository
        PersonRepository personRepository = Mockito.mock(PersonRepository.class);
        
        // Stubbing the behavior of personRepository with an actual list of addresses
        when(personRepository.getPersonsByAddresses(anyList())).thenReturn(expectedPersons);

        // Act
        List<Person> actualPersons = personRepository.getPersonsByAddresses(addresses);

        // Assert
        assertEquals(2, actualPersons.size());
        assertEquals(expectedPersons, actualPersons);
    }
    
    @Test
    public void testGetPersonsByAddresses_NotFound() throws NotFoundException {
        // Create mock data
        List<Person> mockPersons = Arrays.asList(
        		 new Person("John", "Doe", "123 Main St", "Springfield", "12345", "123-456-7890", "john@example.com"),
                 new Person("Alice", "Johnson", "789 Maple Ave", "Springfield", "12345", "456-789-0123", "alice@example.com")
        );

        // Mocking the personRepository
        PersonRepository personRepository = Mockito.mock(PersonRepository.class);

        // Define addresses that won't match any person
        List<String> addresses = Arrays.asList("789 Pine St", "101 Oak St");

        // Invoke the method being tested
        List<Person> result = personRepository.getPersonsByAddresses(addresses);

        // Assert that the result is empty
        assertTrue(result.isEmpty());
    }
    
}
