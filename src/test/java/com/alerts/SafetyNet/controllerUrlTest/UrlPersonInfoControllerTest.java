package com.alerts.SafetyNet.controllerUrlTest;

import com.alerts.SafetyNet.controller.url.UrlPersonInfoController;
import com.alerts.SafetyNet.dto.PersonInfoDto;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.url.UrlPersonInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UrlPersonInfoControllerTest {

    @Mock
    private UrlPersonInfoService urlPersonInfoService;
    @InjectMocks
    private UrlPersonInfoController controller;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testGetPersonInfoController_Success200OK() throws Exception {
        // Arrange
        String lastName = "Doe";
        List<PersonInfoDto> expectedResult = new ArrayList<>();
        // Define your expected result here
        // Mock service method
        when(urlPersonInfoService.getPersonInfoService(lastName)).thenReturn(expectedResult);
        // Act & Assert
        mockMvc.perform(get("/personInfo")
                .param("lastName", lastName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testGetPersonInfoController_NotFound404() throws Exception {
        // Arrange
        String lastName = "Smith";
        // Mock service method to throw NotFoundException
        when(urlPersonInfoService.getPersonInfoService(lastName)).thenThrow(new NotFoundException());
        // Act & Assert
        mockMvc.perform(get("/personInfo")
                .param("lastName", lastName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetPersonInfoController_InternalServerError500() throws Exception {
        // Arrange
        String lastName = "Doe";
        // Mock service method to throw an unexpected exception
        when(urlPersonInfoService.getPersonInfoService(lastName)).thenThrow(new RuntimeException("Internal Server Error"));
        // Act & Assert
        mockMvc.perform(get("/personInfo")
                .param("lastName", lastName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

}
