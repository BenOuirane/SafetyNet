package com.alerts.SafetyNet.service.url;

import org.springframework.stereotype.Service;

import com.alerts.SafetyNet.configuration.DtoConstants.PersonField;
import com.alerts.SafetyNet.dto.PersonDto;
import com.alerts.SafetyNet.entity.Person;

/**
 * Convert a class  in a DTO object. Used to create Json ouput files.
 * 
 * @author BEN OUIRANE Hajeur
 *
 */
@Service
public class ObjectDtoConverterService {
	
	
	
	/**
	 * Convert a {@link Person} in a DTO object
	 * 
	 * @param person : Person to convert
	 * @param fields : attributes that must appear in the output Json File
	 * @return {@link PersonDto}
	 */
	
	public PersonDto buildPersonDto(Person person, PersonField[] fields) { 
		PersonDto personDto = new PersonDto();
		for (PersonField field : fields) {
			switch (field) {
			case FIRST_NAME:
				personDto.setFirstName(person.getFirstName());
				break;
			case LAST_NAME:
				personDto.setLastName(person.getLastName());
				break;
			case ADDRESS:
				personDto.setAddress(person.getAddress());
				break;
			case CITY:
				personDto.setCity(person.getCity());
				break;
			case ZIP:
				personDto.setZip(person.getZip());
				break;
			case PHONE:
				personDto.setPhone(person.getPhone());
				break;
			case EMAIL:
				personDto.setEmail(person.getEmail());
				break;
			default:
				break;

			}
		}
		return personDto;
	}
		
		
		
	}
	
	
	


