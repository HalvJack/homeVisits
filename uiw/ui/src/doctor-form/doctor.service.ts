import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Doctor} from "./doctor";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  private apiUrl = 'http://localhost:8080/location'; // Adjust as needed

  constructor(private http: HttpClient) {
  }

  public findAvailableDoctors(location: {
    latitude: number,
    longitude: number,
    specialization: string,
  }):
    Observable<Doctor[]> {
    return this.http.post<Doctor[]>(this.apiUrl, location);
  }
}
