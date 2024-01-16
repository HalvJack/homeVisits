import {Component, OnInit} from '@angular/core';
import {Patient} from "./patient";
import {ActivatedRoute, Router} from "@angular/router";
import {AppointmentService} from "./appointment.service";
import {Appointment} from "./appointment";
import {Address} from "./address";
import {GeolocationService} from "./geolocation.service";
import {FormControl} from '@angular/forms';
import {MomentDateAdapter, MAT_MOMENT_DATE_ADAPTER_OPTIONS} from '@angular/material-moment-adapter';
import {DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE} from '@angular/material/core';
import * as _moment from 'moment';
import {default as _rollupMoment} from 'moment';

const moment = _rollupMoment || _moment;

export const MY_FORMATS = {
  parse: {
    dateInput: 'MM/DD/YYYY',
  },
  display: {
    dateInput: 'MM/DD/YYYY',
    monthYearLabel: 'MM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass'],
  providers: [
    {provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE, MAT_MOMENT_DATE_ADAPTER_OPTIONS]},
    {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS},
  ],
})
export class AppComponent implements OnInit{
  dateOfBirth = new FormControl(moment());
  title = 'jsapi-angular';
  appointment: Appointment;
  patient: Patient;
  address: Address;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private appointmentService: AppointmentService,
              private geolocationService: GeolocationService) {
    this.address = new Address('Sosnowiec', '32-210', 'mickiewicza', 1, 43);
    this.dateOfBirth = new FormControl(moment(new Date(2000, 11, 10)));
    const dob: Date = this.dateOfBirth.value ? this.dateOfBirth.value.toDate() : new Date();
    this.patient = new Patient('Emi', 'blonid', '845647265', '86011716415',
      'hsals@onet.pl', this.address, dob);
    this.appointment = new Appointment('MINOR', 'diabetologia', 'boli mnie reka',
      this.patient);
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
    this.appointmentService.saveAppointment(appointmentToSend).subscribe({
      next: (response) => {
        console.log('Appointment saved', response);
        // Handle successful response here
      },
      error: (error) => {
        console.error('Error saving patient', error);
        // Handle error here
      }
    });
  }

  ngOnInit() {
    this.geolocationService.getCurrentLocation()
      .then(position => {
        const latitude = position.coords.latitude;
        const longitude = position.coords.longitude;

        this.geolocationService.sendDataToBackend(latitude, longitude).subscribe(
          response => console.log('Location sent to the server', response),
          error => console.error('Error sending location', error)
        );
      })
      .catch(error => {
        console.error('Error occurred: ', error.message);
        // Handle the error here
      });
  }
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


