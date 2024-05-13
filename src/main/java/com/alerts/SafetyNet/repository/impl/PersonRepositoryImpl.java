package com.alerts.SafetyNet.repository.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alerts.SafetyNet.configuration.FileHandling;
import com.alerts.SafetyNet.dto.PersonDto;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.repository.PersonRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PersonRepositoryImpl implements PersonRepository{
	
	private static final String JSON_FILE_PATH = "src/main/resources/data/data.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

	private final FileHandling fileHandling;

    public PersonRepositoryImpl(FileHandling fileHandling) {
        this.fileHandling = fileHandling;
    }
    

	@Override
	public List<Person> getPersons() {
		 JsonNode dataJson = fileHandling.readFile();
	     return fileHandling.getPersonList(dataJson);
	}
	
	@Override
    public PersonDto addPerson(PersonDto person) throws IOException {
        Person personCreate = objectMapper.convertValue(person, Person.class);

		 JsonNode dataJson = fileHandling.readFile();

		fileHandling.getPersonList(dataJson).add(personCreate);
    /*
        List<Person> allPersons = readJsonFile();
        allPersons.add(person);
        saveJsonFile(allPersons);
        */
		return person;
    }
	
	
	private List<Person> readJsonFile() throws IOException {
        return objectMapper.readValue(new File(JSON_FILE_PATH), new TypeReference<List<Person>>() {});
    }

    private void saveJsonFile(List<Person> persons) throws IOException {
        objectMapper.writeValue(new File(JSON_FILE_PATH), persons);
    }
	
	
	

}
