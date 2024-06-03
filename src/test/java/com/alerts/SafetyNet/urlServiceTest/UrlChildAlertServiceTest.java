package com.alerts.SafetyNet.urlServiceTest;

import com.alerts.SafetyNet.dto.ChildAlertDto;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.MedicalrecordRepository;
import com.alerts.SafetyNet.repository.PersonRepository;
import com.alerts.SafetyNet.service.url.UrlChildAlertService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UrlChildAlertServiceTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private MedicalrecordRepository medicalRecordRepository;
    @InjectMocks
    private UrlChildAlertService urlChildAlertService;

    @Test
    public void testGetChildrenIntoAddress_Success() throws NotFoundException {
        String address = "123 Main St";
        Person child = new Person();
        child.setFirstName("John");
        child.setLastName("Doe");
        child.setAddress(address);
        Person adult = new Person();
        adult.setFirstName("Jane");
        adult.setLastName("Doe");
        adult.setAddress(address);
        List<Person> persons = Arrays.asList(child, adult);
        when(personRepository.getPersonsByAddresse(address)).thenReturn(persons);
        when(medicalRecordRepository.ifChild(child)).thenReturn(true);
        when(medicalRecordRepository.ifChild(adult)).thenReturn(false); // Ensure this is set
        when(medicalRecordRepository.ifAdult(adult)).thenReturn(true);
        when(medicalRecordRepository.ifAdult(child)).thenReturn(false); // Ensure this is set
        when(medicalRecordRepository.havePersonAge(child)).thenReturn(10);
        List<ChildAlertDto> result = urlChildAlertService.getChildrenIntoAddress(address);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Doe", result.get(0).getLastName());
        assertEquals(10, result.get(0).getAge());
        assertEquals(1, result.get(0).getFamilyMembers().size());
        assertEquals("Jane", result.get(0).getFamilyMembers().get(0).getFirstName());
    }

    @Test
    public void testGetChildrenIntoAddress_AddressNotFound() throws NotFoundException {
        String address = "123 Main St";
        when(personRepository.getPersonsByAddresse(address)).thenThrow(new NotFoundException());
        assertThrows(NotFoundException.class, () -> {
            urlChildAlertService.getChildrenIntoAddress(address);
        });
    }
    
    @Test
    public void testGetChildrenIntoAddress_NoChildren() throws NotFoundException {
        String address = "123 Main St";
        Person adult = new Person();
        adult.setFirstName("Jane");
        adult.setLastName("Doe");
        adult.setAddress(address);
        List<Person> persons = Collections.singletonList(adult);
        when(personRepository.getPersonsByAddresse(address)).thenReturn(persons);
        when(medicalRecordRepository.ifChild(adult)).thenReturn(false);
        when(medicalRecordRepository.ifAdult(adult)).thenReturn(true);
        List<ChildAlertDto> result = urlChildAlertService.getChildrenIntoAddress(address);
        assertEquals(0, result.size());
    }
    
}
