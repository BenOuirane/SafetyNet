package com.alerts.SafetyNet.loadservice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.configuration.JsonFileConstants;
import com.alerts.SafetyNet.entity.MedicalRecord;
import com.alerts.SafetyNet.repository.MedicalrecordRepository;
import com.fasterxml.jackson.databind.JsonNode;
/**
 * Import Json data into Medical Records Repository
 * 
 * @author BEN OUIRANE Hajeur
 *
 */
@Service
public class LoadMedicalRecordsService {
	
	@Autowired
	private MedicalrecordRepository medicalRecordRepository;
	
	
	/**
	 * Import Json data into MedicalRecord Repository
	 * @param medicalrecordsNode 
	 */
	public void loadMedicalRecords(JsonNode medicalrecordsNode) {
		for (JsonNode medicalRecordNode : medicalrecordsNode) {
		MedicalRecord medicalRecord = new MedicalRecord();
		medicalRecord.setFirstName(medicalRecordNode.path(JsonFileConstants.medicalrecord_firstName).asText());
		medicalRecord.setLastName(medicalRecordNode.path(JsonFileConstants.medicalrecord_lastName).asText());
		medicalRecord.setBirthdate(this.stringToDate(medicalRecordNode.path(JsonFileConstants.medicalrecord_birthdate).asText()));
		
		// medications
					List<String> medications = new ArrayList<>();
					JsonNode medicationData = medicalRecordNode.path(JsonFileConstants.medicalrecord_medications);
					for (JsonNode medicalrecordNode : medicationData) {
						medications.add(medicalrecordNode.asText());
					}
					medicalRecord.setMedications(medications);
					
	    // allergies
					List<String> allergies = new ArrayList<>();
					JsonNode allergiesData = medicalRecordNode.path(JsonFileConstants.medicalrecord_allergies);
					for (JsonNode allergieNode : allergiesData) {
						allergies.add(allergieNode.asText());
					}
					medicalRecord.setAllergies(allergies);

					medicalRecordRepository.addMedicalRecord(medicalRecord);			
					
		
		}
		
	}
	
	
	
	public LocalDate stringToDate(String strDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		return LocalDate.parse(strDate, formatter);
	}
	
	
	
	

}
