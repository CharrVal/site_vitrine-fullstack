import { ChangeDetectorRef, Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActualiteService } from '../services/actualite';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ActualiteRequest } from '../interface/actualite-request';

@Component({
  selector: 'app-add-actualite',
  imports: [ReactiveFormsModule, RouterModule, CommonModule],
  templateUrl: './add-actualite.html',
  styleUrl: './add-actualite.css',
})
export class AddActualite {
  successMessage: string = '';
  errorMessage: string = '';
  actualiteForm: FormGroup;

  constructor(
    private actualiteService: ActualiteService,
    private fb: FormBuilder,
    private cdr: ChangeDetectorRef
  ) {
    this.actualiteForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
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

    this.actualiteService.create(actualite).subscribe({
      next: res => {
        this.successMessage = `Actualité "${res.title}" créé avec succès !`;
        this.cdr.markForCheck();
      },
      error: err => {
      console.error(err);
      this.errorMessage = "Erreur lors de la création de l'actualité";
      }
    });
  }
}
