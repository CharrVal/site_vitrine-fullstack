import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../interface/product'
import { ProductRequest } from '../interface/product-request';

@Injectable({
providedIn: 'root'
})
export class ProductService {

private apiUrl = `http://localhost:8080/api/products`;

constructor(private http: HttpClient) {}

  getAll(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl);
  }

  getById(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/${id}`);
  }

  getImageUrl(path: string): string {
    return `http://localhost:8080/images/${path}`;
  }

  getByCategory(categoryId: number): Observable<Product[]> {
    return this.http.get<Product[]>(`http://localhost:8080/api/categories/${categoryId}/products`);
  }

  create(formData: FormData) {
    console.log('HTTP POST SENT');
    return this.http.post<Product>(this.apiUrl, formData);
  }

  update(id: number, formData: FormData): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, formData);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

}

