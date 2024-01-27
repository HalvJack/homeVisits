import {Address} from "./address";

export class Patient {
  name: string = '';
  surname: string = '';
  phoneNumber: string = '';
  pesel: string = '';
  email: string = '';
  address: Address;
  dateOfBirth: Date;


  constructor(name: string, surname: string, phoneNumber: string, pesel: string,
              email: string, address: Address, dateOfBirth: Date) {
    this.name = name;
    this.surname = surname;
    this.phoneNumber = phoneNumber;
    this.pesel = pesel;
    this.email = email;
    this.address = address;
    this.dateOfBirth = dateOfBirth;
  }
}
