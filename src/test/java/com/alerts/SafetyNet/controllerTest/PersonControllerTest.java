package com.alerts.SafetyNet.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.alerts.SafetyNet.controller.PersonController;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class PersonControllerTest {

	    @Mock
	    private PersonService  personService;

	    @InjectMocks
	    private PersonController personController;

	    @Autowired
		private MockMvc mockMvc;
	    
	    private static final ObjectMapper objectMapper = new ObjectMapper();

	    public static String asJsonString(final Object obj) {
	        try {
	            return objectMapper.writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	    
	    
	    @Before
	    public  void setUp() throws Exception {
	        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

	    }
	   
	    private String mapToJson(Object object) throws JsonProcessingException {
	         ObjectMapper objectMapper = new ObjectMapper();
	         return  objectMapper.writeValueAsString(object);
	     }
	    
	 
	    @Test
	    public void  testcreatePerson_shouldGive200Ok() throws Exception {
	      Person  personObject = new Person("BEN","Hajer","Anytown","Anytown-Tunisie","4145","1111111","Hajer.create@gmail.com");
	      String inputInJson = this.mapToJson(personObject);
	      String uri = "/person/post";
	      Mockito.when((personService.createPersons(Mockito.any(Person.class)))).thenReturn(personObject);
	      RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).content(inputInJson)
	                .contentType(MediaType.APPLICATION_JSON);
	      MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	      MockHttpServletResponse response = result.getResponse();
		  String outputInJson = response.getContentAsString();
		   assertEquals(outputInJson, (inputInJson));
	       assertEquals(HttpStatus.OK.value(), response.getStatus());
	          }
	    
	    @Test
	    void testCreatePerson_ShouldReturn400BadRequest() throws Exception {
	        // Create a person with invalid data (for example, empty firstName)
	        Person personObject = new Person("", "Hajer", "Anytown", "Anytown-Tunisie", "4145", "11111111", "Hajer.create@gmail.com");
	        String inputInJson = asJsonString(personObject);
	        String uri = "/person/post";

	        // Performing the POST request and verifying response
	        mockMvc.perform(post(uri)
	                .content(inputInJson)
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isBadRequest());
	    }
	    

	    @Test
	    public void  testupdatePerson_shouldGive200Ok() throws Exception {
	    	// Prepare a Person object to be created first
	        Person existingPerson = new Person("John", "Doe", "Anytown", "Anytown-Tunisie", "4145", "111111111", "john.doe@gmail.com");

	        // Ensure the person is created first
	        when(personService.createPersons(any(Person.class))).thenReturn(existingPerson);

	        // Perform the POST request to create the person
	        mockMvc.perform(post("/person/post")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(existingPerson)))
	                .andExpect(status().isOk());

	        // Prepare the updated Person object
	        Person updatedPerson = new Person("John", "Doe", "Anytown", "Anytown-Tunisie", "4145", "11111111", "john.doe.updated@gmail.com");

	        // Mock the behavior of personService.updatePerson to update the existing person
	        when(personService.updatePerson(any(Person.class))).thenReturn(updatedPerson);

	        // Perform the PUT request to update the existing person and expect 200 OK
	        mockMvc.perform(put("/person/put")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(asJsonString(updatedPerson)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.firstName").value(updatedPerson.getFirstName())) // Verify the updated data
	                .andExpect(jsonPath("$.lastName").value(updatedPerson.getLastName()))
	                .andExpect(jsonPath("$.email").value(updatedPerson.getEmail()));
	          }
	    
	    
	    @Test
	    public void testUpdatePerson_shouldGive404NotFound() throws Exception {
	    	 // Arrange
	        Person person = new Person();
	        person.setFirstName("Julyen");
	        person.setLastName("Doe");
	        person.setAddress("123 Main St");
	        person.setCity("Anytown");
	        person.setZip("12345");
	        person.setPhone("555-1234");
	        person.setEmail("john.doe@example.com");

	        when(personService.updatePerson(any(Person.class))).thenThrow(new NotFoundException());

	        // Act & Assert
	        mockMvc.perform(put("/person/put")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content("{ \"firstName\": \"Julyen\", \"lastName\": \"Doe\", \"address\": \"123 Main St\", \"city\": \"Anytown\", \"zip\": \"12345\", \"phone\": \"555-1234\", \"email\": \"john.doe@example.com\" }"))
	                .andExpect(status().isNotFound())
	                .andDo(print()); // Print the response for debugging
	    }
	    
	    
	    @Test
	    public void  testdeletePerson_shouldGive200Ok() throws Exception {
	    	// Create a person
	        Person person = new Person("John", "Doe","Anytown","Anytown-Tunisie","4145","1111111","benouirane@gmail.com");
	        // Mocking service method to delete the person
	        doNothing().when(personService).delete("John", "Doe");
	        
	        // Performing the DELETE request and verifying response
	        mockMvc.perform(post("/person/post")
	                .content(asJsonString(person))
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());

	        // Performing the DELETE request and verifying response
	        mockMvc.perform(delete("/person/delete")
	                .param("firstName", "John")
	                .param("lastName", "Doe")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(content().string("The Person has been successfully deleted"));
	          }
	    
	    @Test
	    void testDeletePerson_ShouldReturn404NotFound() throws Exception {
	        // Mocking service method to throw NotFoundException
	        //doThrow(new NotFoundException()).when(personService).delete("John", "Doe");
	        
	        // Performing the DELETE request and verifying response
	        mockMvc.perform(MockMvcRequestBuilders.delete("/person/delete")
	                .param("firstName", "Johny")
	                .param("lastName", "Doe")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(MockMvcResultMatchers.status().isNotFound())
	                .andExpect(content().string("Person not found: Entity not found"));

	    }
	    
	    

	    
	    
	    
}
