package com.alerts.SafetyNet.repository;
/**
 * Contains MedicalRecord Data, CRUD and advanced filter methods
 * 
 * @author : BEN OUIRANE Hajer
 *
 */

import org.springframework.stereotype.Repository;

import com.alerts.SafetyNet.entity.MedicalRecord;
import com.alerts.SafetyNet.exception.NotFoundException;
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
     
     


}
