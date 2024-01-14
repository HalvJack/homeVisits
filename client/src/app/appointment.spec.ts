import { Appointment } from './appointment';
import {Patient} from "./patient";
import {Address} from "./address";

describe('Appointment', () => {
  it('should create an instance', () => {
    expect(new Appointment(1, '', '', '', new Patient(1, '', '', '',
      '', '', new Address(1, '', '', '', 12, 23), ''))).toBeTruthy();
  });
});
