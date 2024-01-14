import {Patient} from "./patient";

export class Appointment {
  id: number = 1;
  importance: string = '';
  specialization: string = '';
  comments: string = '';
  patient: Patient;

  constructor(id: number, importance: string, specialization: string,
              comments: string, patient: Patient) {
    this.id = id;
    this.importance = importance;
    this.specialization = specialization;
    this.comments = comments;
    this.patient = patient;
  }
}
