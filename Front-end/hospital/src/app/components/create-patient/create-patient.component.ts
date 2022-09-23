import { state } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { Doctor } from 'src/app/doctor';
import { Patient } from 'src/app/patient';
import { DoctorService } from 'src/app/services/doctor.service';
import { PatientService } from 'src/app/services/patient.service';
import {FormGroup,FormControl,Validators, FormBuilder} from '@angular/forms'

@Component({
  selector: 'app-create-patient',
  templateUrl: './create-patient.component.html',
  styleUrls: ['./create-patient.component.css']
})
export class CreatePatientComponent implements OnInit {

  patient: Patient = new Patient();
  doctors: Doctor[];

  formGroup: FormGroup;

  constructor(private patientService: PatientService, private doctorService: DoctorService, private formBuilder: FormBuilder) {
    this.formGroup= this.formBuilder.group({
      name: new FormControl('',Validators.compose([Validators.required, Validators.minLength(6)])),
      gender: new FormControl('',Validators.required),
      age: new FormControl('',Validators.required),
      visitedDoctorId: new FormControl('',Validators.required),
      doctorsPriscription: new FormControl('',Validators.required),
    })
   }

  ngOnInit(): void {
    this.getDoctors();
  }

  getDoctors(){
    this.doctorService.getDoctorsList().subscribe(data => {
    this.doctors = data;
  })
}

get f(){
  return this.formGroup.controls;
}

registerPatient(){
  this.patientService.createPatient(this.patient).subscribe(data => {
    console.log(data);
    alert("patient registered");
  },
    error => console.log(error))
    console.log(this.patient);
    this.formGroup.reset();
}

onSubmit(){
  this.patient.name = this.formGroup.value.name;
  this.patient.gender = this.formGroup.value.gender;
  this.patient.age = this.formGroup.value.age;
  this.patient.visitedDoctorId = this.formGroup.value.visitedDoctorId;
  this.patient.doctorsPriscription = this.formGroup.value.doctorsPriscription;

  this.registerPatient();
}


}
