package com.alerts.SafetyNet.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FloodStationsDto {
	
    @JsonProperty("address")
	String address;
    @JsonProperty("habitants")
	List<HabitantDto> habitants;
    
	public String getAddress() {
		return address;
	}
	public List<HabitantDto> getHabitants() {
		return habitants;
	}
	public FloodStationsDto(String address, List<HabitantDto> habitants) {
		super();
		this.address = address;
		this.habitants = habitants;
	}
	
}
