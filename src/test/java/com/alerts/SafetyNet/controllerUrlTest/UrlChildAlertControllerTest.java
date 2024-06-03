package com.alerts.SafetyNet.controllerUrlTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.alerts.SafetyNet.dto.ChildAlertDto;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.url.UrlChildAlertService;
import java.util.Collections;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlChildAlertControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UrlChildAlertService urlChildAlertService;

    @Test
    public void testPersonnesCouvertesCaserne_Success200OK() throws Exception {
        ChildAlertDto childAlertDto = new ChildAlertDto("John", "Doe", 10, Collections.emptyList());
        when(urlChildAlertService.getChildrenIntoAddress("123 Main St"))
                .thenReturn(Collections.singletonList(childAlertDto));
        mockMvc.perform(MockMvcRequestBuilders.get("/childAlert")
                .param("address", "123 Main St"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(10));
    }

    @Test
    public void testPersonnesCouvertesCaserne_NotFound404() throws Exception {
        when(urlChildAlertService.getChildrenIntoAddress("Unknown Address"))
                .thenThrow(new NotFoundException());
        mockMvc.perform(MockMvcRequestBuilders.get("/childAlert")
                .param("address", "Unknown Address"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
