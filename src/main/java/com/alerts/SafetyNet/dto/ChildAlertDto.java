package com.alerts.SafetyNet.dto;

import java.util.List;
import com.alerts.SafetyNet.entity.Person;
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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<Person> getFamilyMembers() {
		return FamilyMembers;
	}

	public void setFamilyMembers(List<Person> familyMembers) {
		FamilyMembers = familyMembers;
	}

	public ChildAlertDto(String firstName, String lastName, Integer age,
			List<Person> familyMembers) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		FamilyMembers = familyMembers;
	}
	
}
