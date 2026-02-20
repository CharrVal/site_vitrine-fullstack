import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { UserLoggedService } from '../pages/connexion-2/services/user-logged-service';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {

  constructor(public userLoggedService: UserLoggedService, private router: Router) {}

   logout() {
    this.userLoggedService.logout();
    sessionStorage.removeItem('Jwt'); 
    sessionStorage.removeItem('user');

    this.router.navigate(['/accueil']);
  }
}
