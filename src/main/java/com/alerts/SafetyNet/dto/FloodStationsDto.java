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
	public List<HabitantDto> getHabitants() {
		return Habitants;
	}
	public FloodStationsDto(String address, List<HabitantDto> habitants) {
		super();
		this.address = address;
		Habitants = habitants;
	}
	
}
