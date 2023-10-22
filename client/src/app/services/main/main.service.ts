import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Doctor} from "../../doctor";
import {map, Observable} from "rxjs";
import {switchMap} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MainService {
  private doctorUrl: string;

  constructor(private http: HttpClient) {
    this.doctorUrl = 'http://localhost:8080/doctor';
  }

  public findAllDoctors(): Observable<Doctor[]>{
    return this.http.get<Doctor[]>(this.doctorUrl);
  }

  public saveDoctor(doctor: Doctor): Observable<Doctor> {
    // Save the new doctor
    return this.http.post<Doctor>(this.doctorUrl, doctor).pipe(
      switchMap(() => {
        // After saving, fetch the list of doctors
        return this.findAllDoctors();
      }),
      map((doctors) => {
        // Find the last doctor's id
        let lastDoctor = doctors[doctors.length - 1];
        let newId = lastDoctor.id + 1; // Calculate the new id
        // Update the doctor with the new id
        doctor.id = newId;
        return doctor;
      })
    );
  }

}
