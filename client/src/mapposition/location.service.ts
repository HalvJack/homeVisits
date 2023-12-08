import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Location} from "./location";
import {Observable} from "rxjs";
import {Doctor} from "../doctor-form/doctor";

@Injectable({
  providedIn: 'root'
})

export class LocationService {

  private locationUrl: string;
  private submitDoctorUrl: string;

  constructor(private http: HttpClient) {
    this.locationUrl = 'http://localhost:8080/location';
    this.submitDoctorUrl = 'http://localhost:8080/submittedDoctor';
  }


  public saveLocation(location: Location): Observable<Doctor[]> {
    return this.http.post<Doctor[]>(this.locationUrl, location);
  }

  public submitDoctorChoice(doctor: Doctor): Observable<Doctor> {
    return this.http.post<Doctor>(this.submitDoctorUrl, doctor);
  }
}
