import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreatePatientComponent } from './components/create-patient/create-patient.component';
import { DoctorDetailsComponent } from './components/doctor-details/doctor-details.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { PatientDetailsComponent } from './components/patient-details/patient-details.component';
import { PatientListComponent } from './components/patient-list/patient-list.component';

const routes: Routes = [
  {path: 'create-patient', component: CreatePatientComponent},
  {path: 'doctor-details', component: DoctorDetailsComponent},
  {path: 'patient-details', component: PatientDetailsComponent},
  {path: 'patient-list', component: PatientListComponent},
  {path: '', component:HomePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
