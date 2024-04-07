package com.alerts.SafetyNet.entity;
/**
 * Class: MedicalRecord
 * @author: BEN OUIRANE Hajer
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@Entity
@Table(name="medicalrecord")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicalRecord {
	
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 Long id;
	 String firstName;
	 String lastName;
	@JsonFormat(pattern = "MM/dd/yyyy")
	 LocalDate birthdate;
	 List<String> medications = new ArrayList<>();
	 List<String> allergies = new ArrayList<>();

}
