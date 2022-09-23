package com.mindtree.doctor.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mindtree.doctor.entity.Doctor;
import com.mindtree.doctor.service.DoctorService;

@RestController
@EnableWebMvc
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	
	@GetMapping
	public ResponseEntity<List<Doctor>> getAllDoctors(){
		return doctorService.getAllDoctors();
	}
	
	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Doctor>> getDoctorById(@PathVariable int id) {
		return doctorService.getDoctorById(id);
	}
	
	@PostMapping//(consumes =MediaType.APPLICATION_JSON_VALUE  ,produces = MediaType.APPLICATION_JSON_VALUE)
	public Doctor addDoctor(@RequestBody Doctor doctor) {
		return doctorService.addDoctor(doctor);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor, @PathVariable int id) {
		return doctorService.updateDoctor(doctor, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDoctor(@PathVariable int id) {
		return doctorService.deleteDoctor(id);
	}
}
