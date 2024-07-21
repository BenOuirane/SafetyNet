package com.alerts.SafetyNet.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.model.MedicalRecord;
import com.alerts.SafetyNet.repository.MedicalrecordRepository;
import com.alerts.SafetyNet.service.impl.MedicalrecordServiceImpl;

public class MedicalrecordServiceImplTest {
	
	    @Mock
	    private MedicalrecordRepository medicalrecordRepository;
	    @InjectMocks
	    private MedicalrecordServiceImpl medicalrecordService;
	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testGetMedicalRecord() {
	        // Arrange: Create a list of medical records
	        MedicalRecord record1 = new MedicalRecord("John", "Doe", LocalDate.of(1990, 1, 1), List.of("Med1", "Med2"), List.of("Allergy1", "Allergy2"));
	        MedicalRecord record2 = new MedicalRecord("Jane", "Smith", LocalDate.of(1985, 5, 15), List.of("Med3", "Med4"), List.of("Allergy3", "Allergy4"));
	        List<MedicalRecord> expectedRecords = Arrays.asList(record1, record2);
	        // Mock the behavior of the repository
	        when(medicalrecordRepository.getMedicalRecord()).thenReturn(expectedRecords);
	        // Act: Call the getMedicalRecord method
	        List<MedicalRecord> actualRecords = medicalrecordService.getMedicalRecord();
	        // Assert: Verify that the records returned by the service match the expected records
	        assertEquals(expectedRecords, actualRecords);
	    }
	    
	    @Test
	    public void testCreateMedicalRecord() {
	        // Arrange
	        MedicalRecord newRecord = new MedicalRecord("John", "Doe", null, null, null);
	        when(medicalrecordRepository.addMedicalRecord(newRecord)).thenReturn(newRecord);
	        // Act
	        MedicalRecord createdRecord = medicalrecordService.createMedicalRecord(newRecord);
	        // Assert
	        assertEquals(newRecord, createdRecord);
	        verify(medicalrecordRepository).addMedicalRecord(newRecord);
	    }
	    
	    @Test
	    public void testUpdateMedicalRecord() throws NotFoundException {
	        // Arrange	        
	        MedicalRecord updatedRecord = new MedicalRecord("John", "Doe", null, null, null);
	        when(medicalrecordRepository.updateMedicalRecord(updatedRecord)).thenReturn(updatedRecord);
	        // Act
	        MedicalRecord result = medicalrecordService.updateMedicalRecord(updatedRecord);
	        // Assert
	        assertEquals(updatedRecord, result);
	        verify(medicalrecordRepository).updateMedicalRecord(updatedRecord);
	    }
	    
	    @Test
	    public void testDeleteByName_Success() throws NotFoundException {
	        // Arrange
	        String firstName = "John";
	        String lastName = "Doe";
	        // Act
	        medicalrecordService.deleteByName(firstName, lastName);
	        // Assert
	        verify(medicalrecordRepository).deleteMedicalRecordByName(firstName, lastName);
	    }

	    @Test
	    public void testDeleteByName_NotFound() throws NotFoundException {
	        // Arrange
	        String firstName = "Nonexistent";
	        String lastName = "Person";
	        NotFoundException exception = new NotFoundException();
	        doThrow(exception).when(medicalrecordRepository).deleteMedicalRecordByName(firstName, lastName);
	        // Act & Assert
	        assertThrows(NotFoundException.class, () -> {
	            medicalrecordService.deleteByName(firstName, lastName);
	        });
	    }
	    
}
