package com.alerts.SafetyNet.repository;
/**
 * Contains Firestation Data, CRUD and advanced filter methods
 * 
 * @author : BEN OUIRANE Hajer
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alerts.SafetyNet.entity.Firestation;


@Repository
public interface firestationRepository extends JpaRepository<Firestation, Long> {

}
