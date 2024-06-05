package com.alerts.SafetyNet.controllerUrlTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.alerts.SafetyNet.controller.url.UrlFloodStationsController;
import com.alerts.SafetyNet.dto.FloodStationsDto;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.url.UrlFloodStationsService;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(UrlFloodStationsController.class)
@AutoConfigureMockMvc
class UrlFloodStationsControllerTest {

	@Autowired
    private MockMvc mockMvc;
    @MockBean
    private UrlFloodStationsService urlFloodStationsService;

    @Test
    void testGetFloodStationsController_Success() throws Exception {
        List<FloodStationsDto> expectedResult = Arrays.asList(/* define expected results */);
        when(urlFloodStationsService.getFloodStationsService(anyList())).thenReturn(expectedResult);

        mockMvc.perform(get("/flood/stations")
                .param("stations", "1", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
      
    }

    @Test
    void testGetFloodStationsController_NotFound() throws Exception {
        when(urlFloodStationsService.getFloodStationsService(anyList())).thenThrow(new NotFoundException());

        mockMvc.perform(get("/flood/stations")
                .param("stations", "1", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetFloodStationsController_InternalServerError() throws Exception {
        when(urlFloodStationsService.getFloodStationsService(anyList())).thenThrow(new RuntimeException("Internal Server Error"));

        mockMvc.perform(get("/flood/stations")
                .param("stations", "1", "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

}
