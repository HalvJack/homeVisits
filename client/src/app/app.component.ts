import {Component, OnInit} from '@angular/core';
import {Patient} from "./patient";
import {ActivatedRoute, Router} from "@angular/router";
import {AppointmentService} from "./appointment.service";
import {Appointment} from "./appointment";
import {Address} from "./address";
import {GeolocationService} from "./geolocation.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass'],
})
export class AppComponent implements OnInit{
  title = 'jsapi-angular';
  appointment: Appointment;
  patient: Patient;
  address: Address;
  dateOfBirth: Date;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private appointmentService: AppointmentService,
              private geolocationService: GeolocationService) {
    this.address = new Address('Sosnowiec', '32-210', 'mickiewicza', 1, 43);
    this.dateOfBirth = new Date(2000-12-10);
    this.patient = new Patient('Emi', 'blonid', '845647265', '86011716415',
      'hsals@onet.pl', this.address, this.dateOfBirth);
    this.appointment = new Appointment('MINOR', 'diabetologia', 'boli mnie reka',
      this.patient);
  }

  onSubmit() {
    console.log(this.appointment);
    this.appointmentService.saveAppointment(this.appointment).subscribe({
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


