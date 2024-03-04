import {Doctor} from "./doctor";

export class DoctorWithPrice {
  price: number;
  doctor: Doctor;


  constructor(doctor: Doctor, price: number) {
    this.doctor = doctor;
    this.price = price;
  }
}
