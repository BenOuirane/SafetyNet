package com.alerts.SafetyNet.controllerTest;

import static org.junit.Assert.assertEquals;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.alerts.SafetyNet.controller.PersonController;
import com.alerts.SafetyNet.entity.Person;
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
	    
	    @Before
	    public  void setUp() throws Exception {
	        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
	    }
	    
	    
	    private String mapToJson(Object object) throws JsonProcessingException {
	         ObjectMapper objectMapper = new ObjectMapper();
	         return  objectMapper.writeValueAsString(object);
	     }
	    
	    /*
	    @Test
	    public void  testcreatePerson_shouldGive200Ok() throws Exception {
	      Person  personObject = new Person(1L,"BEN","Hajer","Djerba","Djerba-Tunisie","4145","22030667","benouirane@gmail.com");
	      String inputInJson = this.mapToJson(personObject);
	      String uri = "/person/post";
	      Mockito.when((personService.createPerson(Mockito.any(Person.class)))).thenReturn(personObject);
	      RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON).content(inputInJson)
	                .contentType(MediaType.APPLICATION_JSON);
	      MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	      MockHttpServletResponse response = result.getResponse();
		  String outputInJson = response.getContentAsString();
		   assertEquals(outputInJson, (inputInJson));
	       assertEquals(HttpStatus.OK.value(), response.getStatus());
	          }
	    
	    @Test
	    public void  testupdatePerson_shouldGive200Ok() throws Exception {
	      Person  personObject = new Person(1L,"BEN","Hajer","Djerba","Djerba-Tunisie","1234","22030667","benouirane@gmail.com");
	      String inputInJson = this.mapToJson(personObject);
	      String uri = "/person/put/1";
	      RequestBuilder requestBuilder = MockMvcRequestBuilders.put(uri).accept(MediaType.APPLICATION_JSON).content(inputInJson)
	                .contentType(MediaType.APPLICATION_JSON);
	      MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	      MockHttpServletResponse response = result.getResponse();
	      String outputInJson = response.getContentAsString();
		  assertEquals(outputInJson,inputInJson);
	      assertEquals(HttpStatus.OK.value(), response.getStatus());
	          }
	    
	    */

	    
	    
	    
}
