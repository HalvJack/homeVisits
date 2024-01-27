export class Address {
  city: string = '';
  zipCode: string = '';
  street: string = '';
  houseNumber: number = 23;
  flatNumber: number = 13;
  longitude: number = 15.887;
  latitude: number = 50.054;

  constructor(city: string, zipCode: string, street: string, houseNumber: number, flatNumber: number, longitude: number,
              latitude: number) {
    this.city = city;
    this.zipCode = zipCode;
    this.street = street;
    this.houseNumber = houseNumber;
    this.flatNumber = flatNumber;
    this.longitude = longitude;
    this.latitude = latitude;
  }
}

