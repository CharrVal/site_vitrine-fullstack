import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Actualite } from './interface/actualite';
import { ActualiteService } from './services/actualite';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { UserLoggedService } from '../connexion-2/services/user-logged-service';
import { FlashMessageService } from '../products/services/flash-message-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-actualites',
  imports: [RouterModule, CommonModule],
  templateUrl: './actualites.html',
  styleUrl: './actualites.css',
})
export class Actualites implements OnInit{

  actualites: Actualite[] = [];
  errorMessage: String = '';
  successMessage : string = '';
  actualiteId!: number;
  
  constructor(
    private actualiteService: ActualiteService,
    public userLoggedService: UserLoggedService,
    private cdr: ChangeDetectorRef,
    private router:Router,
    private flashMessageService: FlashMessageService
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

  deleteActualite(id: number): void{
    if (!confirm('⚠️ Voulez-vous vraiment supprimer cette actualité ?')) return;

    this.actualiteService.delete(id).subscribe({
      next: () => {
        this.actualites = this.actualites.filter(a => a.id !== id);
        this.cdr.markForCheck();
        this.flashMessageService.showSuccess("Actualité supprimée avec succès !", 3000);
      },
      error: (err) => {
        console.error(err);
        this.flashMessageService.showError("Erreur lors de la suppression de l'actualité", 5000);
        this.router.navigate(['/actualites']);
        this.cdr.markForCheck();
      }
    });
  }

}
