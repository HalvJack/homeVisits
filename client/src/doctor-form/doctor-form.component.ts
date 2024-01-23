import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Doctor} from "./doctor";
import {DoctorService} from "./doctor.service";
import {GeolocationService} from "../app/geolocation.service";
import {LocationService} from "./location.service";
import {Location} from "../mapposition/location";

@Component({
  selector: 'app-doctor-form',
  templateUrl: './doctor-form.component.html',
  styleUrls: ['./doctor-form.component.sass']
})
export class DoctorFormComponent {
  doctors!: Doctor[];
  location: Location;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private doctorService: DoctorService,
              private locationService: LocationService) {
    this.location = new Location(1,1, "Diabetologia");
  }

  ngOnInit() {
    this.location = this.locationService.getLocation();
    this.doctorService.findAvailableDoctors(this.location).subscribe({
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
