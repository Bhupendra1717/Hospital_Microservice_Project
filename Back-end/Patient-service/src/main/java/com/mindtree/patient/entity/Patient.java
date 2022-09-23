package com.mindtree.patient.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patients_information")
@AllArgsConstructor
@NoArgsConstructor
//@Data
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String gender;
	private int age;
	private int visitedDoctorId;
	private String dateOfVisit;
	private String doctorsPriscription;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getVisitedDoctorId() {
		return visitedDoctorId;
	}
	public void setVisitedDoctorId(int visitedDoctorId) {
		this.visitedDoctorId = visitedDoctorId;
	}
	public String getDateOfVisit() {
		return dateOfVisit;
	}
	public void setDateOfVisit(String dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}
	public String getDoctorsPriscription() {
		return doctorsPriscription;
	}
	public void setDoctorsPriscription(String doctorsPriscription) {
		this.doctorsPriscription = doctorsPriscription;
	}
	
}
