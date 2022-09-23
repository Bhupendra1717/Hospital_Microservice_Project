import { Component, OnInit } from '@angular/core';
import { Patient } from 'src/app/patient';
import { PatientService } from 'src/app/services/patient.service';
import {FormGroup, FormControl, Validators, FormBuilder} from '@angular/forms'
import { HttpErrorResponse, HttpHeaderResponse, HttpResponse } from '@angular/common/http';
import { empty, subscribeOn } from 'rxjs';
import { DoctorService } from 'src/app/services/doctor.service';
import { Doctor } from 'src/app/doctor';

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.css']
})
export class PatientDetailsComponent implements OnInit {
  patient: Patient = new Patient();
  formGroup: FormGroup;
  patientIdMatch: number;
  doctor: Doctor = new Doctor();

  constructor(private patientService: PatientService, private doctorService: DoctorService, private formBuilder: FormBuilder) {
    this.formGroup= this.formBuilder.group({
      patientId: new FormControl('',Validators.required),
    })
      
    }

  ngOnInit(): void {
  }

  onSubmit(){
    this.patientIdMatch = -1;
    this.patientService.getPatientById(this.formGroup.value.patientId).subscribe(data => {
      this.patient = data;
      this.patientIdMatch = this.patient.id;
      this.doctorService.getDoctorById(this.patient.visitedDoctorId).subscribe(data => {this.doctor= data});
    })
  }
  
}
