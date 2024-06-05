package com.alerts.SafetyNet.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FireDto {
	
    @JsonProperty("firestationNumber")
	int firestationNumber;
    @JsonProperty("firstName")
	String firstName;
    @JsonProperty("phone")
    String phone;
    @JsonProperty("age")
	int age;
    @JsonProperty("medications")
    List<String> medications;
    @JsonProperty("allergies")
    List<String> allergies;
	public FireDto(int firestationNumber, String firstName, String phone,
			int age, List<String> medications, List<String> allergies) {
		super();
		this.firestationNumber = firestationNumber;
		this.firstName = firstName;
		this.phone = phone;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;
	}
	
	public int getFirestationNumber() {
		return firestationNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getPhone() {
		return phone;
	}
	public int getAge() {
		return age;
	}
	public List<String> getMedications() {
		return medications;
	}
	public List<String> getAllergies() {
		return allergies;
	}

}
