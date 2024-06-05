package com.alerts.SafetyNet.urlServiceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.PersonRepository;
import com.alerts.SafetyNet.service.url.UrlCommunityEmailService;

@ExtendWith(MockitoExtension.class)
public class UrlCommunityEmailServiceTest {
    
    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private UrlCommunityEmailService communityEmailService;
    
    @Test
    public void testGetCommunityEmailService_Success() throws NotFoundException {
        // Arrange
        String city = "Springfield";
        List<String> expectedEmails = new ArrayList<>();
        expectedEmails.add("john.doe@example.com");
        expectedEmails.add("jane.smith@example.com");   
        // Mocking behavior of PersonRepository
        when(personRepository.getEmailsByCity(city)).thenReturn(expectedEmails);
        // Act
        List<String> actualEmails = communityEmailService.getCommunityEmailService(city);
        // Assert
        assertEquals(expectedEmails, actualEmails);
    }
    
    @Test
    public void testGetCommunityEmailService_NotFound() throws NotFoundException {
        // Arrange
        String nonExistingCity = "NonExistingCity";       
        // Mocking behavior of PersonRepository to throw NotFoundException
        when(personRepository.getEmailsByCity(nonExistingCity)).thenThrow(new NotFoundException());       
        // Act & Assert
        assertThrows(NotFoundException.class, () -> {
            communityEmailService.getCommunityEmailService(nonExistingCity);
        });
    }
    
}
