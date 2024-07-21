package com.alerts.SafetyNet.loadservice;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.configuration.JsonFileConstants;
import com.alerts.SafetyNet.model.Person;
import com.alerts.SafetyNet.repository.impl.PersonRepositoryImpl;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Import Json data into Person Repository
 * 
 * @author BEN OUIRANE Hajeur
 *
 */
@Service
public class LoadPersonsService {
	
	 @Autowired
	 PersonRepositoryImpl personRepository;
	/**
	 * Import Json data into Person Repository
	 * 
	 * @param personsNode
	 * @throws IOException 
	 */
	
	public void loadPersons(JsonNode personsNode) throws IOException {
		for (JsonNode personNode : personsNode) {
			Person person = new Person();
			person.setFirstName(personNode.path(JsonFileConstants.person_firstName).asText());
			person.setLastName(personNode.path(JsonFileConstants.person_lastName).asText());
			person.setAddress(personNode.path(JsonFileConstants.person_address).asText());
			person.setCity(personNode.path(JsonFileConstants.person_city).asText());
			person.setZip(personNode.path(JsonFileConstants.person_zip).asText());
			person.setPhone(personNode.path(JsonFileConstants.person_phone).asText());
			person.setEmail(personNode.path(JsonFileConstants.person_email).asText());
			personRepository.addPerson(person);
		}
	}
	
}
