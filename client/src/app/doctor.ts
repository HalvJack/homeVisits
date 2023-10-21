export class Doctor {
  id: string = '';
  name: string = '';
  surname: string = '';
  phoneNumber: string= '';
  specialization: string= '';
  email: string= '';

  constructor(id: string, name: string, surname: string, phoneNumber: string, specialization: string, email: string) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.phoneNumber = phoneNumber;
    this.specialization = specialization;
    this.email = email;
  }

}

