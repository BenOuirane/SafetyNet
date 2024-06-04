package com.alerts.SafetyNet.controllerUrlTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.alerts.SafetyNet.dto.FireDto;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.url.UrlFireService;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlFireControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    @MockBean    
    private UrlFireService urlFireService;
    

    @Test
    public void testGetFireController_Success200OK() throws Exception {
        // Arrange
        String address = "123 Main St";
        FireDto fireDto = new FireDto(1, "John", "123-456-7890", 30, Arrays.asList("Med1", "Med2"), Arrays.asList("Allergy1", "Allergy2"));
        List<FireDto> fireDtoList = Arrays.asList(fireDto);
        when(urlFireService.getFireService(any(String.class))).thenReturn(fireDtoList);
        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/fire")
                .param("address", address)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testGetPhoneByFirestation_NotFound404() throws Exception {
        String address = "Unknown Address";
        when(urlFireService.getFireService(any(String.class))).thenThrow(new NotFoundException());
        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/fire")
                .param("address", address)
                .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetPhoneByFirestation_InternalServerError500() throws Exception {
        String address = "Valid Address";
        when(urlFireService.getFireService(any(String.class))).thenThrow(new RuntimeException("Internal error"));
        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/fire")
                .param("address", address)
                .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
    
}
