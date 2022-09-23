package com.mindtree.patient.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.patient.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

	List<Patient> findAllByVisitedDoctorId(int doctorId);

}
