package com.alerts.SafetyNet.repository;
/**
 * Contains MedicalRecord Data, CRUD and advanced filter methods
 * 
 * @author : BEN OUIRANE Hajer
 *
 */

import org.springframework.stereotype.Repository;
import com.alerts.SafetyNet.entity.MedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Repository
public interface MedicalrecordRepository {
	
     static final Logger logger = LogManager.getLogger(MedicalrecordRepository.class);


}
