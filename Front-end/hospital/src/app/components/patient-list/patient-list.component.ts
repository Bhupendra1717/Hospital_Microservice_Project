import { Component, OnInit } from '@angular/core';
import { Patient } from 'src/app/patient';

import { PatientService } from 'src/app/services/patient.service';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {
  patients: any;

  constructor(private patientService: PatientService) { }

  ngOnInit(): void {
    this.displayAllPatients();
  }

  displayAllPatients(){
    this.patientService.getPatientsList().subscribe(data => {
      this.patients = data;
    })
  }

}
