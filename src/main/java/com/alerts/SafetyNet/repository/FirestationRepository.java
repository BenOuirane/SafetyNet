package com.alerts.SafetyNet.repository;
/**
 * Contains Firestation Data, CRUD and advanced filter methods
 * 
 * @author : BEN OUIRANE Hajer
 *
 */

import org.springframework.stereotype.Repository;
import com.alerts.SafetyNet.entity.Firestation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Repository
public interface FirestationRepository{
	
     static final Logger logger = LogManager.getLogger(FirestationRepository.class);


}
