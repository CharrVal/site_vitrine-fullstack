import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { User } from '../../connexion-2/interface/user';
import { ServiceUsers } from '../services/service-users';

@Component({
  selector: 'app-list-users',
  imports: [RouterModule, CommonModule],
  templateUrl: './list-users.html',
  styleUrl: './list-users.css',
})
export class ListUsers implements OnInit {

  users: User[] = [];
  errorMessage: string = '';

  constructor(
    private userService: ServiceUsers,
    private cdr: ChangeDetectorRef,
    private router: Router
  ) {}

    ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getAll().subscribe({
      next: res => {
        this.users = res;
        this.cdr.markForCheck();
      },
      error: () => {
        this.errorMessage = 'Erreur lors du chargement des utilisateurs';
        this.cdr.markForCheck();
      }
    });
  }
}