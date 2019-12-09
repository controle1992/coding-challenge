import { IUser } from 'app/core/user/user.model';

export interface IStore {
  id?: number;
  name?: string;
  email?: string;
  pictureUrl?: string;
  city?: string;
  latitude?: number;
  longitude?: number;
  users?: IUser[];
}

export class Store implements IStore {
  constructor(
    public id?: number,
    public name?: string,
    public email?: string,
    public pictureUrl?: string,
    public city?: string,
    public latitude?: number,
    public longitude?: number,
    public users?: IUser[]
  ) {}
}
