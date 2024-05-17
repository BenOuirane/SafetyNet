package com.alerts.SafetyNet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import com.alerts.SafetyNet.repository.impl.PersonRepositoryImpl;


@SpringBootApplication
@ComponentScan(basePackages = "com.alerts.SafetyNet")
public class SafetyNetApplication  extends SpringBootServletInitializer {

	  @Autowired
	  PersonRepositoryImpl personRepositoryimpl;
	  
	public static void main(String[] args) {
		SpringApplication.run(SafetyNetApplication.class, args);
	}

	/*
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
		File jsonFile = new File("src/main/resources/data/data.json");
        JsonNode jsonFileContent = ((JsonNode)objectMapper.readTree(jsonFile));
        JsonNode PERSONSContent = jsonFileContent.get("persons");
    	 List<Person> listPersons = new ArrayList<>();

        if(PERSONSContent.isArray()) {
        	for(JsonNode PersonNode : PERSONSContent) {
        	 //  	System.out.println(PersonNode);
        	   	String personStr = objectMapper.writeValueAsString(PersonNode);
        	   	Person person = objectMapper.readValue(personStr, Person.class);
        	   	listPersons.add(person);
        	//   	System.out.println(person.getCity());
        	}
    	   	personRepositoryimpl.setPerson(listPersons);

        }
		
	}
	*/


}
