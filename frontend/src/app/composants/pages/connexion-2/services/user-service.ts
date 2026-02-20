import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../interface/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private BASE_URL = "http://localhost:8080/users";

  constructor(private client: HttpClient) { }

  getUsers() {
    return this.client.get<User[]>(this.BASE_URL);
  }
}
