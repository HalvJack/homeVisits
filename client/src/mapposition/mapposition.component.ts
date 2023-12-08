import { Component, Output, EventEmitter, Input } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {LocationService} from "./location.service";
import {Location} from "./location"
import {Importance} from "./importance";
import {Doctor} from "../doctor-form/doctor";

@Component({
  selector: 'app-mapposition',
  templateUrl: './mapposition.component.html',
  styleUrls: ['./mapposition.component.sass']
})
export class MappositionComponent {

  location: Location;
  doctors: Doctor[] = [];

  selectedDoctor: Doctor | null = null;
  defaultDoctor: Doctor = {
    // Define the properties of the default doctor
    id: 1,
    name: 'Default Doctor',
    specialization: 'General',
    email: 'kubah20000@wp.pl',
    surname: 'Pablo',
    phoneNumber: '1111111111'
    // ... other necessary properties
  };
  constructor(private route: ActivatedRoute,
              private router: Router,
              private locationService: LocationService) {
    this.location = new Location(1,1,1,Importance.MINOR, "dermatolog");
  }

  @Input() public zoom = 2;
  @Input() public lat = 0;
  @Input() public lng = 0;

  @Output() notify = new EventEmitter();

  onSubmit(event: Event) {
    this.locationService.saveLocation(this.location).subscribe(result => {
      this.doctors = result;
    });
  }

  onSubmitDoctorSelection() {
    const doctorToSubmit = this.selectedDoctor || this.defaultDoctor;

    this.locationService.submitDoctorChoice(doctorToSubmit).subscribe(
      response => {
        console.log('Doctor submitted successfully', response);
        // Handle successful submission here, like redirecting or showing a message
      },
      error => {
        console.error('Error submitting doctor', error);
        // Handle errors here
      }
    );

}}
