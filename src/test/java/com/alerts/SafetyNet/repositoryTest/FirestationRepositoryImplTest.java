package com.alerts.SafetyNet.repositoryTest;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.alerts.SafetyNet.entity.Firestation;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.impl.FirestationRepositoryImpl;


public class FirestationRepositoryImplTest {

    private FirestationRepositoryImpl firestationRepository;
    

    @BeforeEach
    public void setup() {
        firestationRepository = new FirestationRepositoryImpl();
    }

    @Test
    public void testSetListFirestations() {
        // Arrange
        Firestation firestation1 = new Firestation("Address1", 1);
        Firestation firestation2 = new Firestation("Address2", 2);
        List<Firestation> firestationList = Arrays.asList(firestation1, firestation2);

        // Act
        firestationRepository.setListFirestations(firestationList);

        // Assert
        assertNotNull(firestationRepository.getFirestations());
        assertEquals(2, firestationRepository.getFirestations().size());
        assertEquals(firestation1, firestationRepository.getFirestations().get(0));
        assertEquals(firestation2, firestationRepository.getFirestations().get(1));
    }
    
    @Test
    public void testAddFirestation() {
        // Arrange
        Firestation firestation = new Firestation("Address1", 1);

        // Act
        Firestation result = firestationRepository.addFirestation(firestation);

        // Assert
        List<Firestation> firestations = firestationRepository.getFirestations();
        assertEquals(1, firestations.size());
        assertTrue(firestations.contains(firestation));
        assertEquals(firestation, result);
    }
    
    @Test
    public void testGetFirestations() {
        // Arrange
        Firestation firestation1 = new Firestation("Address1", 1);
        Firestation firestation2 = new Firestation("Address2", 2);
        List<Firestation> expectedList = Arrays.asList(firestation1, firestation2);
        firestationRepository.setListFirestations(expectedList);

        // Act
        List<Firestation> result = firestationRepository.getFirestations();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedList, result);
    }
    
    @Test
    public void testUpdateFirestationSuccess() throws NotFoundException {
        // Arrange
        Firestation firestation1 = new Firestation("Address1", 1);
        Firestation firestation2 = new Firestation("Address2", 2);
        List<Firestation> firestationList = Arrays.asList(firestation1, firestation2);
        firestationRepository.setListFirestations(firestationList);
        
        Firestation updatedFirestation = new Firestation("Address1", 3);

        // Act
        Firestation result = firestationRepository.updateFirestation(updatedFirestation);

        // Assert
        assertEquals(3, firestationRepository.getFirestations().get(0).getStation());
        assertEquals(updatedFirestation, result);
    }

    @Test
    public void testUpdateFirestationNotFound() {
        // Arrange
        Firestation firestation1 = new Firestation("Address1", 1);
        List<Firestation> firestationList = Arrays.asList(firestation1);
        firestationRepository.setListFirestations(firestationList);
        
        Firestation nonExistentFirestation = new Firestation("Address2", 2);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            firestationRepository.updateFirestation(nonExistentFirestation);
        });
    }
    @Test
    public void testDeleteFirestationSuccess() throws NotFoundException {
        // Arrange
        Firestation firestation1 = new Firestation("Address1", 1);
        Firestation firestation2 = new Firestation("Address2", 2);
        List<Firestation> firestationList = new ArrayList<>(List.of(firestation1, firestation2));
        firestationRepository.setListFirestations(firestationList);

        // Act
        firestationRepository.deleteFirestation(firestation1);

        // Assert
        List<Firestation> result = firestationRepository.getFirestations();
        assertEquals(1, result.size());
        assertTrue(!result.contains(firestation1));
        assertTrue(result.contains(firestation2));
    }

    
    @Test
    public void testDeleteFirestationNotFound() {
        // Arrange
        Firestation firestation1 = new Firestation("Address1", 1);
        List<Firestation> firestationList = Arrays.asList(firestation1);
        firestationRepository.setListFirestations(firestationList);

        Firestation nonExistentFirestation = new Firestation("Address2", 2);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            firestationRepository.deleteFirestation(nonExistentFirestation);
        });
    }
    

    @Test
    public void testDeleteFirestationsByAddressSuccess() throws NotFoundException {
        // Arrange
        Firestation firestation1 = new Firestation("Address1", 1);
        Firestation firestation2 = new Firestation("Address1", 2);
        Firestation firestation3 = new Firestation("Address2", 3);
        List<Firestation> firestationList = new ArrayList<>(List.of(firestation1, firestation2, firestation3));
        firestationRepository.setListFirestations(firestationList);

        // Act
        firestationRepository.deleteFirestationsByAddress("Address1");

        // Assert
        List<Firestation> result = firestationRepository.getFirestations();
        assertEquals(1, result.size());
        assertTrue(result.contains(firestation3));
        assertTrue(!result.contains(firestation1));
        assertTrue(!result.contains(firestation2));
    }

    @Test
    public void testDeleteFirestationsByAddressNotFound() {
        // Arrange
        Firestation firestation1 = new Firestation("Address1", 1);
        Firestation firestation2 = new Firestation("Address2", 2);
        List<Firestation> firestationList = new ArrayList<>(List.of(firestation1, firestation2));
        firestationRepository.setListFirestations(firestationList);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            firestationRepository.deleteFirestationsByAddress("NonExistentAddress");
        });
    }
    
    @Test
    public void testDeleteFirestationsByStationNumberSuccess() throws NotFoundException {
        // Arrange
        Firestation firestation1 = new Firestation("Address1", 1);
        Firestation firestation2 = new Firestation("Address2", 1);
        Firestation firestation3 = new Firestation("Address3", 2);
        List<Firestation> firestationList = new ArrayList<>(List.of(firestation1, firestation2, firestation3));
        firestationRepository.setListFirestations(firestationList);

        // Act
        firestationRepository.deleteFirestationsByStationNumber(1);

        // Assert
        List<Firestation> result = firestationRepository.getFirestations();
        assertEquals(1, result.size());
        assertTrue(result.contains(firestation3));
        assertTrue(!result.contains(firestation1));
        assertTrue(!result.contains(firestation2));
    }

    @Test
    public void testDeleteFirestationsByStationNumberNotFound() {
        // Arrange
        Firestation firestation1 = new Firestation("Address1", 1);
        Firestation firestation2 = new Firestation("Address2", 2);
        List<Firestation> firestationList = new ArrayList<>(List.of(firestation1, firestation2));
        firestationRepository.setListFirestations(firestationList);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            firestationRepository.deleteFirestationsByStationNumber(3);
        });
    }
    
    
    @Test
    public void testGetFirestationsByAddressSuccess() throws NotFoundException {
        // Arrange
        Firestation firestation1 = new Firestation("Address1", 1);
        Firestation firestation2 = new Firestation("Address1", 2);
        Firestation firestation3 = new Firestation("Address2", 3);
        List<Firestation> firestationList = new ArrayList<>(List.of(firestation1, firestation2, firestation3));
        firestationRepository.setListFirestations(firestationList);

        // Act
        List<Firestation> result = firestationRepository.getFirestationsByAddress("Address1");

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(firestation1));
        assertTrue(result.contains(firestation2));
    }

    @Test
    public void testGetFirestationsByAddressNotFound() {
        // Arrange
        Firestation firestation1 = new Firestation("Address1", 1);
        Firestation firestation2 = new Firestation("Address2", 2);
        List<Firestation> firestationList = new ArrayList<>(List.of(firestation1, firestation2));
        firestationRepository.setListFirestations(firestationList);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            firestationRepository.getFirestationsByAddress("NonExistentAddress");
        });
    }

    @Test
    public void testGetFirestationsByStationNumberSuccess() throws NotFoundException {
        // Arrange
        Firestation firestation1 = new Firestation("Address1", 1);
        Firestation firestation2 = new Firestation("Address2", 1);
        Firestation firestation3 = new Firestation("Address3", 2);
        List<Firestation> firestationList = new ArrayList<>(List.of(firestation1, firestation2, firestation3));
        firestationRepository.setListFirestations(firestationList);

        // Act
        List<Firestation> result = firestationRepository.getFirestationsByStationNumber(1);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(firestation1));
        assertTrue(result.contains(firestation2));
    }

    @Test
    public void testGetFirestationsByStationNumberNotFound() {
        // Arrange
        Firestation firestation1 = new Firestation("Address1", 1);
        Firestation firestation2 = new Firestation("Address2", 2);
        List<Firestation> firestationList = new ArrayList<>(List.of(firestation1, firestation2));
        firestationRepository.setListFirestations(firestationList);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            firestationRepository.getFirestationsByStationNumber(3);
        });
    }

    @Test
    public void testGetFirestationAddresses() throws NotFoundException {
        List<Firestation> firestations = Arrays.asList(
            new Firestation("123 Main St", 1),
            new Firestation("456 Elm St", 1)
        );
        firestationRepository.setListFirestations(firestations);

        List<String> addresses = firestationRepository.getFirestationAddresses(1);
        assertEquals(Arrays.asList("123 Main St", "456 Elm St"), addresses);
    }

    @Test
    public void testGetFirestationAddresses_NotFound() {

        assertThrows(NotFoundException.class, () -> {
        	firestationRepository.getFirestationAddresses(1);
        });
    }
    
    
    
    
    
}
