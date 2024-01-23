import {Importance} from "./importance";

export class Location{
  longitude: number = 15.887;
  latitude: number = 50.054;
  specialization: string = 'Diabetologia';

  constructor(longitude: number, latitude: number, specialization: string) {
    this.longitude = longitude;
    this.latitude = latitude;
    this.specialization = specialization;
  }
}
