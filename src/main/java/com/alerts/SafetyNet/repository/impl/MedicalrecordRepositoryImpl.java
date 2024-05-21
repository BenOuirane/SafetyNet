package com.alerts.SafetyNet.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alerts.SafetyNet.entity.MedicalRecord;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.MedicalrecordRepository;

@Service
public class MedicalrecordRepositoryImpl  implements MedicalrecordRepository{
	
	private List<MedicalRecord> listMedicalRecords = new ArrayList<>();


	@Override
	public void setListMedicalRecords(List<MedicalRecord> listMedicalRecords) {
		this.listMedicalRecords = listMedicalRecords;
		
	}

	@Override
	public MedicalRecord addMedicalRecord(MedicalRecord medicalRecord) {
		listMedicalRecords.add(medicalRecord);
		return medicalRecord;
	}

	@Override
	public List<MedicalRecord> getMedicalRecord() {
		return listMedicalRecords;
	}

	@Override
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord)
			throws NotFoundException {

		MedicalRecord medicalRecordToUpdate = getMedicalRecordByName(medicalRecord.getFirstName(),
				                                  medicalRecord.getLastName());
		int medicalRecordToUpdateIndex = listMedicalRecords.indexOf(medicalRecordToUpdate);
		
		listMedicalRecords.set(medicalRecordToUpdateIndex, medicalRecord);
		
		return medicalRecord;
	}

	@Override
	public MedicalRecord getMedicalRecordByName(String firstName,
			String lastName) throws NotFoundException {
       Optional<MedicalRecord> medicalRecord = listMedicalRecords.stream()
    		   .filter(m -> m.getFirstName().equals(lastName))
    		   .filter(m -> m.getLastName().equals(lastName))
    		   .findFirst();
       if(medicalRecord.isPresent()) {
    	   return medicalRecord.get();
       } else {
    	   throw new NotFoundException();
       }       
	}
	
	

}
