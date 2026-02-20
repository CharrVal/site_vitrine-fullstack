import { Injectable } from '@angular/core';
import { User } from '../interface/user';

@Injectable({
  providedIn: 'root',
})
export class UserLoggedService {

  public user?: User;

  constructor() {
    const storedUser = sessionStorage.getItem('user');
    if (storedUser) {
      this.user = JSON.parse(storedUser);
    }
  }

  get isLoggedIn(): boolean {
    return !!this.user;
  }

  setUser(user: User) {
    this.user = user;
    sessionStorage.setItem('user', JSON.stringify(user));
  }

  logout() {
    this.user = undefined;
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('Jwt');
  }
}

