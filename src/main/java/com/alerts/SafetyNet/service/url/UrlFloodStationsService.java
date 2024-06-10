package com.alerts.SafetyNet.service.url;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.dto.FloodStationsDto;
import com.alerts.SafetyNet.dto.HabitantDto;
import com.alerts.SafetyNet.entity.Firestation;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.FirestationRepository;
import com.alerts.SafetyNet.repository.MedicalrecordRepository;
import com.alerts.SafetyNet.repository.PersonRepository;

@Service
public class UrlFloodStationsService {
	
	@Autowired
	PersonRepository personRepository;
	@Autowired
	FirestationRepository firestationRepository;
	@Autowired
	MedicalrecordRepository medicalRecordRepository;
	
	public List<FloodStationsDto> getFloodStationsService (List<Integer> stations) throws NotFoundException {
		
        List<FloodStationsDto> FloodStation = new ArrayList<>();
        List<Firestation> Firestations = new ArrayList<>();
        List<String> addressesOfStations = new ArrayList<>();
        for (Integer station : stations) {
        	Firestations = firestationRepository.getFirestationsByStationNumber(station);
        }
        for (Firestation  firestation : Firestations) {
        	addressesOfStations.addAll(firestationRepository.getFirestationAddresses(firestation.getStation()));
        }
        for (String  address : addressesOfStations) {
            List<Person> personsFromAddress = personRepository.getPersonsByAddresse(address);
            List<HabitantDto> HabitantByAddress = new ArrayList<>();
            for(Person person :personsFromAddress ) {
           	 List<String> medications = medicalRecordRepository.getMedicationsByLastName(person.getLastName()) ;
			 List<String> allergies = medicalRecordRepository.getAllergiesByLastName(person.getLastName()) ;
			 int age =	medicalRecordRepository.havePersonAge(person);
            	HabitantDto habittant = new HabitantDto(
   	                 person.getFirstName(),
   	                 person.getPhone(),
   	                 age,
   	                 medications,
   	                 allergies
   	         );
            	HabitantByAddress.add(habittant);
            }
            FloodStationsDto FinalPersons = new FloodStationsDto(address,HabitantByAddress);

            FloodStation.add(FinalPersons);
        }
             return FloodStation;
		}
		        		
}
