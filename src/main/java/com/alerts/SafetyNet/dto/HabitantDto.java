package com.alerts.SafetyNet.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HabitantDto {
	
    @JsonProperty("lastName")
	String lastName;
    @JsonProperty("phone")
	String phone;
    @JsonProperty("age")
	int    age;
    @JsonProperty("medications")
	List<String> medications;
    @JsonProperty("allergies")
	List<String> allergies;
    
	public String getLastName() {
		return lastName;
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
	public HabitantDto(String lastName, String phone, int age,
			List<String> medications, List<String> allergies) {
		super();
		this.lastName = lastName;
		this.phone = phone;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;
	}
	

}
