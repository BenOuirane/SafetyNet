package com.alerts.SafetyNet.model;
/**
 * Class: MedicalRecord
 * @author: BEN OUIRANE Hajer
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MedicalRecord {

	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonFormat(pattern = "MM/dd/yyyy")
	private LocalDate birthdate;
	@JsonProperty("medications")
	private List<String> medications = new ArrayList<>();
	@JsonProperty("allergies")
	private List<String> allergies = new ArrayList<>();
	@Override
	public String toString() {
		return "MedicalRecord [firstName=" + firstName + ", lastName="
				+ lastName + ", birthdate=" + birthdate + ", medications="
				+ medications + ", allergies=" + allergies + "]";
	}
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
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	public List<String> getMedications() {
		return medications;
	}
	public void setMedications(List<String> medications) {
		this.medications = medications;
	}
	public List<String> getAllergies() {
		return allergies;
	}
	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}
	public MedicalRecord(String firstName, String lastName, LocalDate birthdate,
			List<String> medications, List<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.medications = medications;
		this.allergies = allergies;
	}
	public MedicalRecord() {
		super();
	}

}
