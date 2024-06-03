package com.alerts.SafetyNet.service;

import java.util.List;
import com.alerts.SafetyNet.entity.MedicalRecord;
import com.alerts.SafetyNet.exception.NotFoundException;

/**
 * Service that provides CRUD and advanced filter methods for medicalrecord
 * 
 *  @author: BEN OUIRANE Hajer
 *
 */


public interface MedicalrecordService {
	
	List<MedicalRecord> getMedicalRecord();
	MedicalRecord    createMedicalRecord(MedicalRecord m);
	MedicalRecord    updateMedicalRecord(MedicalRecord m) throws NotFoundException;
	void deleteByName(String firstName, String lastName)  throws NotFoundException;
    
}
