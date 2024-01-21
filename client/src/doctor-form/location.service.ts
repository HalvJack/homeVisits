import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  private latitude: number = 0;
  private longitude: number = 0;

  setLocation(latitude: number, longitude: number): void {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  getLocation(): { latitude: number, longitude: number } {
    return {latitude: this.latitude, longitude: this.longitude};
  }
}
