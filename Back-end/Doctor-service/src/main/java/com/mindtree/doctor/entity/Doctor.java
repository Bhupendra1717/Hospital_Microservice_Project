package com.mindtree.doctor.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@Data
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "doctors_information")
public class Doctor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private int age;
	private String gender;
	private String specialistOf;
	private Long noOfPatientsAttended;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSpecialistOf() {
		return specialistOf;
	}
	public void setSpecialistOf(String specialistOf) {
		this.specialistOf = specialistOf;
	}
	public Long getNoOfPatientsAttended() {
		return noOfPatientsAttended;
	}
	public void setNoOfPatientsAttended(Long noOfPatientsAttended) {
		this.noOfPatientsAttended = noOfPatientsAttended;
	}
	
	

}
