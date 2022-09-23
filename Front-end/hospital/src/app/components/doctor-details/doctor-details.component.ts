import { Component, OnInit } from '@angular/core';
import { Doctor } from 'src/app/doctor';
import { DoctorService } from 'src/app/services/doctor.service';

@Component({
  selector: 'app-doctor-details',
  templateUrl: './doctor-details.component.html',
  styleUrls: ['./doctor-details.component.css']
})
export class DoctorDetailsComponent implements OnInit {

  doctors: any;
  doctor: Doctor = new Doctor();

  // selectedDoctor:any = {
  //   id: 0,
  //   name: '',
  //   age: 0,
  //   gender: '',
  //   specialistOf: '',
  //   noOfPatientsAttended: -1,
  // }

  constructor(private doctorService: DoctorService) { 
    
  }

  ngOnInit(): void {
    this.getAllDoctors();
    this.onSelect(this.doctor.id);
  }

  getAllDoctors(){
    this.doctorService.getDoctorsList().subscribe(data => {
      this.doctors = data;
    })
  }

  onSelect(doctorId: any){
    this.doctorService.getDoctorById(doctorId.value).subscribe(data => {
      this.doctor = data;
    })
  }

}
