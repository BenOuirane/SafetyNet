package com.alerts.SafetyNet.configuration;

import java.io.IOException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.alerts.SafetyNet.loadservice.LoadMedicalRecordsService;
import com.alerts.SafetyNet.loadservice.LoadPersonsService;
import com.alerts.SafetyNet.loadservice.loadFirestationsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {
	
	@Autowired
	LoadPersonsService loadPersonsService;
	@Autowired
	loadFirestationsService loadFirestationsService;
	@Autowired
	LoadMedicalRecordsService loadMedicalRecordsService;
	
	@Value("${net.safety.alerts.dataUrl}")
	private String dataUrl;
	
	/**
	 * Import the JsonFile found at the URL stored in application.properties
	 * 
	 * @throws IOException
	 */
	@PostConstruct
	public void importJsonData() throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root = objectMapper.readTree(new URL(dataUrl));
		loadPersonsService.loadPersons(root.path(JsonFileConstants.persons));
		loadFirestationsService.loadFirestations(root.path(JsonFileConstants.firestations));
		loadMedicalRecordsService.loadMedicalRecords(root.path(JsonFileConstants.medicalrecords));
	}
	
}
