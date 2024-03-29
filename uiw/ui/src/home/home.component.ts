import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, NavigationExtras, Router} from "@angular/router";

import {FormControl} from '@angular/forms';
import {MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS} from '@angular/material-moment-adapter';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import * as _moment from 'moment';
import moment, {default as _rollupMoment} from 'moment';
import {error} from "@angular/compiler-cli/src/transformers/util";
import {DoctorService} from "../doctor-form/doctor.service";
import {LocationService} from "../doctor-form/location.service";
import {Doctor} from "../doctor-form/doctor";
import { Patient } from 'src/app/patient';
import { Address } from 'src/app/address';
import { Appointment } from 'src/app/appointment';
import {AppointmentService} from "../app/appointment.service";
import {GeolocationService} from "../app/geolocation.service";
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {
  dateOfBirth = new FormControl(moment());
  title = 'jsapi-angular';
  appointment: Appointment;
  patient: Patient;
  address: Address;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private appointmentService: AppointmentService,
              private geolocationService: GeolocationService,
              private doctorService: DoctorService,
              private locationService: LocationService) {
    this.address = new Address('Sosnowiec', '32-210', 'mickiewicza', 1, 43,
      15.887, 50.054);
    this.dateOfBirth = new FormControl(moment(new Date(2000, 11, 10)));
    const dob: Date = this.dateOfBirth.value ? this.dateOfBirth.value.toDate() : new Date();
    this.patient = new Patient('Emi', 'blonid', '845647265', '86011716415',
      'hsals@onet.pl', this.address, dob);
    this.appointment = new Appointment('MINOR', 'diabetologia', 'boli mnie reka',
      this.patient);
  }

  getSpecialization(): { specialization: string } {
    return {specialization: this.appointment.specialization};
  }

  onSubmit() {
    const formattedDOB = moment(this.patient.dateOfBirth).format('YYYY-MM-DD');
    const patientToSend = {
      ...this.patient,
      dateOfBirth: formattedDOB
    };
    // Create a new appointment object with the updated patient information
    const appointmentToSend = {
      ...this.appointment,
      patient: patientToSend
    };

    console.log(this.appointment);
    const locationDto = {
      latitude: this.appointment.patient.address.latitude,
      longitude: this.appointment.patient.address.longitude,
      specialization: this.appointment.specialization
    };

    this.doctorService.findAvailableDoctors(locationDto).subscribe({
      next: (doctorsResponse) => {
        console.log('Available doctors', doctorsResponse);

        //     Continue with saving the appointment and then redirect
        this.appointmentService.saveAppointment(appointmentToSend).subscribe({
          next: (appointmentResponse) => {
            console.log('Appointment saved', appointmentResponse);

            // Prepare navigation with both doctors and appointment responses
            const navigationExtras: NavigationExtras = {
              state: {
                appointment: appointmentResponse,
                availableDoctors: doctorsResponse
              }
            };
            console.log(navigationExtras);
            this.router.navigate(['/app-doctor-form'], navigationExtras);
          },
        });
      },
    });
  }

  ngOnInit() {
    this.obtainAndSendLocation().then(() => {
      this.locationService.setLocation(this.appointment.patient.address.latitude,
        this.appointment.patient.address.longitude);
    }).catch(error => {
      console.error('Error in obtainAndSendLocation:', error);

    });
  }

  async obtainAndSendLocation() {
    try {
      const position = await this.geolocationService.getCurrentLocation();
      this.updatePatientLocation(position.coords.latitude, position.coords.longitude);
    } catch (error) {
      if (error instanceof Error) {
        console.error('Error occurred: ', error.message);
        // Additional error handling if required
      } else {
        // Handle non-Error objects
        console.error('An unknown error occurred');
      }
    }
  }

  updatePatientLocation(latitude: number, longitude: number) {
    this.appointment.patient.address.latitude = 45.7214687;
    this.appointment.patient.address.longitude = 7.3957376;
  }

  protected readonly name = name;
}

/*constructor() {
  this.zoom = 2;
  this.lat = 0;
  this.lng = 0;
}

zoom: number;
lat: number;
lng: number;

handleInputChange(event: Event) {
  const target = <HTMLInputElement> event.target;
  if (target) {
    if (target.name === 'zoom') {
      this.zoom = parseFloat(target.value);
    }
    if (target.name === 'lat') {
      this.lat = parseFloat(target.value);
    }
    if (target.name === 'lng') {
      this.lng = parseFloat(target.value);
    }
  }
}

handleMapChange(event: H.map.ChangeEvent) {
  if (event.newValue.lookAt) {
    const lookAt = event.newValue.lookAt;
    this.zoom = lookAt.zoom;
    this.lat = lookAt.position.lat;
    this.lng = lookAt.position.lng;
  }
}*/




