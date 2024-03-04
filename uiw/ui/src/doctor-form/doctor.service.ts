import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Doctor} from "./doctor";
import {Observable} from "rxjs";
import {Address} from "../app/address";
import {Appointment} from "../app/appointment";
import {Location} from "../mapposition/location";
import {DoctorWithPrice} from "./doctorWithPrice";

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  private apiUrl = 'http://localhost:8080/location'; // Adjust as needed
  private doctorUrl: string;

  constructor(private http: HttpClient) {
    this.doctorUrl = 'http://localhost:8080/submittedDoctor';
  }

  public sendDoctor(doctorWithPrice: DoctorWithPrice): Observable<DoctorWithPrice> {
    return this.http.post<DoctorWithPrice>(this.doctorUrl, doctorWithPrice);
  }

  public findAvailableDoctors(location: {
    latitude: number,
    longitude: number,
    specialization: string,
  }):
    Observable<DoctorWithPrice[]> {
    return this.http.post<DoctorWithPrice[]>(this.apiUrl, location);
  }
}
