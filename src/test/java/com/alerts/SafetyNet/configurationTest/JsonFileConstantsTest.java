package com.alerts.SafetyNet.configurationTest;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.alerts.SafetyNet.configuration.JsonFileConstants;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class JsonFileConstantsTest {
	
    @Test
    public void testPersonConstants() {
        assertEquals("persons", JsonFileConstants.persons);
        assertEquals("firstName", JsonFileConstants.person_firstName);
        assertEquals("lastName", JsonFileConstants.person_lastName);
        assertEquals("address", JsonFileConstants.person_address);
        assertEquals("city", JsonFileConstants.person_city);
        assertEquals("zip", JsonFileConstants.person_zip);
        assertEquals("phone", JsonFileConstants.person_phone);
        assertEquals("email", JsonFileConstants.person_email);
    }

    @Test
    public void testFirestationConstants() {
        assertEquals("firestations", JsonFileConstants.firestations);
        assertEquals("address", JsonFileConstants.firestation_address);
        assertEquals("station", JsonFileConstants.firestation_station);
    }
    
    @Test
    public void testMedicalRecordConstants() {
        assertEquals("medicalrecords", JsonFileConstants.medicalrecords);
        assertEquals("firstName", JsonFileConstants.medicalrecord_firstName);
        assertEquals("lastName", JsonFileConstants.medicalrecord_lastName);
        assertEquals("birthdate", JsonFileConstants.medicalrecord_birthdate);
        assertEquals("medications", JsonFileConstants.medicalrecord_medications);
        assertEquals("allergies", JsonFileConstants.medicalrecord_allergies);
    }
    
}
