import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Doctor } from '../doctor';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private httpClient: HttpClient) { }

  doctorURL = "http://localhost:8383/doctors";

  getDoctorsList(): Observable<Doctor[]>{
    return this.httpClient.get<Doctor[]>(`${this.doctorURL}`);
  }

  getDoctorById(id: number): Observable<Doctor>{
    return this.httpClient.get<Doctor>(`${this.doctorURL}/${id}`);
  }

}
