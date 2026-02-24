import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Actualite } from '../interface/actualite';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ActualiteService {
  private apiUrl = `http://localhost:8080/api/actualites`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Actualite[]> {
      return this.http.get<Actualite[]>(this.apiUrl);
  }

  update(id:number, actualite: Actualite): Observable<Actualite> {
    return this.http.put<Actualite>(`${this.apiUrl}/${id}`, actualite);
  }

}
