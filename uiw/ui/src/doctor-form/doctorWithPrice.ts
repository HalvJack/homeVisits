import {Doctor} from "./doctor";

export class DoctorWithPrice {
  doctor: Doctor;
  price: number;

  constructor(doctor: Doctor, price: number) {
    this.doctor = doctor;
    this.price = price;
  }
}
