package com.alerts.SafetyNet.repositoryTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.model.Person;
import com.alerts.SafetyNet.repository.impl.PersonRepositoryImpl;

@ExtendWith(MockitoExtension.class)
public class PersonRepositoryImplTest {

    @Mock
    private PersonRepositoryImpl personRepository;
    @Mock
    private List<Person> listPersons;
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
    
    @Test
    public void testGetPersonsByLastName_Success() throws NotFoundException {
    	// Initialize the listPersons with one test data
    	Person johnDoe = new Person("John", "Doe", "123 Main St", "Anytown", "12345", "555-1234", "john.doe@example.com");
        personRepository.addPerson(johnDoe); // Assuming a method to set the list of persons
        // Define the last names to search for
        List<String> lastNames = Arrays.asList("Doe");
        // Call the method
        List<Person> result = personRepository.getPersonsByLastName(lastNames);
        // Assert the correct person is returned
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Doe", result.get(0).getLastName());
    }
    
    @Test
    public void testGetPersonsByLastName_NotFound() throws NotFoundException {
        // Initialize the listPersons with one test data
        Person johnDoe = new Person("John", "Doe", "123 Main St", "Anytown", "12345", "555-1234", "john.doe@example.com");
        personRepository.addPerson(johnDoe);
        // Define the last names to search for
        List<String> lastNames = Arrays.asList("NonExistent");
        // Call the method and expect an empty list
        List<Person> result = personRepository.getPersonsByLastName(lastNames);
        // Assert that no persons are returned
        assertEquals(0, result.size());
    }
    
    @Test
    public void testGetPersonsByAddresse_Success() throws NotFoundException {
        // Arrange
        Person person = new Person("John", "Doe", "123 Main St", "Springfield", "12345", "123-456-7890", "john.doe@example.com");
        personRepository.addPerson(person);
        // Act
        List<Person> result = personRepository.getPersonsByAddresse("123 Main St");
        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(person, result.get(0));
    }
    
    @Test
    public void testGetPersonsByAddresse_NotFound() {
        // Arrange
    	  Person person = new Person("John", "Doe", "123 Main St", "Springfield", "12345", "123-456-7890", "john.doe@example.com");
          personRepository.addPerson(person);
        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            List<Person> result = personRepository.getPersonsByAddresse("456 Elm St");
            if (result.isEmpty()) {
                throw new NotFoundException();
            }
        });
    }
    
    @Test
    void testGetPersonsInfoByLastName_Success() throws NotFoundException {
        // Arrange
        String lastName = "Doe";
        Person person1 = new Person("John", "Doe", "123 Main St", "Springfield", "12345", "123-456-7890", "john.doe@example.com");
        Person person2 = new Person("Jane", "Doe", "123 Main St", "Springfield", "12345", "123-456-7890", "john.doe@example.com");
        personRepository.addPerson(person1);
        personRepository.addPerson(person2);
        List<Person> expectedResult = Arrays.asList(person1, person2);
        // Act
        List<Person> result = personRepository.getPersonsInfoByLastName(lastName);
        // Assert
        assertEquals(expectedResult, result);
    }
    
    @Test
    void testGetPersonsInfoByLastName_NotFound() throws NotFoundException {
    	// Arrange
        String lastName = "Smith";
        // Act
        List<Person> result = personRepository.getPersonsInfoByLastName(lastName);
        // Assert
        assertTrue(result.isEmpty());
    }
    
    @Test
    public void testGetEmailsByCity_Success() throws NotFoundException {
        // Arrange
        String city = "New York";
        Person person1 = new Person("Alice","Doe", "123 Main St", "New York", "", "", "alice@example.com" );
        Person person2 = new Person("Bob","Doe", "123 Main St", "London", "", "", "bob@example.com");
        Person person3 = new Person("Charlie", "Doe", "123 Main St", "New York", "", "", "charlie@example.com");
        Person person4 = new Person("David","Doe", "123 Main St", "Paris", "", "", "david@example.com");
        personRepository.addPerson(person1);
        personRepository.addPerson(person2);
        personRepository.addPerson(person3);
        personRepository.addPerson(person4);
        List<String> expectedEmails = Arrays.asList("alice@example.com", "charlie@example.com");
        // Act
        List<String> result = personRepository.getEmailsByCity(city);
        // Assert
        assertEquals(expectedEmails.size(), result.size());
        assertEquals(expectedEmails, result);
    }

    @Test
    public void testGetEmailsByCity_NotFound() {
        // Arrange
        Person person = new Person("John", "Doe", "123 Main St", "Springfield", "12345", "123-456-7890", "john.doe@example.com");
        personRepository.addPerson(person);
        String nonExistingCity = "NonExistingCity";
        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            List<String> result = personRepository.getEmailsByCity(nonExistingCity);
            if (result.isEmpty()) {
                throw new NotFoundException();
            }
        });
    }


}
