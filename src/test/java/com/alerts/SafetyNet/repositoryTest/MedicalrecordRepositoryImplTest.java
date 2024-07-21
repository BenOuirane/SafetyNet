package com.alerts.SafetyNet.repositoryTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.model.MedicalRecord;
import com.alerts.SafetyNet.model.Person;
import com.alerts.SafetyNet.repository.impl.MedicalrecordRepositoryImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class MedicalrecordRepositoryImplTest {

    @InjectMocks
    private MedicalrecordRepositoryImpl medicalrecordRepository;
    @Mock
    private List<MedicalRecord> listMedicalRecords;
    @BeforeEach
    public void setUp() {
      //  MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSetListMedicalRecords() {
        // Create some sample MedicalRecord objects
         MedicalRecord record1 = new MedicalRecord("John", "Doe", LocalDate.of(1990, 1, 1), Arrays.asList("med1", "med2"), Arrays.asList("allergy1"));
         MedicalRecord record2 = new MedicalRecord("Jane", "Doe", LocalDate.of(1990, 1, 1), Arrays.asList("med3"), Arrays.asList("allergy2", "allergy3"));
        // Create a list of MedicalRecord objects
        List<MedicalRecord> medicalRecords = Arrays.asList(record1, record2);
        // Set the list using the method to be tested
        medicalrecordRepository.setListMedicalRecords(medicalRecords);
        // Verify that the list was set correctly
        List<MedicalRecord> retrievedRecords = medicalrecordRepository.getMedicalRecord();
        assertEquals(2, retrievedRecords.size());
        assertEquals("John", retrievedRecords.get(0).getFirstName());
        assertEquals("Jane", retrievedRecords.get(1).getFirstName());
    }
    

    @Test
    public void testAddMedicalRecord() {
        // Create a sample MedicalRecord object
        MedicalRecord newRecord = new MedicalRecord("John", "Doe", LocalDate.of(1990, 1, 1), List.of("med1", "med2"), List.of("allergy1"));
        // Add the MedicalRecord using the method to be tested
        MedicalRecord returnedRecord = medicalrecordRepository.addMedicalRecord(newRecord);  
        // Create a list of MedicalRecord objects
        List<MedicalRecord> medicalRecords = Arrays.asList(newRecord);
        // Set the list using the method to be tested
        medicalrecordRepository.setListMedicalRecords(medicalRecords);         
        // Verify that the returned record is the same as the added record
        assertEquals(newRecord, returnedRecord);
        // Verify that the list contains the new record
        List<MedicalRecord> retrievedRecords = medicalrecordRepository.getMedicalRecord();
        assertEquals(1, retrievedRecords.size());
        assertTrue(retrievedRecords.contains(newRecord));
    }
    
    @Test
    public void testGetMedicalRecord() {
        // Create sample MedicalRecord objects
        MedicalRecord record1 = new MedicalRecord("John", "Doe", LocalDate.of(1990, 1, 1), List.of("med1", "med2"), List.of("allergy1"));
        MedicalRecord record2 = new MedicalRecord("Jane", "Doe", LocalDate.of(1990, 1, 1), List.of("med3"), List.of("allergy2"));
        // Add records to the list
        medicalrecordRepository.addMedicalRecord(record1);
        medicalrecordRepository.addMedicalRecord(record2);
        // Create a list of MedicalRecord objects
        List<MedicalRecord> medicalRecords = Arrays.asList(record1,record2);
        // Set the list using the method to be tested
        medicalrecordRepository.setListMedicalRecords(medicalRecords); 
        // Retrieve the list using the method to be tested
        List<MedicalRecord> retrievedRecords = medicalrecordRepository.getMedicalRecord();
        // Verify that the retrieved list contains the added records
        assertEquals(2, retrievedRecords.size());
        assertTrue(retrievedRecords.contains(record1));
        assertTrue(retrievedRecords.contains(record2));
    }
    
    @Test
    public void testUpdateMedicalRecord_Success() throws NotFoundException {
        // Arrange: Populate the repository with an initial medical record
        MedicalRecord initialRecord = new MedicalRecord("John", "Doe", LocalDate.of(1990, 1, 1), List.of("Med1", "Med2"), List.of("Allergy1", "Allergy2"));
        List<MedicalRecord> MedicalRecordList = Arrays.asList(initialRecord);
        medicalrecordRepository.setListMedicalRecords(MedicalRecordList);
        // Act: Attempt to update the medical record
        MedicalRecord updatedRecord = new MedicalRecord("John", "Doe", LocalDate.of(1990, 1, 1), List.of("UpdatedMed1", "UpdatedMed2"), List.of("UpdatedAllergy1", "UpdatedAllergy2"));
        MedicalRecord result = medicalrecordRepository.updateMedicalRecord(updatedRecord);
        // Assert: Verify that the update operation succeeds
        assertEquals(updatedRecord.getBirthdate(), result.getBirthdate());
        assertEquals(updatedRecord.getMedications(), result.getMedications());
        assertEquals(updatedRecord.getAllergies(), result.getAllergies());

    }

    @Test
    public void testUpdateMedicalRecord_NotFound() {
        MedicalRecord nonExistingRecord = new MedicalRecord("NonExisting", "Person", LocalDate.of(1990, 1, 1), List.of("med1"), List.of("allergy1"));
        assertThrows(NotFoundException.class, () -> {
            medicalrecordRepository.updateMedicalRecord(nonExistingRecord);
        });
    }    
    
    @Test
    public void testGetMedicalRecordByName_Success() throws NotFoundException {
        // Arrange: Populate the repository with medical records
        MedicalRecord record1 = new MedicalRecord("John", "Doe", LocalDate.of(1990, 01, 01), List.of("Med1", "Med2"), List.of("Allergy1", "Allergy2"));
        MedicalRecord record2 = new MedicalRecord("Jane", "Smith", LocalDate.of(1990, 1, 1), List.of("Med3", "Med4"), List.of("Allergy3", "Allergy4"));      
        List<MedicalRecord> MedicalRecordList = Arrays.asList(record1,record2);
        medicalrecordRepository.setListMedicalRecords(MedicalRecordList);
        // Act: Retrieve a medical record by name
        MedicalRecord result = medicalrecordRepository.getMedicalRecordByName("John", "Doe");
        // Assert: Verify that the retrieved record matches the expected values
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals(List.of("Med1", "Med2"), result.getMedications());
        assertEquals(List.of("Allergy1", "Allergy2"), result.getAllergies());
    }
 
    @Test
    public void testGetMedicalRecordByName_NotFound() {
        // Arrange: Populate the repository with medical records
        MedicalRecord record1 = new MedicalRecord("John", "Doe", LocalDate.of(1990, 01, 01), List.of("Med1", "Med2"), List.of("Allergy1", "Allergy2"));
        medicalrecordRepository.addMedicalRecord(record1);
        // Act & Assert: Attempt to retrieve a non-existing medical record and expect a NotFoundException
        assertThrows(NotFoundException.class, () -> {
            medicalrecordRepository.getMedicalRecordByName("Jane", "Smith");
        });
    }
    
    @Test
    public void testDeleteMedicalRecord_Success() throws NotFoundException {
        // Arrange: Populate the repository with medical records
        MedicalRecord record1 = new MedicalRecord("John", "Doe", LocalDate.of(1990, 1, 1), List.of("Med1", "Med2"), List.of("Allergy1", "Allergy2"));
        MedicalRecord record2 = new MedicalRecord("Jane", "Smith", LocalDate.of(1985, 5, 15), List.of("Med3", "Med4"), List.of("Allergy3", "Allergy4"));
        List<MedicalRecord> medicalRecordList = new ArrayList<>(Arrays.asList(record1, record2));
        medicalrecordRepository.setListMedicalRecords(medicalRecordList);
        // Act: Attempt to delete a medical record
        medicalrecordRepository.deleteMedicalRecord(record1);
        // Assert: Verify that the record is deleted
        assertThrows(NotFoundException.class, () -> {
            medicalrecordRepository.getMedicalRecordByName("John", "Doe");
        });
        assertEquals(1, medicalrecordRepository.getMedicalRecord().size());
        assertEquals(record2, medicalrecordRepository.getMedicalRecord().get(0));
    }
    
    @Test
    public void testDeleteMedicalRecord_NotFound() {
        // Arrange: Populate the repository with medical records
        MedicalRecord record1 = new MedicalRecord("John", "Doe", LocalDate.of(1990, 01, 01), List.of("Med1", "Med2"), List.of("Allergy1", "Allergy2"));
        List<MedicalRecord> medicalRecordList = new ArrayList<>(Arrays.asList(record1));
        medicalrecordRepository.setListMedicalRecords(medicalRecordList);
        // Act & Assert: Attempt to delete a non-existing medical record and expect a NotFoundException
        MedicalRecord nonExistingRecord = new MedicalRecord("Jane", "Smith", LocalDate.of(1990, 01, 01), List.of("Med3", "Med4"), List.of("Allergy3", "Allergy4"));
        assertThrows(NotFoundException.class, () -> {
            medicalrecordRepository.deleteMedicalRecord(nonExistingRecord);
        });
        assertEquals(1, medicalrecordRepository.getMedicalRecord().size());
    }
    
    @Test
    public void testDeleteMedicalRecordByName_Success() throws NotFoundException {
        // Arrange: Populate the repository with medical records
        MedicalRecord record1 = new MedicalRecord("John", "Doe", LocalDate.of(1990, 1, 1), List.of("Med1", "Med2"), List.of("Allergy1", "Allergy2"));
        MedicalRecord record2 = new MedicalRecord("Jane", "Smith", LocalDate.of(1985, 5, 15), List.of("Med3", "Med4"), List.of("Allergy3", "Allergy4"));
        List<MedicalRecord> medicalRecordList = new ArrayList<>(Arrays.asList(record1, record2));
        medicalrecordRepository.setListMedicalRecords(medicalRecordList);
        // Act: Attempt to delete a medical record by name
        medicalrecordRepository.deleteMedicalRecordByName("John", "Doe");
        // Assert: Verify that the record is deleted
        assertThrows(NotFoundException.class, () -> {
            medicalrecordRepository.getMedicalRecordByName("John", "Doe");
        });
        assertEquals(1, medicalrecordRepository.getMedicalRecord().size());
        assertEquals(record2, medicalrecordRepository.getMedicalRecord().get(0));
    }

    @Test
    public void testDeleteMedicalRecordByName_NotFound() {
        // Arrange: Populate the repository with medical records
        MedicalRecord record1 = new MedicalRecord("John", "Doe", LocalDate.of(1990, 1, 1), List.of("Med1", "Med2"), List.of("Allergy1", "Allergy2"));
        MedicalRecord record2 = new MedicalRecord("Jane", "Smith", LocalDate.of(1985, 5, 15), List.of("Med3", "Med4"), List.of("Allergy3", "Allergy4"));
        List<MedicalRecord> medicalRecordList = new ArrayList<>(Arrays.asList(record1, record2));
        medicalrecordRepository.setListMedicalRecords(medicalRecordList);
        // Act & Assert: Attempt to delete a non-existent medical record and expect NotFoundException
        assertThrows(NotFoundException.class, () -> {
            medicalrecordRepository.deleteMedicalRecordByName("Alice", "Johnson");
        });
    }
    
    @Test
    public void testCalculateAge() {
        LocalDate birthdate = LocalDate.of(1990, 6, 1);
        int expectedAge = 34; // Assuming the current date is 2024-06-01
        int actualAge = medicalrecordRepository.calculateAge(birthdate);
        assertEquals(expectedAge, actualAge);
    }
    
    @Test
    public void testCalculateAgeDifferentYear() {
        LocalDate birthdate = LocalDate.of(2000, 1, 1);
        int expectedAge = 24; // Assuming the current date is 2024-06-01     
        int actualAge = medicalrecordRepository.calculateAge(birthdate);    
        assertEquals(expectedAge, actualAge);
    }
    
    @Test
    public void testCalculateAgeLeapYear() {    
        LocalDate birthdate = LocalDate.of(2000, 2, 29); // Leap year date
        int expectedAge = 24; // Assuming the current date is 2024-06-01   
        int actualAge = medicalrecordRepository.calculateAge(birthdate);  
        assertEquals(expectedAge, actualAge);
    }

    @Test
    public void testHavePersonAge() throws NotFoundException {
    	MedicalRecord record1 = new MedicalRecord("John", "Doe", LocalDate.of(1990, 1, 1), List.of("Med1", "Med2"), List.of("Allergy1", "Allergy2"));
        MedicalRecord record2 = new MedicalRecord("Jane", "Smith", LocalDate.of(1985, 5, 15), List.of("Med3", "Med4"), List.of("Allergy3", "Allergy4"));
        List<MedicalRecord> medicalRecordList = new ArrayList<>(Arrays.asList(record1, record2));
        medicalrecordRepository.setListMedicalRecords(medicalRecordList);
        Person person = new Person("John", "Doe"," "," "," "," "," ");
        int expectedAge = 34; // Assuming the current date is 2024-06-01
        int actualAge = medicalrecordRepository.havePersonAge(person);
        assertEquals(expectedAge, actualAge);
    }

    @Test
    public void testHavePersonAgePersonNotFound() {
        Person person = new Person("Nonexistent", "Person"," "," "," "," "," ");
        assertThrows(NotFoundException.class, () -> {
        	medicalrecordRepository.havePersonAge(person);
        });
    }
   
    @Test
    public void testIfAdult_AdultPerson() {
    	MedicalRecord record1 = new MedicalRecord("John", "Doe", LocalDate.of(2023, 1, 1), List.of("Med1", "Med2"), List.of("Allergy1", "Allergy2"));
        MedicalRecord record2 = new MedicalRecord("Jane", "Smith", LocalDate.of(1995, 5, 15), List.of("Med3", "Med4"), List.of("Allergy3", "Allergy4"));
        List<MedicalRecord> medicalRecordList = new ArrayList<>(Arrays.asList(record1, record2));
        medicalrecordRepository.setListMedicalRecords(medicalRecordList);
        Person adult = new Person("John", "Doe"," "," "," "," "," ");
        assertFalse(medicalrecordRepository.ifAdult(adult));
        Person child = new Person("Jane", "Smith"," "," "," "," "," ");
        assertTrue(medicalrecordRepository.ifAdult(child));
    }

    @Test
    public void testIfAdultPersonNotFound() {
        Person unknown = new Person("Nonexistent", "Person"," "," "," "," "," ");
        assertFalse(medicalrecordRepository.ifAdult(unknown));
    }
    
    @Test
    public void testIfChild() {
    	MedicalRecord record1 = new MedicalRecord("John", "Doe", LocalDate.of(1990, 1, 1), List.of("Med1", "Med2"), List.of("Allergy1", "Allergy2"));
        MedicalRecord record2 = new MedicalRecord("Jane", "Smith", LocalDate.of(2022, 5, 15), List.of("Med3", "Med4"), List.of("Allergy3", "Allergy4"));
        List<MedicalRecord> medicalRecordList = new ArrayList<>(Arrays.asList(record1, record2));
        medicalrecordRepository.setListMedicalRecords(medicalRecordList);
        Person adult = new Person("John", "Doe"," "," "," "," "," ");
        assertFalse(medicalrecordRepository.ifChild(adult));
        Person child = new Person("Jane", "Smith"," "," "," "," "," ");
        assertTrue(medicalrecordRepository.ifChild(child));
    }

    @Test
    public void testIfChildPersonNotFound() {
        Person unknown = new Person("Nonexistent", "Person"," "," "," "," "," ");
        assertFalse(medicalrecordRepository.ifChild(unknown));
    } 
    
    @Test
    public void testGetMedicationsByLastName_Success() throws NotFoundException {
        MedicalRecord record = new MedicalRecord("John", "Doe", LocalDate.of(1990, 1, 1), List.of("med1", "med2"), List.of("Allergy1", "Allergy2"));
        when(listMedicalRecords.stream())
                .thenReturn(Arrays.asList(record).stream());
        List<String> medications = medicalrecordRepository.getMedicationsByLastName("Doe");
        assertEquals(Arrays.asList("med1", "med2"), medications);
    }

    @Test
    public void testGetMedicationsByLastName_NotFound() {
        when(listMedicalRecords.stream()).thenReturn(Stream.empty());
        assertThrows(NotFoundException.class, () -> medicalrecordRepository.getMedicationsByLastName("Doe"));
    }
    
    @Test
    public void testGetAllergiesByLastName_Success() throws NotFoundException {
        MedicalRecord record = new MedicalRecord("John", "Doe", LocalDate.of(1990, 1, 1), List.of("med1", "med2"), List.of("allergy1", "allergy2"));
        when(listMedicalRecords.stream()).thenReturn(Stream.of(record));
        List<String> allergies = medicalrecordRepository.getAllergiesByLastName("Doe");
        assertEquals(Arrays.asList("allergy1", "allergy2"), allergies);
    }

    @Test
    public void testGetAllergiesByLastName_NotFound() {
        when(listMedicalRecords.stream()).thenReturn(Stream.empty());
        assertThrows(NotFoundException.class, () -> medicalrecordRepository.getAllergiesByLastName("Doe"));
    }
    
      
}
