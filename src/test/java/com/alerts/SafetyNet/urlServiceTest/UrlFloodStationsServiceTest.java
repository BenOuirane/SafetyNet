package com.alerts.SafetyNet.urlServiceTest;

import com.alerts.SafetyNet.dto.FloodStationsDto;
import com.alerts.SafetyNet.dto.HabitantDto;
import com.alerts.SafetyNet.entity.Firestation;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.FirestationRepository;
import com.alerts.SafetyNet.repository.MedicalrecordRepository;
import com.alerts.SafetyNet.repository.PersonRepository;
import com.alerts.SafetyNet.service.url.UrlFloodStationsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UrlFloodStationsServiceTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private FirestationRepository firestationRepository;
    @Mock
    private MedicalrecordRepository medicalRecordRepository;
    @InjectMocks
    private UrlFloodStationsService urlFloodStationsService;

    
    @Test
    public void testGetFloodStationsService_Success() throws NotFoundException {
        // Mock data
        List<Integer> stations = Arrays.asList(1, 2);
        Firestation firestation1 = new Firestation();
        firestation1.setStation(1);
        firestation1.setAddress("Address1");
        Firestation firestation2 = new Firestation();
        firestation2.setStation(2);
        firestation2.setAddress("Address2");
        when(firestationRepository.getFirestationsByStationNumber(1)).thenReturn(Collections.singletonList(firestation1));
        when(firestationRepository.getFirestationsByStationNumber(2)).thenReturn(Collections.singletonList(firestation2));
        when(firestationRepository.getFirestationAddresses(2)).thenReturn(Collections.singletonList("Address2"));
        Person person1 = new Person();
        person1.setFirstName("John");
        person1.setLastName("Doe");
        person1.setAddress("Address1");
        Person person2 = new Person();
        person2.setFirstName("Jane");
        person2.setLastName("Doe");
        person2.setAddress("Address2");
        when(personRepository.getPersonsByAddresse("Address2")).thenReturn(Collections.singletonList(person2));
        when(medicalRecordRepository.getMedicationsByLastName("Doe")).thenReturn(Arrays.asList("Medication1", "Medication2"));
        when(medicalRecordRepository.getAllergiesByLastName("Doe")).thenReturn(Collections.singletonList("Allergy"));
        when(medicalRecordRepository.havePersonAge(person2)).thenReturn(25);
        // Call service method
        List<FloodStationsDto> result = urlFloodStationsService.getFloodStationsService(stations);
        // Assertions
        assertEquals(1, result.size());
        // Check the first flood station
        FloodStationsDto floodStation1 = result.get(0);
        assertEquals("Address2", floodStation1.getAddress());
        assertEquals(1, floodStation1.getHabitants().size());
        HabitantDto habitant1 = floodStation1.getHabitants().get(0);
        assertEquals("Jane", habitant1.getLastName());
        assertEquals(25, habitant1.getAge());
        assertEquals(Arrays.asList("Medication1", "Medication2"), habitant1.getMedications());
        assertEquals(Collections.singletonList("Allergy"), habitant1.getAllergies());
        
    }

}
