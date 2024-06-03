package com.alerts.SafetyNet.urlServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alerts.SafetyNet.dto.PersonDto;
import com.alerts.SafetyNet.dto.UrlPersonnesCouvertesCaserneDTO;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.FirestationRepository;
import com.alerts.SafetyNet.repository.MedicalrecordRepository;
import com.alerts.SafetyNet.repository.PersonRepository;
import com.alerts.SafetyNet.service.url.ObjectDtoConverterService;
import com.alerts.SafetyNet.service.url.UrlPersonnesCouvertesCaserneService;

@ExtendWith(MockitoExtension.class)
public class UrlPersonnesCouvertesCaserneServiceTest {
	@Mock
    private FirestationRepository firestationRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private MedicalrecordRepository medicalRecordRepository;

    @Mock
    private ObjectDtoConverterService dtoService;

    @InjectMocks
    private UrlPersonnesCouvertesCaserneService urlPersonnesCouvertesCaserneService;

    @Test
    public void testUrlPersonnesCouvertesCaserneService() throws NotFoundException {
        // Préparation des données de test
        Integer stationNumber = 1;
        List<String> addresses = Arrays.asList("123 Main St", "456 Elm St");
        List<Person> personsCovered = Arrays.asList(
                new Person("John", "Doe", "123 Main St", "", "", "", ""),
                new Person("Jane", "Doe", "456 Elm St", "", "", "", "")
        );
        List<PersonDto> personsConvertedDTO = personsCovered.stream()
                .map(person -> new PersonDto(person.getFirstName(), person.getLastName(), "", "", "", "", ""))
                .collect(Collectors.toList());

        // Simulation des réponses des repositories et du service de conversion DTO
        when(firestationRepository.getFirestationAddresses(stationNumber)).thenReturn(addresses);
        when(personRepository.getPersonsByAddresses(addresses)).thenReturn(personsCovered);
        when(dtoService.buildPersonDto(any(Person.class), any())).thenReturn(
                new PersonDto("John", "Doe", "", "", "", "", ""),
                new PersonDto("Jane", "Doe", "", "", "", "", "")
        );
        when(medicalRecordRepository.ifAdult(any(Person.class))).thenReturn(true, false);
        when(medicalRecordRepository.ifChild(any(Person.class))).thenReturn(false, true);

        // Appel de la méthode à tester
        UrlPersonnesCouvertesCaserneDTO result = urlPersonnesCouvertesCaserneService.UrlPersonnesCouvertesCaserneService(stationNumber);

        // Assertions
        assertEquals(2, result.getPersons().size(), "Le nombre de personnes est incorrect");
        assertEquals(1, result.getAdultsNumber(), "Le nombre d'adultes est incorrect");
        assertEquals(1, result.getChildrenNumber(), "Le nombre d'enfants est incorrect");
    }
    

}
