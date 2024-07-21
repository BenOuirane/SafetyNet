package com.alerts.SafetyNet.repository;
/**
 * Contains Firestation Data, CRUD and advanced filter methods
 * 
 * @author : BEN OUIRANE Hajer
 *
 */

import org.springframework.stereotype.Repository;

import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.model.Firestation;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Repository
public interface FirestationRepository{
	
     static final Logger logger = LogManager.getLogger(FirestationRepository.class);
     List<Firestation> getFirestations();
     Firestation addFirestation(Firestation firestation);
     Firestation updateFirestation(Firestation firestation) throws NotFoundException;
     void deleteFirestation(Firestation firestation) throws NotFoundException;
     void deleteFirestationsByAddress(String address) throws NotFoundException;    
     void deleteFirestationsByStationNumber(Integer stationNumber) throws NotFoundException;     
     List<Firestation> getFirestationsByAddress(String address) throws NotFoundException;    
     List<Firestation> getFirestationsByStationNumber(Integer stationNumber) throws NotFoundException;   
     List<String> getFirestationAddresses(Integer StationNumber) throws NotFoundException;
     int getStationByAddress(String address) throws NotFoundException;    


}
