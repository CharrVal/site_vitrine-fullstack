import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';

@Injectable({
  providedIn: 'root',
})
export class FlashMessageService {
  private successSubject = new BehaviorSubject<string>('');
  private errorSubject = new BehaviorSubject<string>('');

  success$ = this.successSubject.asObservable();
  error$ = this.errorSubject.asObservable();

  showSuccess(msg: string, duration: number = 3000) {
    this.successSubject.next(msg);
    setTimeout(() => this.successSubject.next(''), duration);
  }

  showError(msg: string, duration: number = 5000) {
    this.errorSubject.next(msg);
    setTimeout(() => this.errorSubject.next(''), duration);
  }
}
