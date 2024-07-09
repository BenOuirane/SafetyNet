package com.alerts.SafetyNet.service.url;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.dto.ChildAlertDto;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.MedicalrecordRepository;
import com.alerts.SafetyNet.repository.PersonRepository;

@Service
public class UrlChildAlertService {	
	
	@Autowired
	PersonRepository personRepository;
	@Autowired
	MedicalrecordRepository medicalRecordRepository;

	public List<ChildAlertDto> getChildrenIntoAddress(String address) throws NotFoundException {
		List<Person> PeopleIntoAddress   = personRepository .getPersonsByAddresse(address);
		List<Person> ChildrenIntoAddress = PeopleIntoAddress.stream().filter(p -> medicalRecordRepository
				                                            .ifChild(p)).collect(Collectors.toList());
		List<Person> FamilyIntoAddress   = PeopleIntoAddress.stream().filter(p -> medicalRecordRepository
                                                            .ifAdult(p)).collect(Collectors.toList());
        List<ChildAlertDto> childrenIntoAddressToRead = new ArrayList<>();
		for(Person person : ChildrenIntoAddress ) {
		 int age =	medicalRecordRepository.havePersonAge(person);
		 ChildAlertDto Kid = new ChildAlertDto(
                 person.getFirstName(),
                 person.getLastName(),
                 age,
                 FamilyIntoAddress
         );
		 childrenIntoAddressToRead.add(Kid);
		}
        return childrenIntoAddressToRead;
	}

}
