import { Appointment } from './appointment';
import {Patient} from "./patient";
import {Address} from "./address";

describe('Appointment', () => {
  it('should create an instance', () => {
    expect(new Appointment('', '', '', new Patient('', '', '',
      '', '', new Address('', '', '', 12, 23), ''))).toBeTruthy();
  });
});
