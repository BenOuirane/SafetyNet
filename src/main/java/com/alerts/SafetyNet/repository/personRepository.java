package com.alerts.SafetyNet.repository;
/**
 * Contains Person Data, CRUD and advanced filter methods
 * 
 * @author : BEN OUIRANE Hajer
 *
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.alerts.SafetyNet.entity.Person;

@Repository
public interface personRepository extends JpaRepository<Person, Long>{

}
