package com.alerts.SafetyNet.urlServiceTest;

import com.alerts.SafetyNet.dto.PersonInfoDto;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.MedicalrecordRepository;
import com.alerts.SafetyNet.repository.PersonRepository;
import com.alerts.SafetyNet.service.url.UrlPersonInfoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UrlPersonInfoServiceTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private MedicalrecordRepository medicalRecordRepository;
    @InjectMocks
    private UrlPersonInfoService personInfoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPersonInfoService_Success() throws NotFoundException {
        // Arrange
        String lastName = "Doe";
        Person person1 = new Person("John", "Doe", "john.doe@example.com","","","","");
        Person person2 = new Person("Jane", "Doe", "jane.doe@example.com","","","","");
        List<Person> personsByLastName = new ArrayList<>();
        personsByLastName.add(person1);
        personsByLastName.add(person2);
        when(personRepository.getPersonsInfoByLastName(lastName)).thenReturn(personsByLastName);
        when(medicalRecordRepository.getMedicationsByLastName(lastName)).thenReturn(new ArrayList<>());
        when(medicalRecordRepository.getAllergiesByLastName(lastName)).thenReturn(new ArrayList<>());
        when(medicalRecordRepository.havePersonAge(person1)).thenReturn(30);
        when(medicalRecordRepository.havePersonAge(person2)).thenReturn(25);
        // Act
        List<PersonInfoDto> result = personInfoService.getPersonInfoService(lastName);
        // Assert
        assertEquals(2, result.size());
        assertEquals(person1.getLastName(), result.get(0).getLastName());
        assertEquals(person1.getAddress(), result.get(0).getAddress());
        assertEquals(30, result.get(0).getAge());
        assertEquals(person1.getEmail(), result.get(0).getEmail());
        assertEquals(0, result.get(0).getMedications().size());
        assertEquals(0, result.get(0).getAllergies().size());

        assertEquals(person2.getLastName(), result.get(1).getLastName());
        assertEquals(person2.getAddress(), result.get(1).getAddress());
        assertEquals(25, result.get(1).getAge());
        assertEquals(person2.getEmail(), result.get(1).getEmail());
        assertEquals(0, result.get(1).getMedications().size());
        assertEquals(0, result.get(1).getAllergies().size());
    }

}
