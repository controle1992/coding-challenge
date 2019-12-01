export interface IStore {
  id?: number;
  name?: string;
  email?: string;
  pictureUrl?: string;
  city?: string;
  latitude?: number;
  longitude?: number;
  userLogin?: string;
  userId?: number;
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
    public userLogin?: string,
    public userId?: number
  ) {}
}
