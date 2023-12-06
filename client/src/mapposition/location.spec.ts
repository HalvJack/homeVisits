import { Location } from './location';

describe('Location', () => {
  it('should create an instance', () => {
    expect(new Location(1,1,1, Importance.MINOR)).toBeTruthy();
  });
});
