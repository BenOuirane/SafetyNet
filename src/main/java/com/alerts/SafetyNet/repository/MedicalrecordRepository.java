package com.alerts.SafetyNet.repository;
/**
 * Contains MedicalRecord Data, CRUD and advanced filter methods
 * 
 * @author : BEN OUIRANE Hajer
 *
 */

import org.springframework.stereotype.Repository;

import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.model.MedicalRecord;
import com.alerts.SafetyNet.model.Person;

import java.time.LocalDate;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Repository
public interface MedicalrecordRepository {
	
     static final Logger logger = LogManager.getLogger(MedicalrecordRepository.class);
     
     void setListMedicalRecords(List<MedicalRecord> listMedicalRecords); 
     MedicalRecord addMedicalRecord(MedicalRecord medicalRecord);
     public   List<MedicalRecord> getMedicalRecord();
     MedicalRecord  updateMedicalRecord(MedicalRecord medicalRecord) throws NotFoundException;
     MedicalRecord getMedicalRecordByName(String firstName, String lastName) throws NotFoundException;
     void deleteMedicalRecord(MedicalRecord medicalRecord) throws NotFoundException;
     void deleteMedicalRecordByName(String firstName, String lastName) throws NotFoundException;
     public int calculateAge(LocalDate birthdate);   
     public Integer havePersonAge(Person p) throws NotFoundException;
     public boolean ifAdult(Person p);
     public boolean ifChild(Person p);
     List<String> getMedicationsByLastName(String lastName) throws NotFoundException;
     List<String> getAllergiesByLastName(String lastName) throws NotFoundException;

	 
}
