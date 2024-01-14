import {Component} from '@angular/core';
import {Patient} from "./patient";
import {ActivatedRoute, Router} from "@angular/router";
import {AppointmentService} from "./appointment.service";
import {Appointment} from "./appointment";
import {Address} from "./address";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass'],
})
export class AppComponent {
  title = 'jsapi-angular';
  appointment: Appointment;
  patient: Patient;
  address: Address;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private appointmentService: AppointmentService) {
    this.address = new Address(1, 'Sosnowiec', '32-210', 'mickiewicza', 12, 43);
    this.patient = new Patient(1, 'Emi', 'blonid', '845647265', '33521199552',
      'hsals@onet.pl', this.address, '11-JAN-2001');
    this.appointment = new Appointment(1, 'MINOR', 'diabetologia', 'boli mnie reka',
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


