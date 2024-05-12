package com.alerts.SafetyNet.serviceTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.repository.PersonRepository;
import com.alerts.SafetyNet.service.impl.PersonServiceImpl;


@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class PersonServiceTest {

    @InjectMocks
    private PersonServiceImpl personService ;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private Person person;

    @SuppressWarnings("deprecation")
	@Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    /*
     
    @Test
    public void testcreatePerson_shouldCreatePerson() {
      // Create a mock Person object with ID 1
      Person  personObject = new Person(1L,"BEN","Hajer","Djerba","Djerba-Tunisie","4145","27030668","benouirane@gmail.com");
      // Mock the save method to return the mock Person object
      when(personRepository.save(ArgumentMatchers.any(Person.class))).thenReturn(personObject);
      // Call the createPerson method with the mocked data
      Person created = personService.createPerson(personObject);
      // Assert that the first name of the created person matches the first name of the mock Person object
      assertThat(created.getFirstName()).isSameAs(personObject.getFirstName());
      // Verify that the save method of the personRepository was called with the mock Person object
      verify(personRepository).save(personObject);
    }
    
   
    @Test
    public void testcreatePerson_cantCreatePerson() {
    	 // Create a mock Person object with ID 1
           Person personObject = new Person(1L, "BEN", "Hajer", "Djerba", "Djerba-Tunisie", "4145", "27030668", "invalid_email");
        // Mock the save method to return null, indicating failure to save
           when(personRepository.save(ArgumentMatchers.any(Person.class))).thenReturn(null);
        // Assert that calling createPerson with the mocked data throws an exception
           assertThrows(IllegalArgumentException.class, () -> personService.createPerson(personObject));
    }
   
    
    
    @Test
    public void testupdatePerson_shouldUpdatePerson() {
      // Create a mock Person object with ID 1
      Person  personObject = new Person(1L,"BEN","Hajer","Djerba","Djerba-Tunisie","4145","27030668","benouirane@gmail.com");
      personObject.setAddress("New-test-address");
      // Mock the findById method to return the mock Person object
      when(personRepository.findById(1L)).thenReturn(Optional.of(personObject));
      // Mock the save method to return the updated mock Person object
      when(personRepository.save(ArgumentMatchers.any(Person.class))).thenReturn(personObject);
      // Call the updatePerson method with the mocked data
      Person updated = personService.updatePerson(personObject.getId(),personObject);
      // Assert that the address has been updated correctly
      assertThat(updated.getAddress()).isSameAs("New-test-address");
      verify(personRepository).save(personObject);
    }
    
    
    @Test
    public void testupdatePerson_userDoesntExist() {
      // Create a mock Person object with ID 1
      Person  personObject = new Person(1L,"BEN","Hajer","Djerba","Djerba-Tunisie","4145","27030668","benouirane@gmail.com");
      personObject.setAddress("New-test-address");
      // Mock the findById method to return the mock Person object
      when(personRepository.findById(2L)).thenReturn(Optional.of(personObject));
      // Mock the save method to return the updated mock Person object
      when(personRepository.save(ArgumentMatchers.any(Person.class))).thenReturn(personObject);
      // Verifies that calling updatePerson() with specific arguments throws EntityNotFoundException
      assertThrows(EntityNotFoundException.class, () -> {personService.updatePerson(personObject.getId(),personObject); });
    }
    */
    

	
	

}
