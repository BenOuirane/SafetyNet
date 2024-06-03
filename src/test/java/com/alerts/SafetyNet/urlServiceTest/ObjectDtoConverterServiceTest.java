package com.alerts.SafetyNet.urlServiceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alerts.SafetyNet.configuration.DtoConstants.PersonField;
import com.alerts.SafetyNet.dto.PersonDto;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.service.url.ObjectDtoConverterService;

public class ObjectDtoConverterServiceTest {
	
	 private ObjectDtoConverterService objectDtoConverterService;

	    @BeforeEach
	    public void setUp() {
	        objectDtoConverterService = new ObjectDtoConverterService();
	    }

	    @Test
	    public void testBuildPersonDto_AllFields() {
	        Person person = new Person();
	        person.setFirstName("John");
	        person.setLastName("Doe");
	        person.setAddress("123 Main St");
	        person.setCity("Anytown");
	        person.setZip("12345");
	        person.setPhone("555-1234");
	        person.setEmail("john.doe@example.com");

	        PersonField[] fields = PersonField.values();

	        PersonDto personDto = objectDtoConverterService.buildPersonDto(person, fields);

	        assertEquals("John", personDto.getFirstName());
	        assertEquals("Doe", personDto.getLastName());
	        assertEquals("123 Main St", personDto.getAddress());
	        assertEquals("Anytown", personDto.getCity());
	        assertEquals("12345", personDto.getZip());
	        assertEquals("555-1234", personDto.getPhone());
	        assertEquals("john.doe@example.com", personDto.getEmail());
	    }

	    @Test
	    public void testBuildPersonDto_SomeFields() {
	        Person person = new Person();
	        person.setFirstName("John");
	        person.setLastName("Doe");
	        person.setAddress("123 Main St");
	        person.setCity("Anytown");
	        person.setZip("12345");
	        person.setPhone("555-1234");
	        person.setEmail("john.doe@example.com");

	        PersonField[] fields = { PersonField.FIRST_NAME, PersonField.LAST_NAME, PersonField.EMAIL };

	        PersonDto personDto = objectDtoConverterService.buildPersonDto(person, fields);

	        assertEquals("John", personDto.getFirstName());
	        assertEquals("Doe", personDto.getLastName());
	        assertNull(personDto.getAddress());
	        assertNull(personDto.getCity());
	        assertNull(personDto.getZip());
	        assertNull(personDto.getPhone());
	        assertEquals("john.doe@example.com", personDto.getEmail());
	    }

	    @Test
	    public void testBuildPersonDto_NoFields() {
	        Person person = new Person();
	        person.setFirstName("John");
	        person.setLastName("Doe");
	        person.setAddress("123 Main St");
	        person.setCity("Anytown");
	        person.setZip("12345");
	        person.setPhone("555-1234");
	        person.setEmail("john.doe@example.com");

	        PersonField[] fields = {};

	        PersonDto personDto = objectDtoConverterService.buildPersonDto(person, fields);

	        assertNull(personDto.getFirstName());
	        assertNull(personDto.getLastName());
	        assertNull(personDto.getAddress());
	        assertNull(personDto.getCity());
	        assertNull(personDto.getZip());
	        assertNull(personDto.getPhone());
	        assertNull(personDto.getEmail());
	    }
	    

}
