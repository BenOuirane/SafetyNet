package com.alerts.SafetyNet.controllerUrlTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.alerts.SafetyNet.controller.url.UrlCommunityEmailController;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.url.UrlCommunityEmailService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UrlCommunityEmailController.class)
public class UrlCommunityEmailControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean    
    private UrlCommunityEmailService communityEmailService;
    @InjectMocks
    private UrlCommunityEmailController communityEmailController;

    @Test
    public void testGetCommunityEmailController_Success() throws Exception {
        // Arrange
        String city = "Springfield";
        List<String> emails = new ArrayList<>();
        emails.add("john.doe@example.com");
        emails.add("jane.smith@example.com");
        when(communityEmailService.getCommunityEmailService(city)).thenReturn(emails);
        // Act & Assert
        mockMvc.perform(get("/communityEmail").param("city", city))
                .andExpect(status().isOk());
      
    }

    @Test
    public void testGetCommunityEmailController_NotFound404() throws Exception {
    	String city =  "Unknown Address";
        when(communityEmailService.getCommunityEmailService(any(String.class))).thenThrow(new NotFoundException());
        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/communityEmail")
                .param("city", city)
                .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testGetCommunityEmailController_InternalServerError500() throws Exception {
    	String city =  "Valid Address";
        when(communityEmailService.getCommunityEmailService(any(String.class))).thenThrow(new RuntimeException("Internal error"));
        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/communityEmail")
                .param("city", city)
                .contentType(MediaType.APPLICATION_JSON))
                // Assert
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
    
    
}
