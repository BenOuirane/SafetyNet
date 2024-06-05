package com.alerts.SafetyNet.service.url;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.PersonRepository;

@Service
public class UrlCommunityEmailService {
	
	@Autowired
	PersonRepository personRepository;
	
	public List<String> getCommunityEmailService (String city) throws NotFoundException {		
        List<String > CommunityEmail = personRepository.getEmailsByCity(city);            
        return CommunityEmail;
	}

}
