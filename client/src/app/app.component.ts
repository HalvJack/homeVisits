import { Component } from '@angular/core';
import {Patient} from "./patient";
import {ActivatedRoute, Router} from "@angular/router";
import {PatientService} from "./patient.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass'],
})
export class AppComponent {
  title = 'jsapi-angular';
  patient: Patient;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private patientService: PatientService) {
    this.patient = new Patient(1, 'Edi', 'Mother', '535130943', '93519933854',
      'mother2000@wp.pl', 'krakow ul. mickiewicza', '13.04.2014', 'minor',
      'diabetologia', 'dolega mi bol tarczycy i brak insuliny');
  }

  onSubmit() {
    console.log(this.patient);
    this.patientService.savePatient(this.patient);
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

}
