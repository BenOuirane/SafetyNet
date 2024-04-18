package com.alerts.SafetyNet.repository;
/**
 * Contains MedicalRecord Data, CRUD and advanced filter methods
 * 
 * @author : BEN OUIRANE Hajer
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alerts.SafetyNet.entity.MedicalRecord;

@Repository
public interface MedicalrecordRepository extends JpaRepository<MedicalRecord, Long>{

}
