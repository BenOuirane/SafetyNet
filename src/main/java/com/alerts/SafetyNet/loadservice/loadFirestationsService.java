package com.alerts.SafetyNet.loadservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.configuration.JsonFileConstants;
import com.alerts.SafetyNet.entity.Firestation;
import com.alerts.SafetyNet.repository.FirestationRepository;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class loadFirestationsService {
	
	@Autowired
	 FirestationRepository firestationRepository;

	/**
	 * Import Json data into Firestation Repository
	 * 
	 * @param firestationsNode
	 */
	public  void loadFirestations(JsonNode firestationsNode) {

		for (JsonNode firestationNode : firestationsNode) {
			Firestation firestation = new Firestation();
			firestation.setAddress(firestationNode.path(JsonFileConstants.firestation_address).asText());
			firestation.setStation(firestationNode.path(JsonFileConstants.firestation_station).asInt());
			firestationRepository.addFirestation(firestation);
		}

	}

}
