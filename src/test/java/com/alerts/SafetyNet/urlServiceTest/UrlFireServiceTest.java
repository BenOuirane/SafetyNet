package com.alerts.SafetyNet.urlServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.alerts.SafetyNet.dto.FireDto;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.FirestationRepository;
import com.alerts.SafetyNet.repository.MedicalrecordRepository;
import com.alerts.SafetyNet.repository.PersonRepository;
import com.alerts.SafetyNet.service.url.UrlFireService;

public class UrlFireServiceTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private FirestationRepository firestationRepository;
    @Mock
    private MedicalrecordRepository medicalRecordRepository;
    @InjectMocks
    private UrlFireService urlFireService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetFireService() throws NotFoundException {
        // Arrange
        String address = "123 Test St";
        Person person1 = new Person("John", "Doe", "123 Test St", "Test City", "12345", "123456789", "john@example.com");
        Person person2 = new Person("Jane", "Doe", "123 Test St", "Test City", "12345", "987654321", "jane@example.com");
        List<Person> persons = List.of(person1, person2);
        when(personRepository.getPersonsByAddresse(address)).thenReturn(persons);
        when(firestationRepository.getStationByAddress(address)).thenReturn(1);
        when(medicalRecordRepository.getMedicationsByLastName(any())).thenReturn(List.of("Med1", "Med2"));
        when(medicalRecordRepository.getAllergiesByLastName(any())).thenReturn(List.of("Allergy1", "Allergy2"));
        // Act
        List<FireDto> result = urlFireService.getFireService(address);
        // Assert
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getFirestationNumber());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("123456789", result.get(0).getPhone());
        assertEquals(List.of("Med1", "Med2"), result.get(0).getMedications());
        assertEquals(List.of("Allergy1", "Allergy2"), result.get(0).getAllergies());
    }
}
