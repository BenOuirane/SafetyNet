package com.alerts.SafetyNet.service.url;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.dto.PhoneAlertDto;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.model.Firestation;
import com.alerts.SafetyNet.model.Person;
import com.alerts.SafetyNet.repository.FirestationRepository;
import com.alerts.SafetyNet.repository.PersonRepository;

@Service
public class UrlPhoneAlertService {
	
	@Autowired
	PersonRepository personRepository;
	@Autowired
	FirestationRepository firestationRepository;
	
	public List<PhoneAlertDto> getPhoneByFirestation(int station) throws NotFoundException {
		List<Firestation> firestationList = firestationRepository.getFirestationsByStationNumber(station);
        if (firestationList.isEmpty()) {
            throw new NotFoundException();
        }
        List<String> phoneNumbers = new ArrayList<>();
        for (Firestation firestation : firestationList) {
            List<Person> peopleIntoFirestationAddress = personRepository.getPersonsByAddresse(firestation.getAddress());
            for (Person person : peopleIntoFirestationAddress) {
                phoneNumbers.add(person.getPhone());
            }
        }
        if (phoneNumbers.isEmpty()) {
            throw new NotFoundException();
        }
        PhoneAlertDto phoneAlertDto = new PhoneAlertDto(phoneNumbers);
        return List.of(phoneAlertDto);
	}

}
