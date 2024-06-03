package com.alerts.SafetyNet.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.impl.PersonRepositoryImpl;
import com.alerts.SafetyNet.service.impl.PersonServiceImpl;

@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest
public class PersonServiceImplTest {

    @InjectMocks
    private PersonServiceImpl personService;
    @Mock
    private PersonRepositoryImpl personRepositoryimpl;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPerson() {
        // Arrange
        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Doe");
        Person person2 = new Person();
        person2.setFirstName("Jane");
        person2.setLastName("Doe");
        List<Person> persons = Arrays.asList(person1, person2);
        when(personRepositoryimpl.getPersons()).thenReturn(persons);
        // Act
        List<Person> result = personService.getPerson();
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Jane", result.get(1).getFirstName());
    }
    
    @Test
    public void testCreatePersons() {
        // Arrange
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        when(personRepositoryimpl.addPerson(any(Person.class))).thenReturn(person);
        // Act
        Person result = personService.createPersons(person);
        // Assert
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }
    
    @Test
    public void testUpdatePerson() throws NotFoundException {
        // Arrange
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Doe");
        when(personRepositoryimpl.updatePerson(any(Person.class))).thenReturn(person);
        // Act
        Person result = personService.updatePerson(person);
        // Assert
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }
    
    @Test
    public void testDelete() throws NotFoundException {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        // Act
        personService.delete(firstName, lastName);
        // Assert
        verify(personRepositoryimpl, times(1)).deletePersonByName(firstName, lastName);
    }
    
}
