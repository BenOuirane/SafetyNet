package com.alerts.SafetyNet.service.url;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.alerts.SafetyNet.configuration.DtoConstants;
import com.alerts.SafetyNet.dto.PersonDto;
import com.alerts.SafetyNet.dto.UrlPersonnesCouvertesCaserneDTO;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
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
	
	
	@Autowired
	ObjectDtoConverterService dtoService;
	
	
	public UrlPersonnesCouvertesCaserneDTO UrlPersonnesCouvertesCaserneService (@RequestParam Integer stationNumber) throws NotFoundException {
	
				List<String> addresses = firestationRepository.getFirestationAddresses(stationNumber);
				List<Person> personsCovered = personRepository.getPersonsByAddresses(addresses);
				List<PersonDto> personsConvertedDTO = personsCovered.stream() 
						        .map(person -> dtoService.buildPersonDto(person, DtoConstants.UrlFirestationCoveragePerson))
										.collect(Collectors.toList());
				// calculate counts
				long adultsCount = personsCovered.stream().filter(p -> medicalRecordRepository.ifAdult(p)).count();

				long childrenCount = personsCovered.stream().filter(p -> medicalRecordRepository.ifChild(p)).count();
				
				// create the final DTO object that will be returned by URL Controller
				UrlPersonnesCouvertesCaserneDTO personnesCouvertesCaserneDTO = new UrlPersonnesCouvertesCaserneDTO();
				personnesCouvertesCaserneDTO.setPersons(personsConvertedDTO);
				personnesCouvertesCaserneDTO.setAdultsNumber((int) (adultsCount));
				personnesCouvertesCaserneDTO.setChildrenNumber((int) (childrenCount));
				
				return personnesCouvertesCaserneDTO;
	
	}

}
