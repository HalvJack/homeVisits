import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Location} from "./location";
import {map, Observable} from "rxjs";
import {switchMap} from "rxjs";
import {Doctor} from "../doctor-form/doctor";

@Injectable({
  providedIn: 'root'
})

export class LocationService {

  private locationUrl: string;
  private availableDoctors: string;

  constructor(private http: HttpClient) {
    this.locationUrl = 'http://localhost:8080/location';
    this.availableDoctors = 'http://localhost:8080/availableDoctors'
  }

  public availableDoctorsList(): Observable<Doctor[]> {
    return this.http.get<Doctor[]>(this.availableDoctors);
  }

  public saveLocation(location: Location): Observable<Doctor[]> {
    return this.http.post(this.locationUrl, location).pipe(
      switchMap(() => {
        return this.availableDoctorsList();
      })
    )}


}
