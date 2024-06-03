package com.alerts.SafetyNet.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.alerts.SafetyNet.controller.FirestationController;
import com.alerts.SafetyNet.entity.Firestation;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.impl.FirestationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FirestationServiceImpl firestationService;
    @InjectMocks
    private FirestationController firestationController;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetFirestations_shouldGive200Ok() throws Exception {
        // Arrange
        Firestation firestation1 = new Firestation("123 Main St", 1);
        Firestation firestation2 = new Firestation("456 Elm St", 2);
        List<Firestation> expectedFirestations = Arrays.asList(firestation1, firestation2);
        when(firestationService.getFirestation()).thenReturn(expectedFirestations);
        // Act & Assert
        MvcResult result = mockMvc.perform(get("/firestation/getFirestations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        JSONArray jsonArray = new JSONArray(content);
        // Assert the size of the returned JSON array
        assertEquals(2, jsonArray.length());
        // Assert the content of each JSON object
        assertEquals(1, jsonArray.getJSONObject(0).getInt("station"));
        assertEquals("123 Main St", jsonArray.getJSONObject(0).getString("address"));
        assertEquals(2, jsonArray.getJSONObject(1).getInt("station"));
        assertEquals("456 Elm St", jsonArray.getJSONObject(1).getString("address"));
    }
    
    @Test
    public void testCreateFirestationSuccess_shouldGive200Ok() throws Exception {
    	// Arrange
        Firestation firestation = new Firestation("123 Main St", 1);
        when(firestationService.createFirestations(firestation)).thenReturn(firestation);
        // Act & Assert
        mockMvc.perform(post("/firestation/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(firestation)))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testCreateFirestationException_shouldGive500InternalServerError() throws Exception {
    	// Arrange
        Firestation firestation = new Firestation("123 Main St", 1);
        when(firestationService.createFirestations(any(Firestation.class))).thenThrow(new RuntimeException("Service exception"));
        // Act & Assert
        mockMvc.perform(post("/firestation/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(firestation)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Internal Server Error"));
    }
    
    @Test
    public void testUpdatePersonSuccess_shouldGive200Ok() throws Exception {
    	// Create an existing Firestation object
        Firestation firestation = new Firestation();
        firestation.setAddress("Test Station");
        // Simulate the updated data
        String updatedAddress = "Updated Station";
        // Mock the service to update the Firestation object
        when(firestationService.updateFirestation(any(Firestation.class))).thenAnswer(invocation -> {
        // Update the existing Firestation object with the simulated updated data
            firestation.setAddress(updatedAddress);
            return firestation; // Return the updated Firestation object
        });
        // Perform the PUT request to update the Firestation
        mockMvc.perform(put("/firestation/put")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(firestation)))
                .andExpect(status().isOk())
                // Assert that the updated address is returned in the response
                .andExpect(jsonPath("$.address").value(updatedAddress));
    }
    
    @Test
    public void testUpdatePersonNotFoundException_shouldGive404NotFound() throws Exception {
    	Firestation firestation = new Firestation();
        firestation.setAddress("Test Station");
        // Configure the mock to throw a NotFoundException when updateFirestation is called with the provided Firestation object
        doThrow(new NotFoundException()).when(firestationService).updateFirestation(any(Firestation.class));
        // Perform the PUT request
            mockMvc.perform(put("/firestation/put")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(firestation)))
                    .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdatePersonGeneralException_ShouldReturn500InternalServerError() throws Exception {
        Firestation firestation = new Firestation();
        firestation.setAddress("Test Station");
        doThrow(new RuntimeException("Unexpected error")).when(firestationService).updateFirestation(any(Firestation.class));
        mockMvc.perform(put("/firestation/put")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(firestation)))
                .andExpect(status().isInternalServerError());
    }
    
    @Test
    public void testDeleteFirestationsByAddressSuccess_shouldGive200Ok() throws Exception {
        String address = "123 Main St";
        // Mock service method to verify deleteFirestationsByAddress is called with the provided address
        doNothing().when(firestationService).deleteFirestationsByAddress(address);

        mockMvc.perform(delete("/firestation/delete")
                .param("address", address))
                .andExpect(status().isOk())
                .andExpect(content().string("Firestation(s) successfully deleted"));
        // Verify that the service method was called with the provided address
        verify(firestationService, times(1)).deleteFirestationsByAddress(address);
    }
    
    @Test
    public void testDeleteFirestationsByStationNumberSuccess_shouldGive200Ok() throws Exception {
        int stationNumber = 1;
        // Mock service method to verify deleteFirestationsByStationNumber is called with the provided station number
        doNothing().when(firestationService).deleteFirestationsByStationNumber(stationNumber);
        mockMvc.perform(delete("/firestation/delete")
                .param("stationNumber", String.valueOf(stationNumber)))
                .andExpect(status().isOk())
                .andExpect(content().string("Firestation(s) successfully deleted"));
        // Verify that the service method was called with the provided station number
        verify(firestationService, times(1)).deleteFirestationsByStationNumber(stationNumber);
    }

    @Test
    public void testdeleteFirestations_shouldThrowIllegalArgumentException_whenNoParamsProvided() {
        Optional<String> address = Optional.empty();
        Optional<Integer> stationNumber = Optional.empty();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            firestationController.deleteFirestations(address, stationNumber);
        });
        assertNotNull(exception);
        assertEquals("java.lang.IllegalArgumentException", exception.getClass().getName());
    }
    
}
