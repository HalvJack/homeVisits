export class Doctor {
  name: string = '';
  surname: string = '';
  phoneNumber: string = '';
  specialization: string = '';
  email: string = '';
  latitude: number = 15.07;
  longitude: number = 10.52;

  constructor(name: string, surname: string, phoneNumber: string, specialization: string, email: string,
              latitude: number, longitude: number) {
    this.name = name;
    this.surname = surname;
    this.phoneNumber = phoneNumber;
    this.specialization = specialization;
    this.email = email;
    this.latitude = latitude;
    this.longitude = longitude;
  }
}


