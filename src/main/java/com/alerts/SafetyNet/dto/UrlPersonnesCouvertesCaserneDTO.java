package com.alerts.SafetyNet.dto;

import java.util.List;

public class UrlPersonnesCouvertesCaserneDTO {
	
	List<PersonDto> persons;
	Integer adultsNumber;
	Integer childrenNumber;
	public List<PersonDto> getPersons() {
		return persons;
	}
	public void setPersons(List<PersonDto> persons) {
		this.persons = persons;
	}
	public Integer getAdultsNumber() {
		return adultsNumber;
	}
	public void setAdultsNumber(Integer adultsNumber) {
		this.adultsNumber = adultsNumber;
	}
	public Integer getChildrenNumber() {
		return childrenNumber;
	}
	public void setChildrenNumber(Integer childrenNumber) {
		this.childrenNumber = childrenNumber;
	}
	@Override
	public String toString() {
		return "UrlPersonnesCouvertesCaserneDTO [persons=" + persons
				+ ", adultsNumber=" + adultsNumber + ", childrenNumber="
				+ childrenNumber + "]";
	}
	public UrlPersonnesCouvertesCaserneDTO(List<PersonDto> persons,
			Integer adultsNumber, Integer childrenNumber) {
		super();
		this.persons = persons;
		this.adultsNumber = adultsNumber;
		this.childrenNumber = childrenNumber;
	}
	public UrlPersonnesCouvertesCaserneDTO() {
		super();
	}

}
