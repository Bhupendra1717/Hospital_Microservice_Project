package com.mindtree.patient.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mindtree.patient.entity.Patient;
import com.mindtree.patient.repository.PatientRepository;
import com.mindtree.patient.vo.Doctor;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String get_Doctor_URL = "http://DOCTOR-SERVICE/doctors/";
	private static final String update_Doctor_URL = "http://DOCTOR-SERVICE/doctors/{doctorId}";

	//get List Of Patients
	public ResponseEntity<List<Patient>> getAllPatients() {
		List<Patient> patients =patientRepository.findAll();
		if(patients.size() <= 0) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok().body(patients);
	}

	//get Patient by Id
	public ResponseEntity<Optional<Patient>> getPatientById(Long id) {
		Optional<Patient> patient = patientRepository.findById(id);
		if(patient.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(patient);
	}
	
	//update number of patients attended by doctor
	public void updateNoOfPatientsAttended(int doctorId) {
		Doctor doctor = restTemplate.getForObject(get_Doctor_URL+doctorId, Doctor.class);
		doctor.setNoOfPatientsAttended((long) patientRepository.findAllByVisitedDoctorId(doctorId).size());	
		
		Map<String, Integer> param = new HashMap<>();
		param.put("doctorId", doctorId);
		restTemplate.put(update_Doctor_URL, doctor, param);
	}

	//add new patient
	public ResponseEntity<Patient> addPatient(Patient patient) {
		try {
			LocalDateTime myDateObj = LocalDateTime.now();
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		    String formattedDate = myDateObj.format(myFormatObj);  
		    
		    patient.setDateOfVisit(formattedDate);
			patientRepository.save(patient);
			
			int doctorId = patient.getVisitedDoctorId();
			updateNoOfPatientsAttended(doctorId);
			return ResponseEntity.status(HttpStatus.CREATED).body(patient);
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}

	//update patient information
	public ResponseEntity<Patient> updatePatient(Patient patient, Long id) {
		Optional<Patient> p = patientRepository.findById(id);
		int pId = p.get().getVisitedDoctorId();
		if(p.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		patient.setId(id);
		patientRepository.save(patient);
		updateNoOfPatientsAttended(patient.getVisitedDoctorId());
		updateNoOfPatientsAttended(pId);

		return ResponseEntity.ok().body(patient);
	}

	//delete patient entry
	public ResponseEntity<Void> deletePatient(Long id) {
		try {
			Patient patient = patientRepository.findById(id).get();
			int doctorId = patient.getVisitedDoctorId();
			patientRepository.deleteById(id);
			updateNoOfPatientsAttended(doctorId);
			
			return ResponseEntity.noContent().build();
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().build();
		}
	}
	
	

}