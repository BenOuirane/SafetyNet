package com.alerts.SafetyNet.dto;

import java.util.List;

import com.alerts.SafetyNet.model.Person;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ChildAlertDto {

    @JsonProperty("firstName")
	String firstName;
    @JsonProperty("lastName")
    String lastName;
    @JsonProperty("age")
	Integer age;
    @JsonProperty("familyMembers")
	List<Person> FamilyMembers;
    
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Integer getAge() {
		return age;
	}
	public List<Person> getFamilyMembers() {
		return FamilyMembers;
	}
	public ChildAlertDto(String firstName, String lastName, Integer age,
			List<Person> familyMembers) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.FamilyMembers = familyMembers;
	}
	
}
