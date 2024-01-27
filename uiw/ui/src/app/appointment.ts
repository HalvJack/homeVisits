import {Patient} from "./patient";

export class Appointment {
  importance: string = '';
  specialization: string = '';
  comments: string = '';
  patient: Patient;

  constructor(importance: string, specialization: string,
              comments: string, patient: Patient) {
    this.importance = importance;
    this.specialization = specialization;
    this.comments = comments;
    this.patient = patient;
  }
}
