export class Address {
  city: string = '';
  zipCode: string = '';
  street: string = '';
  houseNumber: number = 23;
  flatNumber: number = 13;

  constructor(city: string, zipCode: string, street: string, houseNumber: number, flatNumber: number) {
    this.city = city;
    this.zipCode = zipCode;
    this.street = street;
    this.houseNumber = houseNumber;
    this.flatNumber = flatNumber;
  }
}

