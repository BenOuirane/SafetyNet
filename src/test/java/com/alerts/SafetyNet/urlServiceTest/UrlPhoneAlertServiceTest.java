package com.alerts.SafetyNet.urlServiceTest;

import com.alerts.SafetyNet.dto.PhoneAlertDto;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.model.Firestation;
import com.alerts.SafetyNet.model.Person;
import com.alerts.SafetyNet.repository.FirestationRepository;
import com.alerts.SafetyNet.repository.PersonRepository;
import com.alerts.SafetyNet.service.url.UrlPhoneAlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UrlPhoneAlertServiceTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private FirestationRepository firestationRepository;
    @InjectMocks
    private UrlPhoneAlertService urlPhoneAlertService;
    private Firestation firestation;
    private Person person;

    @BeforeEach
    public void setUp() {
        firestation = new Firestation();
        firestation.setAddress("123 Main St");
        person = new Person();
        person.setPhone("123-456-7890");
        person.setAddress("123 Main St");
    }

    @Test
    public void testGetPhoneByFirestation_Success() throws NotFoundException {
        when(firestationRepository.getFirestationsByStationNumber(1))
                .thenReturn(Collections.singletonList(firestation));
        when(personRepository.getPersonsByAddresse("123 Main St"))
                .thenReturn(Collections.singletonList(person));
        List<PhoneAlertDto> result = urlPhoneAlertService.getPhoneByFirestation(1);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getPhone().size());
        assertEquals("123-456-7890", result.get(0).getPhone().get(0));
    }

    @Test
    public void testGetPhoneByFirestation_FirestationNotFound() throws NotFoundException {
        when(firestationRepository.getFirestationsByStationNumber(1))
                .thenReturn(Collections.emptyList());
        assertThrows(NotFoundException.class, () -> {
            urlPhoneAlertService.getPhoneByFirestation(1);
        });
    }

    @Test
    public void testGetPhoneByFirestation_NoPhonesFound() throws NotFoundException {
        when(firestationRepository.getFirestationsByStationNumber(1))
                .thenReturn(Collections.singletonList(firestation));
        when(personRepository.getPersonsByAddresse("123 Main St"))
                .thenReturn(Collections.emptyList());
        assertThrows(NotFoundException.class, () -> {
            urlPhoneAlertService.getPhoneByFirestation(1);
        });
    }
    
}
