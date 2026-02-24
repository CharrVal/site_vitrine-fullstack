import { ChangeDetectorRef, Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Actualite } from '../interface/actualite';
import { ActualiteService } from '../services/actualite';

@Component({
  selector: 'app-add-actualite',
  imports: [ReactiveFormsModule,],
  templateUrl: './add-actualite.html',
  styleUrl: './add-actualite.css',
})
export class AddActualite {
  successMessage: string = '';
  errorMessage: string = '';
  actualite?: Actualite;
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

    const actualite = {
      title: formValue.title,
      description: formValue.description,
    };

    const formData = new FormData();
    formData.append('actualite', new Blob([JSON.stringify(actualite)], {
      type: 'application/json'
    })
  );

    this.actualiteService.create(formData).subscribe({
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
