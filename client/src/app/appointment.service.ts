import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Appointment} from "./appointment";
import {Address} from "./address";

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private appointmentUrl: string;
  constructor(private http: HttpClient) {
    this.appointmentUrl = 'http://localhost:8080/appointment';
  }

  public saveAppointment(appointment: {
    comments: string;
    importance: string;
    patient: {
      phoneNumber: string;
      address: Address;
      surname: string;
      name: string;
      dateOfBirth: string;
      pesel: string;
      email: string
    };
    specialization: string
  }): Observable<Appointment> {
    return this.http.post<Appointment>(this.appointmentUrl, appointment);
  }
}
