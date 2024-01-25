import {Location} from "../mapposition/location";

export class Doctor {
  name: string = '';
  surname: string = '';
  phoneNumber: string = '';
  specialization: string = '';
  email: string = '';
  location: Location;

  constructor(name: string, surname: string, phoneNumber: string, specialization: string, email: string,
              location: Location) {
    this.name = name;
    this.surname = surname;
    this.phoneNumber = phoneNumber;
    this.specialization = specialization;
    this.email = email;
    this.location = location;
  }
}


