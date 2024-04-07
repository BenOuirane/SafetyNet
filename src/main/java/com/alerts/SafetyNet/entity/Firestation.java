package com.alerts.SafetyNet.entity;
/**
 * Class: Firestation
 * @author: BEN OUIRANE Hajer
 */
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
@Table(name="firestation")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Firestation {

	
	@Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 Long    id;
	 String  address;
	 Integer station;
}
