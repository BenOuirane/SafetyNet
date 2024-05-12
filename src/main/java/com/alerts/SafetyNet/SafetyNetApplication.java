package com.alerts.SafetyNet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.alerts.SafetyNet.configuration.FileHandling;
import com.alerts.SafetyNet.entity.Firestation;
import com.alerts.SafetyNet.entity.MedicalRecord;
import com.alerts.SafetyNet.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootApplication
@ComponentScan(basePackages = "com.alerts.SafetyNet")
public class SafetyNetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetyNetApplication.class, args);

		
  	/*	
		
		try {
            ObjectMapper objectMapper = new ObjectMapper();
           // Ajoutez le module JavaTimeModule à votre ObjectMapper
            objectMapper.registerModule(new JavaTimeModule());
            
            File file = new File("src/main/resources/data/data.json");
            JsonNode rootNode = objectMapper.readTree(file);

            // Afficher les détails des personnes
            
            JsonNode personsNode = rootNode.get("persons");
            if (personsNode.isArray()) {
                List<Person> persons = objectMapper.readValue(personsNode.toString(), new TypeReference<List<Person>>() {});
                System.out.println("Liste des personnes :");
                for (Person person : persons) {
                    System.out.println(person.toString());
                }
            }
                  
            // Afficher les détails des dossiers médicaux
            
            JsonNode medicalRecordsNode = rootNode.get("medicalrecords");
            if (medicalRecordsNode.isArray()) {
                List<MedicalRecord> medicalRecords = objectMapper.readValue(medicalRecordsNode.toString(), new TypeReference<List<MedicalRecord>>() {});
                System.out.println("\nDossiers médicaux :");
                for (MedicalRecord medicalRecord : medicalRecords) {
                    System.out.println(medicalRecord.toString());
                }
            }
            

            // Afficher les détails des postes de secours
            JsonNode firestationsNode = rootNode.get("firestations");
            if (firestationsNode.isArray()) {
                List<Firestation> firestations = objectMapper.readValue(firestationsNode.toString(), new TypeReference<List<Firestation>>() {});
                System.out.println("\nPostes de secours :");
                for (Firestation firestation : firestations) {
                    System.out.println(firestation.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        	*/
	}


}
