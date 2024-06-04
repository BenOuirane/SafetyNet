package com.alerts.SafetyNet.controllerUrlTest;

import com.alerts.SafetyNet.controller.url.UrlPhoneAlertController;
import com.alerts.SafetyNet.dto.PhoneAlertDto;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.service.url.UrlPhoneAlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UrlPhoneAlertControllerTest {

    @Mock
    private UrlPhoneAlertService urlPhoneAlertService;
    @InjectMocks
    private UrlPhoneAlertController urlPhoneAlertController;
    private List<PhoneAlertDto> phoneAlertDtoList;

    @BeforeEach
    public void setUp() {
        phoneAlertDtoList = List.of(new PhoneAlertDto(List.of("123-456-7890")));
    }

    @Test
    public void testGetPhoneByFirestation_Success200() throws NotFoundException {
        when(urlPhoneAlertService.getPhoneByFirestation(1)).thenReturn(phoneAlertDtoList);
        ResponseEntity<List<PhoneAlertDto>> response = urlPhoneAlertController.getPhoneAlertController(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(phoneAlertDtoList, response.getBody());
    }

    @Test
    public void testGetPhoneByFirestation_NotFound404() throws NotFoundException {
        when(urlPhoneAlertService.getPhoneByFirestation(1)).thenThrow(new NotFoundException());
        ResponseEntity<List<PhoneAlertDto>> response = urlPhoneAlertController.getPhoneAlertController(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }

    @Test
    public void testGetPhoneByFirestation_InternalServerError500() throws NotFoundException {
        when(urlPhoneAlertService.getPhoneByFirestation(1)).thenThrow(new RuntimeException("Internal error"));
        ResponseEntity<List<PhoneAlertDto>> response = urlPhoneAlertController.getPhoneAlertController(1);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }
}
