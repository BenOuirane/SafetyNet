package com.alerts.SafetyNet.dto;

import java.util.List;

public class PersonInfoDto {
	
	String lastName;
    String address;
    int age;
    String email;
    List<String> medications;
    List<String> allergies;
	public String getLastName() {
		return lastName;
	}
	public String getAddress() {
		return address;
	}
	public int getAge() {
		return age;
	}
	public String getEmail() {
		return email;
	}
	public List<String> getMedications() {
		return medications;
	}
	public List<String> getAllergies() {
		return allergies;
	}
	public PersonInfoDto(String lastName, String address, int age, String email,
			List<String> medications, List<String> allergies) {
		super();
		this.lastName = lastName;
		this.address = address;
		this.age = age;
		this.email = email;
		this.medications = medications;
		this.allergies = allergies;
	}
    

}
