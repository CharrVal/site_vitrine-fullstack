import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FlashMessageService } from '../pages/products/services/flash-message-service';
import { Contact } from './interface/contact';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-footer',
  imports: [RouterModule, CommonModule, ReactiveFormsModule],
  templateUrl: './footer.html',
  styleUrl: './footer.css',
})
export class Footer implements OnInit{
  
  contactForm!: FormGroup;

  constructor(private http: HttpClient, private flashMessageService: FlashMessageService, private fb: FormBuilder,) {}

  ngOnInit () {
    this.contactForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      message: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.contactForm.invalid) {
      this.flashMessageService.showError('Veuillez remplir correctement tous les champs.', 5000);
      return;
    }

    this.http.post('http://localhost:8080/api/contact', this.contactForm.value).subscribe({
      next: () => {
        this.flashMessageService.showSuccess('Message envoyé avec succès !', 3000);
        this.contactForm.reset();
      },
      error: () => {
        this.flashMessageService.showError('Erreur lors de l\'envoi du message.', 5000);
      }
    });
  }

}
