import {Importance} from './importance';
export class Location{
  id: number= 1;
  longitude: number = 15.887;
  latitude: number = 50.054;
  importance: Importance = Importance.MINOR;
  specialization: string = "diabetolog";

  constructor(id: number, longitude: number, latitude: number, importance: Importance, specialization: string) {
    this.id = id;
    this.longitude = longitude;
    this.latitude = latitude;
    this.importance = importance;
    this.specialization = specialization;
  }
}
