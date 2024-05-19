package com.alerts.SafetyNet.service;

import java.util.List;
import com.alerts.SafetyNet.entity.Firestation;
import com.alerts.SafetyNet.entity.Person;
import com.alerts.SafetyNet.exception.NotFoundException;

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


}
