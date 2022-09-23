package com.mindtree.patient.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mindtree.patient.entity.Patient;
import com.mindtree.patient.service.PatientService;

@RestController
@EnableWebMvc
@RequestMapping("/patients")
@CrossOrigin(origins = "*")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping
	public ResponseEntity<List<Patient>> getAllPatients(){
		return patientService.getAllPatients();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Patient>> getPatientById(@PathVariable Long id){
		return patientService.getPatientById(id);
	}
	
	@PostMapping
	public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
		return patientService.addPatient(patient);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient, @PathVariable Long id){
		return patientService.updatePatient(patient, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePatient(@PathVariable Long id){
		return patientService.deletePatient(id);
	}
}
