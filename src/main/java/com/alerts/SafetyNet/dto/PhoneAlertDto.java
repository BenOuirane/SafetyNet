package com.alerts.SafetyNet.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PhoneAlertDto {
	 
	 @JsonProperty("phone")
	  List<String> phone;

	public List<String> getPhone() {
		return phone;
	}

	public void setPhone(List<String> phone) {
		this.phone = phone;
	}

	public PhoneAlertDto(List<String> phone) {
		super();
		this.phone = phone;
	}


}
