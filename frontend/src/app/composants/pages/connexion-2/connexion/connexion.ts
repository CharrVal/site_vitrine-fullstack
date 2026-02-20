import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../services/auth-service';
import { UserLogged } from '../interface/user-logged';
import { UserLoggedService } from '../services/user-logged-service';
import { Token } from '@angular/compiler';

@Component({
  selector: 'app-connexion',
  imports: [RouterModule, CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './connexion.html',
  styleUrl: './connexion.css',
})
export class Connexion {
  form : FormGroup;
  errorMessage: string | null = null;
  userLogged?: UserLogged;
  showPassword: any;

  constructor(private fb : FormBuilder,
              private router : Router,
              private authService : AuthService,
              private userLoggedService: UserLoggedService) {
              this.form = this.fb.group({
                  username: ['',Validators.required],
                  password: ['',Validators.required]
              });
  }

  connecter() {
    const val = this.form.value;
    if (val.username && val.password) {
    this.authService.connexion(val.username, val.password)
      .subscribe({
        next: response => {
          sessionStorage.setItem('Jwt', response.token);
          if (response.user) {
            this.userLoggedService.setUser(response.user);
          } else {
            console.warn('Utilisateur non présent dans la réponse !');
          }
          this.router.navigate(['/accueil']);
        },
        error: () => {
          this.errorMessage = "Identifiant ou mot de passe incorrect.";
        }
      });
    }
  }

}
