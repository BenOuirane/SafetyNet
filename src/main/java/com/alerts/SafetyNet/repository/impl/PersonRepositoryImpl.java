package com.alerts.SafetyNet.repository.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alerts.SafetyNet.configuration.FileHandling;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.repository.PersonRepository;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class PersonRepositoryImpl implements PersonRepository{
	
	
	private final FileHandling fileHandling;

    public PersonRepositoryImpl(FileHandling fileHandling) {
        this.fileHandling = fileHandling;
    }
    

	@Override
	public List<Person> getPersons() {
		 JsonNode dataJson = fileHandling.readFile();
	     return fileHandling.getPersonList(dataJson);
	}
	
	
	

}
