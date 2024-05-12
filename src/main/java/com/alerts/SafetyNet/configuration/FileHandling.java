package com.alerts.SafetyNet.configuration;

import java.io.IOException;
import java.util.List;
import com.alerts.SafetyNet.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;

@Getter
@Configuration
@Component
public class FileHandling {

	
    private static  Logger logger = LogManager.getLogger(FileHandling.class);
    
    private  List<Person> persons;
    private  ObjectMapper objectMapper = new ObjectMapper();

    /**
     * This constructor calls all the SafetyNetAlertsCatalog's methods to set up the different lists.
     */
    public FileHandling() {
        JsonNode dataJson = readFile();
        persons = getPersonList(dataJson);
        logger.debug("the constructor FileHandling succeeded");
    }

    
    public  JsonNode readFile() {
         String RESOURCE_NAME = "data/data.json";
        Resource data = new ClassPathResource(RESOURCE_NAME);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;
        try {
            jsonNode = objectMapper.readTree(data.getFile());
        } catch (IOException e) {
            logger.error("the copy of the data file failed", e);
            throw new RuntimeException(e);
        }
        logger.debug("copy the data file");
        return jsonNode;
    }
    
    /**
     * Get all the persons from the JSON source.
     *
     * @return a list of all persons, obtained from JSON source, duplicates are possible
     */
     public  List<Person> getPersonList(JsonNode dataJson) {
        String jsonString = dataJson.get("persons").toString();
        try {
            logger.debug("create a list of persons");
            return objectMapper.readValue(jsonString, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            logger.error("the create of persons list failed", e);
            throw new RuntimeException(e);
        }
     }
    
}
