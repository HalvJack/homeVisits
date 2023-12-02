import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MainService} from "../main/main.service";
import {Doctor} from "./doctor";

@Component({
  selector: 'app-doctor-form',
  templateUrl: './doctor-form.component.html',
  styleUrls: ['./doctor-form.component.sass']
})
export class DoctorFormComponent {
  doctor: Doctor;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private mainService: MainService) {
    this.doctor = new Doctor(1, 'SDv', 'fdv','11111111111', 'fdz', 'kubah20000@wp.pl');
  }

  onSubmit(){
    this.mainService.saveDoctor(this.doctor).subscribe(result => this.goToDoctorList());
  }


  private goToDoctorList() {
    this.router.navigate(['/doctor']);
  }
}
