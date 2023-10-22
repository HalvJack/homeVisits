export class Doctor {
  id: number = 1;
  name: string = '';
  surname: string = '';
  phoneNumber: string= '';
  specialization: string= '';
  email: string= '';

  constructor(id: number, name: string, surname: string, phoneNumber: string, specialization: string, email: string) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.phoneNumber = phoneNumber;
    this.specialization = specialization;
    this.email = email;
  }

}

