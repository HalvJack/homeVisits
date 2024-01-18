import { Location } from './location';
import { Importance} from "./importance";

describe('Location', () => {
  it('should create an instance', () => {
    expect(new Location(1,1,1, Importance.MINOR)).toBeTruthy();
  });
});
