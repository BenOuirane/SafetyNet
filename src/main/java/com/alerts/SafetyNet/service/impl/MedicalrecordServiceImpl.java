package com.alerts.SafetyNet.service.impl;
/**
 * ServiceImpl that provides CRUD and advanced filter methods for medicalrecord
 * 
 *  @author: BEN OUIRANE Hajer
 *
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alerts.SafetyNet.entity.MedicalRecord;
import com.alerts.SafetyNet.exception.NotFoundException;
import com.alerts.SafetyNet.repository.MedicalrecordRepository;
import com.alerts.SafetyNet.service.MedicalrecordService;

@Service
public class MedicalrecordServiceImpl implements MedicalrecordService{
	
    @Autowired
    MedicalrecordRepository    medicalrecordRepository;
  

	@Override
	public List<MedicalRecord> getMedicalRecord() {
		List<MedicalRecord> medicalRecords = medicalrecordRepository.getMedicalRecord();
		return medicalRecords ;
	}

	@Override
	public MedicalRecord createMedicalRecord(MedicalRecord m) {
		return medicalrecordRepository.addMedicalRecord(m);
	}

	@Override
	public MedicalRecord updateMedicalRecord(MedicalRecord m)
			throws NotFoundException {
          return medicalrecordRepository.updateMedicalRecord(m);
	}

}
