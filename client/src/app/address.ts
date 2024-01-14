export class Address {
  id: number = 1;
  city: string = '';
  zipCode: string = '';
  street: string = '';
  houseNumber: number = 23;
  flatNumber: number = 13;

  constructor(id: number, city: string, zipCode: string, street: string, houseNumber: number, flatNumber: number) {
    this.id = id;
    this.city = city;
    this.zipCode = zipCode;
    this.street = street;
    this.houseNumber = houseNumber;
    this.flatNumber = flatNumber;
  }
}

