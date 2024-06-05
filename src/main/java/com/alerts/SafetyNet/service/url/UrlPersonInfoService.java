package com.alerts.SafetyNet.service.url;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.dto.PersonInfoDto;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.MedicalrecordRepository;
import com.alerts.SafetyNet.repository.PersonRepository;

@Service
public class UrlPersonInfoService {
	
	@Autowired
	PersonRepository personRepository;
	@Autowired
	MedicalrecordRepository medicalRecordRepository;
	
	public List<PersonInfoDto> getPersonInfoService (String lastName) throws NotFoundException {
		
        List<PersonInfoDto> PersonsInfo = new ArrayList<>();
        List<Person> personsByLastName = personRepository.getPersonsInfoByLastName(lastName);
        
        for(Person person: personsByLastName) {
        	 List<String> medications = medicalRecordRepository.getMedicationsByLastName(person.getLastName()) ;
			 List<String> allergies = medicalRecordRepository.getAllergiesByLastName(person.getLastName()) ;
			 int age =	medicalRecordRepository.havePersonAge(person);
			 PersonInfoDto personInfo = new PersonInfoDto(
   	                 person.getLastName(),
   	                 person.getAddress(),
   	                 age,
   	                 person.getEmail(),
   	                 medications,
   	                 allergies
   	         );
			 PersonsInfo.add(personInfo);
            }
        return PersonsInfo;
	}

}
