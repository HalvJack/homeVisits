import { Doctor } from './doctor';

describe('Doctor', () => {
  it('should create an instance', () => {
    expect(new Doctor('', '', '','','',15.07, 10.52))
      .toBeTruthy();
  });
});
