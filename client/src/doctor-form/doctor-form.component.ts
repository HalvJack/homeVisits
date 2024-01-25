import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Doctor} from "./doctor";
import {DoctorService} from "./doctor.service";
import {GeolocationService} from "../app/geolocation.service";
import {LocationService} from "./location.service";
import {Location} from "../mapposition/location";
import {AppointmentDetails} from "./appointment-details";

@Component({
  selector: 'app-doctor-form',
  templateUrl: './doctor-form.component.html',
  styleUrls: ['./doctor-form.component.sass']
})
export class DoctorFormComponent {
  doctors!: Doctor[];
  appointmentDetails: AppointmentDetails;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private doctorService: DoctorService,
              private locationService: LocationService) {
    this.appointmentDetails = new AppointmentDetails(1,1, "Diabetologia");
  }

  ngOnInit() {
    this.appointmentDetails.latitude = this.locationService.getLocation().latitude;
    this.doctorService.findAvailableDoctors(this.appointmentDetails).subscribe({
      next: (doctors) => {
        this.doctors = doctors;
      },
      error: (error) => {
        console.error('Error fetching doctors:', error);
        // Handle error appropriately
      }
    });
  }

}
