export class Location{
  id: number= 1;
  longitude: number = 1;
  latitude: number = 1;
  importance: Importance = Importance.MINOR;

  constructor(id: number, longitude: number, latitude: number, importance: Importance) {
    this.id = id;
    this.longitude = longitude;
    this.latitude = latitude;
    this.importance = importance;
  }
}
