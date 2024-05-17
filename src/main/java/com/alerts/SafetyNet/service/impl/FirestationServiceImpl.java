package com.alerts.SafetyNet.service.impl;
/**
 * ServiceImpl that provides CRUD and advanced filter methods for firestation
 * 
 *  @author: BEN OUIRANE Hajer
 *
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.entity.Firestation;
import com.alerts.SafetyNet.repository.impl.FirestationRepositoryImpl;
import com.alerts.SafetyNet.service.FirestationService;

@Service
public class FirestationServiceImpl  implements FirestationService {
	
	
	@Autowired
	FirestationRepositoryImpl firestationRepositoryimpl;

	@Override
	public List<Firestation> getFirestation() {
		List<Firestation> firestations = firestationRepositoryimpl.getFirestations();
		return firestations ;
	}

	@Override
	public Firestation createFirestations(Firestation f) {
		// TODO Auto-generated method stub
		return null;
	}





    

}
