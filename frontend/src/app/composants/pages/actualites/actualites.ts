import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Actualite } from './interface/actualite';
import { ActualiteService } from './services/actualite';

@Component({
  selector: 'app-actualites',
  imports: [],
  templateUrl: './actualites.html',
  styleUrl: './actualites.css',
})
export class Actualites implements OnInit{
  actualites: Actualite[] = [];
  errorMessage: String = '';
  
  
  constructor(
    private actualiteService: ActualiteService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadActualites();
  }

  loadActualites() {
    this.actualiteService.getAll().subscribe({
      next: res => {
        this.actualites = res;
        this.cdr.markForCheck();
      },
      error: () => {
        this.errorMessage = 'Erreur lors du chargement des actualités';
        this.cdr.markForCheck();
      }
    });
  }
}
