import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Doctor} from "./doctor";
import {DoctorService} from "./doctor.service";
import {GeolocationService} from "../app/geolocation.service";
import {LocationService} from "./location.service";
import {Location} from "../mapposition/location";
import {AppointmentDetails} from "./appointment-details";
import {SelectionModel} from "@angular/cdk/collections";
import {DoctorWithPrice} from "./doctorWithPrice";

@Component({
  selector: 'app-doctor-form',
  templateUrl: './doctor-form.component.html',
  styleUrls: ['./doctor-form.component.sass']
})
export class DoctorFormComponent {
  doctors!: Doctor[];
  appointmentDetails: AppointmentDetails;
  displayedColumns: string[] = ['select', 'name', 'surname', 'email', 'phoneNumber', 'specialization', 'latitude', 'longitude', 'price'];
  selection = new SelectionModel<DoctorWithPrice>(true, []);
  availableDoctors: DoctorWithPrice[] | undefined;


  constructor(private route: ActivatedRoute,
              private router: Router,
              private doctorService: DoctorService,
              private locationService: LocationService) {
    this.appointmentDetails = new AppointmentDetails(1,1, "Diabetologia");
  }


  onSubmit(){
    const selectedDoctors = this.selection.selected;

    const doctorToSend = selectedDoctors[0];
    this.sendDoctorData(doctorToSend);
    this.router.navigate(['/app-jsmap']);
  }

  sendDoctorData(doctorData: DoctorWithPrice) {
    this.doctorService.sendDoctor(doctorData).subscribe({
      next: (response) => {
        // Handle successful response
        console.log('Doctor data sent successfully', response);
        // Optional: Navigate to another page or show success message
      },
      error: (error) => {
        // Handle error
        console.error('Error sending doctor data', error);
      }
    });
  }
  ngOnInit() {
      if (history.state.availableDoctors) {
        this.availableDoctors = history.state.availableDoctors.map((item: any) =>
          new DoctorWithPrice(
            new Doctor(
              item.doctor.name,
              item.doctor.surname,
              item.doctor.phoneNumber,
              item.doctor.specialization,
              item.doctor.email,
              new Location(item.doctor.latitude, item.doctor.longitude)
            ),
            item.appointmentPrice
          )
        );
        console.log('Deserialized availableDoctors:', this.availableDoctors);
      }
    }


  }
