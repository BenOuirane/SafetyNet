package com.alerts.SafetyNet.dto;

public class PersonByStationDto {
	
	String firstName;
    String lastName;
    String address;
    String phone;
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhone() {
		return phone;
	}

	public PersonByStationDto(String firstName, String lastName, String address,
			String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "PersonByStationDto [firstName=" + firstName + ", lastName="
				+ lastName + ", address=" + address + ", phone=" + phone + "]";
	}
	
}
