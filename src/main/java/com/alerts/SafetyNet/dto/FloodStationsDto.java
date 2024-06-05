package com.alerts.SafetyNet.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FloodStationsDto {
	
    @JsonProperty("address")
	String address;
    @JsonProperty("Habitants")
	List<HabitantDto> Habitants;
    
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<HabitantDto> getHabitants() {
		return Habitants;
	}
	public void setHabitants(List<HabitantDto> habitants) {
		Habitants = habitants;
	}
	public FloodStationsDto(String address, List<HabitantDto> habitants) {
		super();
		this.address = address;
		Habitants = habitants;
	}
	
}
