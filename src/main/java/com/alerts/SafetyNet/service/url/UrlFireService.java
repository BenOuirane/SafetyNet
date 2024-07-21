package com.alerts.SafetyNet.service.url;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.dto.FireDto;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.model.Person;
import com.alerts.SafetyNet.repository.FirestationRepository;
import com.alerts.SafetyNet.repository.MedicalrecordRepository;
import com.alerts.SafetyNet.repository.PersonRepository;

@Service
public class UrlFireService {
	
	@Autowired
	PersonRepository personRepository;
	@Autowired
	FirestationRepository firestationRepository;
	@Autowired
	MedicalrecordRepository medicalRecordRepository;
	
	public List<FireDto> getFireService (String address) throws NotFoundException {
		
		   List<Person> personsCoveredByAddress = personRepository.getPersonsByAddresse(address);		   
		   List<FireDto> FireByAddressToRead = new ArrayList<>();	   
			for(Person person : personsCoveredByAddress ) {
			 int age =	medicalRecordRepository.havePersonAge(person);
			 int station = firestationRepository.getStationByAddress(person.getAddress());
			 List<String> medications = medicalRecordRepository.getMedicationsByLastName(person.getLastName()) ;
			 List<String> allergies = medicalRecordRepository.getAllergiesByLastName(person.getLastName()) ;
			 FireDto Fire = new FireDto(
					 station,
	                 person.getFirstName(),
	                 person.getPhone(),
	                 age,
	                 medications,
	                 allergies
	         );
			 FireByAddressToRead.add(Fire);
			}
	        return FireByAddressToRead;
		}
		        		
	
	}



