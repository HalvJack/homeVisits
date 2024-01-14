import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Appointment} from "./appointment";

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private appointmentUrl: string;
  constructor(private http: HttpClient) {
    this.appointmentUrl = 'http://localhost:8080/appointment';
  }

  public saveAppointment(appointment: Appointment): Observable<Appointment> {
    return this.http.post<Appointment>(this.appointmentUrl, appointment);
  }
}
