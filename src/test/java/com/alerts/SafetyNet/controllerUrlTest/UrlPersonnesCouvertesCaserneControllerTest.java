package com.alerts.SafetyNet.controllerUrlTest;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.alerts.SafetyNet.dto.PersonDto;
import com.alerts.SafetyNet.dto.UrlPersonnesCouvertesCaserneDTO;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.url.UrlPersonnesCouvertesCaserneService;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlPersonnesCouvertesCaserneControllerTest {
	
	    @Autowired
	    private MockMvc mockMvc;
	    @MockBean
	    private UrlPersonnesCouvertesCaserneService urlPersonnesCouvertesCaserneService;

	    @Test
	    public void testPersonnesCouvertesCaserne_Success() throws Exception {
	        UrlPersonnesCouvertesCaserneDTO dto = new UrlPersonnesCouvertesCaserneDTO();
	        dto.setAdultsNumber(1);
	        dto.setChildrenNumber(1);
	        dto.setPersons(List.of(new PersonDto("John", "Doe", "", "", "", "", ""), new PersonDto("Jane", "Doe", "", "", "", "", "")));
	        when(urlPersonnesCouvertesCaserneService.urlPersonnesCouvertesCaserneService(anyInt())).thenReturn(dto);
	        mockMvc.perform(get("/covergePerson/get")
	                .param("numberOfStation", "1")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.adultsNumber").value(1))
	                .andExpect(jsonPath("$.childrenNumber").value(1))
	                .andExpect(jsonPath("$.persons[0].firstName").value("John"))
	                .andExpect(jsonPath("$.persons[1].firstName").value("Jane"));
	    }

	    @Test
	    public void testPersonnesCouvertesCaserne_NotFound() throws Exception {
	        when(urlPersonnesCouvertesCaserneService.urlPersonnesCouvertesCaserneService(anyInt())).thenThrow(new NotFoundException());
	        mockMvc.perform(get("/covergePerson/get")
	                .param("numberOfStation", "1")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isNotFound());
	    }
}
