package com.alerts.SafetyNet.repository.impl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.entity.MedicalRecord;
import com.alerts.SafetyNet.entity.Person;
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
    		   .filter(m -> m.getFirstName().equals(firstName))
    		   .filter(m -> m.getLastName().equals(lastName))
    		   .findFirst();
       if(medicalRecord.isPresent()) {
    	   return medicalRecord.get();
       } else {
    	   throw new NotFoundException();
       }       
	}

	@Override
	public void deleteMedicalRecord(MedicalRecord medicalRecord)
			throws NotFoundException {
         if(!listMedicalRecords.remove(medicalRecord)) {
        	 throw new NotFoundException();		
         }
	}

	@Override
	public void deleteMedicalRecordByName(String firstName, String lastName)
			throws NotFoundException {
		MedicalRecord medicalRecordToUpdate = getMedicalRecordByName(firstName,lastName);
		deleteMedicalRecord(medicalRecordToUpdate);
	}

	@Override
	public int calculateAge(LocalDate birthdate) {
		Period duration = Period.between(birthdate, LocalDate.now());
		return duration.getYears();
	}

	@Override
	public Integer havePersonAge(Person p) throws NotFoundException {
		 Optional<MedicalRecord> personMR = listMedicalRecords.stream()
				                 .filter(m -> m.getFirstName().equals(p.getFirstName()))
				                 .filter(m -> m.getLastName().equals(p.getLastName())).findFirst();
		 if(personMR.isPresent()) {
			 return calculateAge(personMR.get().getBirthdate());
		 }else {
			 throw new NotFoundException();
		 }
	}

	@Override
	public boolean ifAdult(Person p) {
		try {
			return (this.havePersonAge(p)>18);
		}catch(NotFoundException e){
		return false;
		}
	}

	@Override
	public boolean ifChild(Person p) {
		try {
			return (this.havePersonAge(p) <= 18);
		}catch(NotFoundException e){
		return false;
		}
	}

}
