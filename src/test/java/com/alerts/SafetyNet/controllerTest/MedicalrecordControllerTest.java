package com.alerts.SafetyNet.controllerTest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.alerts.SafetyNet.controller.MedicalrecordController;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.model.MedicalRecord;
import com.alerts.SafetyNet.service.impl.MedicalrecordServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MedicalrecordControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
    @MockBean
    private MedicalrecordServiceImpl medicalrecordService; 
    @InjectMocks
    private MedicalrecordController medicalrecordController;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
    	MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(medicalrecordController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.findAndRegisterModules();
    }

    @Test
    public void testGetMedicalrecords() throws Exception {
        // Arrange
        MedicalRecord record1 = new MedicalRecord("John", "Doe", LocalDate.of(1990, 1, 1), Arrays.asList("med1"), Arrays.asList("allergy1"));
        MedicalRecord record2 = new MedicalRecord("Jane", "Doe", LocalDate.of(1992, 2, 2), Arrays.asList("med2"), Arrays.asList("allergy2"));
        List<MedicalRecord> records = Arrays.asList(record1, record2);
        when(medicalrecordService.getMedicalRecord()).thenReturn(records);
        // Act & Assert
        mockMvc.perform(get("/medicalrecord/getMedicalrecords"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].firstName", is(record1.getFirstName())))
               .andExpect(jsonPath("$[0].lastName", is(record1.getLastName())))
               .andExpect(jsonPath("$[0].birthdate", is("01/01/1990")))
               .andExpect(jsonPath("$[0].medications[0]", is(record1.getMedications().get(0))))
               .andExpect(jsonPath("$[0].allergies[0]", is(record1.getAllergies().get(0))))
               .andExpect(jsonPath("$[1].firstName", is(record2.getFirstName())))
               .andExpect(jsonPath("$[1].lastName", is(record2.getLastName())))
               .andExpect(jsonPath("$[1].birthdate", is("02/02/1992")))
               .andExpect(jsonPath("$[1].medications[0]", is(record2.getMedications().get(0))))
               .andExpect(jsonPath("$[1].allergies[0]", is(record2.getAllergies().get(0))));
    }
    
    @Test
    public void testCreateMedicalrecords_shouldGive200Ok() throws Exception {
        MedicalRecord newRecord = new MedicalRecord();
        newRecord.setFirstName("John");
        newRecord.setLastName("Doe");
        newRecord.setBirthdate( LocalDate.of(1990, 1, 1));
        newRecord.setMedications(Arrays.asList("med1"));
        newRecord.setAllergies(Arrays.asList("allergy1"));
        @SuppressWarnings("unused")
		MvcResult result = mockMvc.perform(post("/medicalrecord/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newRecord)))
                .andExpect(status().isOk())
                .andReturn();
    }
    
    
    @Test
    public void testCreateMedicalrecordsError_shouldGive500InternalServerError() throws Exception {
    	 MedicalRecord invalidRecord = new MedicalRecord();
         // Mock the service to throw an exception
         when(medicalrecordService.createMedicalRecord(any(MedicalRecord.class))).thenThrow(new RuntimeException("Simulated service exception"));

         mockMvc.perform(post("/medicalrecord/post")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(invalidRecord)))
                 .andExpect(status().isInternalServerError());
    }
   
    @Test
    public void testUpdateMedicalrecordNotFound_shouldGive404NotFound() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        // Mock the service to throw a NotFoundException
        when(medicalrecordService.updateMedicalRecord(any(MedicalRecord.class))).thenThrow(new NotFoundException());
        mockMvc.perform(put("/medicalrecord/put")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateMedicalrecordError_shouldGive500InternalServerError() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        // Mock the service to throw a general exception
        when(medicalrecordService.updateMedicalRecord(any(MedicalRecord.class))).thenThrow(new RuntimeException("Simulated service exception"));
        mockMvc.perform(put("/medicalrecord/put")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().isInternalServerError());
    }

    
    @Test
    public void testUpdateMedicalrecordSuccess_shouldGive200OK() throws Exception {
        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName("John");
        medicalRecord.setLastName("Doe");
        // Set other fields as necessary
        MedicalRecord updatedRecord = new MedicalRecord();
        updatedRecord.setFirstName("John");
        updatedRecord.setLastName("Doe");
        // Set other fields as necessary
        // Mock the service to return the updated record
        when(medicalrecordService.updateMedicalRecord(medicalRecord)).thenReturn(updatedRecord);
        @SuppressWarnings("unused")
		MvcResult result = mockMvc.perform(put("/medicalrecord/put")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(medicalRecord)))
                .andExpect(status().isOk())
                .andReturn();
    }
    
    @Test
    public void testDeletePersonSuccess_shouldGive200OK() throws Exception {
        doNothing().when(medicalrecordService).deleteByName("John", "Doe");
        mockMvc.perform(delete("/medicalrecord/delete")
                .param("firstName", "John")
                .param("lastName", "Doe"))
                .andExpect(status().isOk())
                .andExpect(content().string("The MedicalRecord has been succesfully deleted"));
    }
    
    @Test
    public void testDeletePersonNotFound_shouldGive404NotFound() throws Exception {
        doThrow(new NotFoundException()).when(medicalrecordService).deleteByName("John", "Doe");
        mockMvc.perform(delete("/medicalrecord/delete")
                .param("firstName", "John")
                .param("lastName", "Doe"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("MedicalRecord not found"));
    }

    @Test
    public void testDeletePersonError_shouldGive500InternalServerError() throws Exception {
        doThrow(new RuntimeException("Unexpected error")).when(medicalrecordService).deleteByName("John", "Doe");
        mockMvc.perform(delete("/medicalrecord/delete")
                .param("firstName", "John")
                .param("lastName", "Doe"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("An error occurred while deleting the MedicalRecord"));
    }    

}
