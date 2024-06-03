package com.alerts.SafetyNet.entity;
/**
 * Class: Firestation
 * @author: BEN OUIRANE Hajer
 */
import com.fasterxml.jackson.annotation.JsonProperty;

public class Firestation {

	@JsonProperty("address")
	String  address;
	@JsonProperty("station")
	Integer station;
	@Override
	public String toString() {
		return "Firestation [address=" + address + ", station=" + station + "]";
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getStation() {
		return station;
	}
	public void setStation(Integer station) {
		this.station = station;
	}
	public Firestation(String address, Integer station) {
		super();
		this.address = address;
		this.station = station;
	}
	public Firestation() {
		super();
	}
	
}
