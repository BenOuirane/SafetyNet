package com.alerts.SafetyNet.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.entity.Firestation;
import com.alerts.SafetyNet.exception.NotFoundException;
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

	@Override
	public Firestation updateFirestation(Firestation firestation)
			throws NotFoundException {
		Optional<Firestation> firestationToUpdate = listFirestations.stream()
				.filter(f -> f.getAddress().equals(firestation.getAddress())).findFirst();
		if (firestationToUpdate.isPresent()) {
			listFirestations.set(listFirestations.indexOf(firestationToUpdate.get()), firestation);
			return firestation;
		} else {
			throw new NotFoundException();
		}

	
	}

	@Override
	public void deleteFirestation(Firestation firestation)
			throws NotFoundException {
		if (!listFirestations.remove(firestation)) {
			throw new NotFoundException();
		}
		
	}

	@Override
	public void deleteFirestationsByAddress(String address) throws NotFoundException {
           List<Firestation> firestations = this.getFirestationsByAddress(address) ;
           for (Firestation f : firestations) {
        	   this.deleteFirestation(f);
           }
		
	}

	@Override
	public void deleteFirestationsByStationNumber(Integer stationNumber)
			throws NotFoundException {
        List<Firestation> firestations = this.getFirestationsByStationNumber(stationNumber);
        for(Firestation f : firestations) {
        	this.deleteFirestation(f);;
        }
	}

	@Override
	public List<Firestation> getFirestationsByAddress(String address)
			throws NotFoundException {
       List<Firestation> firestations = listFirestations.stream().filter(f ->f.getAddress().equals(address)).toList();
       if(firestations.isEmpty()) {
    	   throw new NotFoundException();
       }else {
    	   return firestations;
       }
	}

	@Override
	public List<Firestation> getFirestationsByStationNumber(
			Integer stationNumber) throws NotFoundException {
		 List<Firestation> firestations = listFirestations.stream().filter(f ->f.getStation().equals(stationNumber)).toList();
	       if(firestations.isEmpty()) {
	    	   throw new NotFoundException();
	       }else {
	    	   return firestations;
	       }
	}


	 
	
	

}
