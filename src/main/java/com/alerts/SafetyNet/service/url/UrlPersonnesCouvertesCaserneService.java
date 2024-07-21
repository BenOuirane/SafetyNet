package com.alerts.SafetyNet.service.url;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.dto.PersonByStationDto;
import com.alerts.SafetyNet.dto.UrlPersonnesCouvertesCaserneDTO;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.model.Person;
import com.alerts.SafetyNet.repository.FirestationRepository;
import com.alerts.SafetyNet.repository.MedicalrecordRepository;
import com.alerts.SafetyNet.repository.PersonRepository;

@Service
public class UrlPersonnesCouvertesCaserneService {
	
	@Autowired
	PersonRepository personRepository;
	@Autowired
	FirestationRepository firestationRepository;
	@Autowired
	MedicalrecordRepository medicalRecordRepository;
	
	public UrlPersonnesCouvertesCaserneDTO urlPersonnesCouvertesCaserneService (Integer stationNumber) throws NotFoundException {
	
				List<String> addresses = firestationRepository.getFirestationAddresses(stationNumber);
				List<Person> personsCovered = personRepository.getPersonsByAddresses(addresses);
		        List<PersonByStationDto> PersonsByStation = new ArrayList<>();

				for(Person person: personsCovered) {
					PersonByStationDto PersonByStation = new PersonByStationDto(
		   	                 person.getLastName(),
		   	                 person.getFirstName(),
		   	                 person.getAddress(),
		   	                 person.getPhone()	   	           
		   	         );
					PersonsByStation.add(PersonByStation);
		            }					
				// calculate counts
				long adultsCount = personsCovered.stream().filter(p -> medicalRecordRepository.ifAdult(p)).count();
				long childrenCount = personsCovered.stream().filter(p -> medicalRecordRepository.ifChild(p)).count();
				// create the final DTO object that will be returned by URL Controller
				UrlPersonnesCouvertesCaserneDTO personnesCouvertesCaserneDTO = new UrlPersonnesCouvertesCaserneDTO();
				personnesCouvertesCaserneDTO.setPersons(PersonsByStation);
				personnesCouvertesCaserneDTO.setAdultsNumber((int) (adultsCount));
				personnesCouvertesCaserneDTO.setChildrenNumber((int) (childrenCount));
				return personnesCouvertesCaserneDTO;	
	}

}
