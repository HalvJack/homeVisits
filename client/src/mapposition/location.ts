export class Location{
  id: number= 1;
  longitude: number = 1;
  latitude: number = 1;

  constructor(id: number, longitude: number, latitude: number) {
    this.id = id;
    this.longitude = longitude;
    this.latitude = latitude;
  }
}
