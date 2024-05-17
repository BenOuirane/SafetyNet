package com.alerts.SafetyNet.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alerts.SafetyNet.entity.Firestation;
import com.alerts.SafetyNet.repository.FirestationRepository;

@Service
public class FirestationRepositoryImpl implements FirestationRepository {
	
	
	 List<Firestation> listFirestations = new ArrayList<>();
	 
	 public void setListFirestations(List<Firestation> listFirestations) {
			this.listFirestations = listFirestations;
		}

	@Override
	public Firestation addFirestation(Firestation firestation) {
		listFirestations.add(firestation);		
		return firestation;
	}

	@Override
	public List<Firestation> getFirestations() {
		return listFirestations;
	}


	 
	
	

}
