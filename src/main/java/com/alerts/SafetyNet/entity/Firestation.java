package com.alerts.SafetyNet.entity;
/**
 * Class: Firestation
 * @author: BEN OUIRANE Hajer
 */

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Firestation {

	@JsonProperty("address")
	String  address;
	@JsonProperty("station")
	Integer station;
	@Override
	public String toString() {
		return "Firestation [address=" + address + ", station=" + station + "]";
	}
	
	
	
}
