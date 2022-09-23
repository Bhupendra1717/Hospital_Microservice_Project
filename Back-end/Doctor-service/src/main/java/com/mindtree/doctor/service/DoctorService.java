package com.mindtree.doctor.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mindtree.doctor.entity.Doctor;
import com.mindtree.doctor.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;

	public ResponseEntity<List<Doctor>> getAllDoctors() {
		List<Doctor> doctors= doctorRepository.findAll();
		if(doctors.size() <= 0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(doctors);
	}

	public ResponseEntity<Optional<Doctor>> getDoctorById(int id) {
		Optional<Doctor> doctor = doctorRepository.findById(id);
		
		if(doctor.isEmpty()) {
			return ResponseEntity.notFound().build();

		}
		return ResponseEntity.ok().body(doctor);
	}

	public Doctor addDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	public ResponseEntity<Doctor> updateDoctor(Doctor doctor, int id) {
		Optional<Doctor> doc = doctorRepository.findById(id);
		if(doc.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		doctor.setId(id);
		doctorRepository.save(doctor);
		return ResponseEntity.ok().body(doctor);
	}

	public ResponseEntity<Void> deleteDoctor(int id) {
		try {
			doctorRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	

}
