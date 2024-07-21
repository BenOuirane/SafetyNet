package com.alerts.SafetyNet.service;

import java.util.List;

import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.model.Firestation;

/**
 * Service that provides CRUD and advanced filter methods for firestation
 * 
 *  @author: BEN OUIRANE Hajer
 *
 */

public interface FirestationService {
	
	List<Firestation> getFirestation();
	Firestation       createFirestations(Firestation f);
	Firestation       updateFirestation(Firestation f) throws NotFoundException;
	void deleteFirestation(Firestation f) throws NotFoundException;
	void deleteFirestationsByAddress(String address) throws NotFoundException;
	void deleteFirestationsByStationNumber(Integer stationNumber) throws NotFoundException;

}
