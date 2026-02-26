import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Actualite } from '../interface/actualite';
import { Observable } from 'rxjs';
import { ActualiteRequest } from '../interface/actualite-request';

@Injectable({
  providedIn: 'root',
})
export class ActualiteService {
  private apiUrl = `http://localhost:8080/api/actualites`;

  constructor(private http: HttpClient) {}

  getById(id:number): Observable<Actualite>{
    return this.http.get<Actualite>(`${this.apiUrl}/${id}`);
  }

  getAll(): Observable<Actualite[]> {
      return this.http.get<Actualite[]>(this.apiUrl);
  }

  create(actualite: ActualiteRequest): Observable<Actualite> {
    return this.http.post<Actualite>(this.apiUrl, actualite);
  }

  update(id:number, actualite: ActualiteRequest): Observable<Actualite> {
    return this.http.put<Actualite>(`${this.apiUrl}/${id}`, actualite);
  }

  delete(id:number) {
    return this.http.delete<Actualite>(`${this.apiUrl}/${id}`);
  }

}
