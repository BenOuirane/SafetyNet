package com.alerts.SafetyNet.serviceTest;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.alerts.SafetyNet.entity.Firestation;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.impl.FirestationRepositoryImpl;
import com.alerts.SafetyNet.service.impl.FirestationServiceImpl;

public class FirestationServiceImplTest {

    @Mock
    private FirestationRepositoryImpl firestationRepository;
    @InjectMocks
    private FirestationServiceImpl firestationService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetFirestation() {
        // Arrange
        Firestation firestation1 = new Firestation("Address1", 1);
        Firestation firestation2 = new Firestation("Address2", 2);
        List<Firestation> firestationList = new ArrayList<>(List.of(firestation1, firestation2));
        when(firestationRepository.getFirestations()).thenReturn(firestationList);
        // Act
        List<Firestation> result = firestationService.getFirestation();
        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(firestation1));
        assertTrue(result.contains(firestation2));
    }
    
    @Test
    public void testCreateFirestations() {
        // Arrange
        Firestation firestationToAdd = new Firestation("Test Address", 1);
        Firestation addedFirestation = new Firestation("Test Address", 1);
        when(firestationRepository.addFirestation(firestationToAdd)).thenReturn(addedFirestation);
        // Act
        Firestation result = firestationService.createFirestations(firestationToAdd);
        // Assert
        assertEquals(addedFirestation, result);
        verify(firestationRepository).addFirestation(firestationToAdd);
    }
    
    @Test
    public void testUpdateFirestation_Success() throws NotFoundException {
        // Arrange
        Firestation existingFirestation = new Firestation("Existing Address", 1);
        Firestation updatedFirestation = new Firestation("Updated Address", 2);
        when(firestationRepository.updateFirestation(existingFirestation)).thenReturn(updatedFirestation);
        // Act
        Firestation result = firestationService.updateFirestation(existingFirestation);
        // Assert
        assertEquals(updatedFirestation, result);
        verify(firestationRepository).updateFirestation(existingFirestation);
    }

    @Test
    public void testUpdateFirestation_NotFoundException() throws NotFoundException {
        // Arrange
        Firestation nonExistingFirestation = new Firestation("Non-existing Address", 1);
        when(firestationRepository.updateFirestation(nonExistingFirestation)).thenThrow(NotFoundException.class);
        // Act and Assert
        assertThrows(NotFoundException.class, () -> firestationService.updateFirestation(nonExistingFirestation));
        verify(firestationRepository).updateFirestation(nonExistingFirestation);
    }
    
    @Test
    public void testDeleteFirestation_Success() throws NotFoundException {
        // Arrange
        Firestation existingFirestation = new Firestation("Existing Address", 1);
        // Act
        firestationService.deleteFirestation(existingFirestation);
        // Assert
        verify(firestationRepository).deleteFirestation(existingFirestation);
    }

    @Test
    public void testDeleteFirestation_NotFoundException() throws NotFoundException {
        // Arrange
        Firestation nonExistingFirestation = new Firestation("Non-existing Address", 1);
        // Stubbing the repository to throw NotFoundException
        doThrow(NotFoundException.class).when(firestationRepository).deleteFirestation(nonExistingFirestation);
        // Act and Assert
        assertThrows(NotFoundException.class, () -> firestationService.deleteFirestation(nonExistingFirestation));
        verify(firestationRepository).deleteFirestation(nonExistingFirestation);
    }
    
    @Test
    public void testDeleteFirestationsByAddress() throws NotFoundException {
        // Arrange
        String address = "Test Address";
        // Act
        firestationService.deleteFirestationsByAddress(address);
        // Assert
        verify(firestationRepository).deleteFirestationsByAddress(address);
    }

    @Test
    public void testDeleteFirestationsByAddress_NotFoundException() throws NotFoundException {
        // Arrange
        String nonExistingAddress = "Non-existing Address";
        // Stubbing the repository method to throw NotFoundException when called with non-existing address
        doThrow(NotFoundException.class).when(firestationRepository).deleteFirestationsByAddress(nonExistingAddress);
        // Act and Assert
        assertThrows(NotFoundException.class, () -> firestationService.deleteFirestationsByAddress(nonExistingAddress));
        verify(firestationRepository).deleteFirestationsByAddress(nonExistingAddress);
    }
    
    @Test
    public void testDeleteFirestationsByStationNumber() throws NotFoundException {
        // Arrange
        Integer stationNumber = 123;
        // Act
        firestationService.deleteFirestationsByStationNumber(stationNumber);
        // Assert
        verify(firestationRepository).deleteFirestationsByStationNumber(stationNumber);
    }

    @Test
    public void testDeleteFirestationsByStationNumber_NotFoundException() throws NotFoundException {
        // Arrange
        Integer nonExistingStationNumber = 999;
        // Stubbing the repository to throw NotFoundException
        doThrow(NotFoundException.class).when(firestationRepository).deleteFirestationsByStationNumber(nonExistingStationNumber);
        // Act and Assert
        assertThrows(NotFoundException.class, () -> firestationService.deleteFirestationsByStationNumber(nonExistingStationNumber));
        verify(firestationRepository).deleteFirestationsByStationNumber(nonExistingStationNumber);
    }
    
}
