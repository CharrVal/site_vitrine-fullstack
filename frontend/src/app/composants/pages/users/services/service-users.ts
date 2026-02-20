import { Injectable } from '@angular/core';
import { User } from '../../connexion-2/interface/user';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ServiceUsers {
  private apiUrl = `http://localhost:8080/api/users`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<User[]> {
    return this.http.get<User[]>(this.apiUrl);
  }

}
