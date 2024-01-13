import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Patient} from "./patient";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PatientService {
  private patientUrl: string;
  constructor(private http: HttpClient) {
    this.patientUrl = 'http://localhost:8080/patient';
  }

  public savePatient(patient: Patient): Observable<Patient> {
    return this.http.post<Patient>(this.patientUrl, patient);
  }
}
