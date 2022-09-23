import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Patient } from '../patient';

const httpOptions = {
  headers: new HttpHeaders({
    'ContentType' : 'application/json',
  })
};

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private httpClient: HttpClient) { }

  patientURL = "http://localhost:8282/patients";

  getPatientsList(): Observable<Patient[]>{
    return this.httpClient.get<Patient[]>(`${this.patientURL}`);
  }

  createPatient(patient: Patient):Observable<Object>{
    return this.httpClient.post(`${this.patientURL}`, patient, httpOptions);
  }

  getPatientById(id: number): Observable<Patient>{
    return this.httpClient.get<Patient>(`${this.patientURL}/${id}`);
  }

  updatePatient(id: number, patient: Patient): Observable<Object>{
    return this.httpClient.put(`${this.patientURL}/${id}`,patient);
  }

  deletePatient(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.patientURL}/${id}`);
  }
}
