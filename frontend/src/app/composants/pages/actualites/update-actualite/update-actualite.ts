import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActualiteService } from '../services/actualite';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Actualite } from '../interface/actualite';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-update-actualite',
  imports: [RouterModule, ReactiveFormsModule, CommonModule],
  templateUrl: './update-actualite.html',
  styleUrl: './update-actualite.css',
})
export class UpdateActualite implements OnInit {
  
  successMessage: string = '';
  errorMessage: string = '';
  actualite!: Actualite;
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
        this.router.navigate(['/controlPanel']);
      }
    });
  }

  initForm(): void {
    this.actualiteForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
    });
  }


  onSubmit() {
    if (this.actualiteForm.invalid) return;

    const formData = new FormData();

    const actualite = {
      title: this.actualiteForm.value.title,
      description: this.actualiteForm.value.description,
    };

    formData.append(
      'actualite',
      new Blob([JSON.stringify(actualite)], { type: 'application/json' })
    );


    this.actualiteService.update(this.actualiteId, formData).subscribe({
      next: () => this.router.navigate(['/productList']),
      error: () => this.errorMessage = 'Erreur lors de la mise à jour'
    });
  }
  
  cancel(): void {
    this.router.navigate(['/controlPanel']);
  }

}
