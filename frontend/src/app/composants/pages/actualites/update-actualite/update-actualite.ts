import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActualiteService } from '../services/actualite';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Actualite } from '../interface/actualite';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ActualiteRequest } from '../interface/actualite-request';

@Component({
  selector: 'app-update-actualite',
  imports: [RouterModule, ReactiveFormsModule, CommonModule],
  templateUrl: './update-actualite.html',
  styleUrl: './update-actualite.css',
})
export class UpdateActualite implements OnInit {
  
  successMessage: string = '';
  errorMessage: string = '';
  actualite!: ActualiteRequest;
  actualiteForm!: FormGroup;
  actualiteId!: number;
  
  constructor (
    private actualiteService: ActualiteService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {

  }
  
  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');

    if (!idParam || isNaN(Number(idParam))) {
      this.router.navigate(['/controlPanel']);
      return;
    }
    this.actualiteId = +idParam;

    this.initForm();
    this.loadActualite();
  }

  initForm(): void {
    this.actualiteForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
    });
  }

   loadActualite(): void {
    this.actualiteService.getById(this.actualiteId).subscribe({
      next: actualite => {
        this.actualite = actualite;

        this.actualiteForm.patchValue({
          title: actualite.title,
          description: actualite.description,
        });
        this.cdr.markForCheck();
      },
      error: err => {
        console.error('Erreur chargement actualité', err);
        this.router.navigate(['/accueil']);
      }
    });
  }

    onSubmit() {
    if (this.actualiteForm.invalid) {
      this.errorMessage = 'Formulaire invalide';
      return;
    }

    const formValue = this.actualiteForm.value;
    const actualite : ActualiteRequest = {
      title: formValue.title,
      description: formValue.description
    };

  
    this.actualiteService.update(this.actualiteId, actualite).subscribe({
      next: () => this.router.navigate(['/actualites']),
      error: () => this.errorMessage = 'Erreur lors de la mise à jour'
    });
  }
  
  cancel(): void {
    this.router.navigate(['/actualites']);
  }

}
