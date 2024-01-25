export class AppointmentDetails {
  longitude: number = 15.887;
  latitude: number = 50.054;
  specialization: string = '';

  constructor(latitude: number, longitude: number, specialization: string){
    this.latitude = latitude;
    this.longitude = longitude;
    this.specialization = specialization;
  }
}
