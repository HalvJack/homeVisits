export class Patient {
  id: number = 1;
  name: string = '';
  surname: string = '';
  phoneNumber: string= '';
  pesel: string = '';
  email: string = '';
  address: string = '';
  birthDate: string = '';
  importance: string = '';
  specialization: string = '';
  comments: string = '';


  constructor(id: number, name: string, surname: string, phoneNumber: string, pesel: string,
              email: string, address: string, birthDate: string, importance: string, specialization: string,
              comments: string) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.phoneNumber = phoneNumber;
    this.pesel = pesel;
    this.email = email;
    this.address = address;
    this.birthDate = birthDate;
    this.importance = importance;
    this.specialization = specialization;
    this.comments = comments;
  }

}
