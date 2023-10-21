import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Doctor} from "../../doctor";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MainService {
  private doctorUrl: string;

  constructor(private http: HttpClient) {
    this.doctorUrl = 'http://localhost:8080/doctor';
  }

  public findAllDoctors(): Observable<Doctor[]>{
    return this.http.get<Doctor[]>(this.doctorUrl);
  }

  public saveDoctor(doctor: Doctor){
    return this.http.post<Doctor>(this.doctorUrl, doctor);
  }

}
