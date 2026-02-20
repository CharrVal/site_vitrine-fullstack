import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserLoggedService } from '../pages/connexion-2/services/user-logged-service';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, CommonModule, ReactiveFormsModule],
  templateUrl: './navbar.html',
  styleUrls: ['./navbar.css'],
})
export class Navbar {

  constructor(
    public userLoggedService: UserLoggedService,
    private router: Router,
  ) {}

  logout() {
    this.userLoggedService.logout();
    sessionStorage.removeItem('Jwt'); 
    sessionStorage.removeItem('user');
    this.router.navigate(['/accueil']);
  }
}
