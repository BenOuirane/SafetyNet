package com.alerts.SafetyNet.dto;

import java.util.List;

public class UrlPersonnesCouvertesCaserneDTO {
	
	List<PersonByStationDto> persons;
	Integer adultsNumber;
	Integer childrenNumber;
	public List<PersonByStationDto> getPersons() {
		return persons;
	}
	public void setPersons(List<PersonByStationDto> persons) {
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

}
