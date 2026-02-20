import { ChangeDetectorRef, Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from '../services/product';
import { CategoryService } from '../../categories/services/category';
import { Category } from '../../categories/interface/category';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-create',
  imports: [RouterModule, ReactiveFormsModule, CommonModule],
  templateUrl: './product-create.html',
  styleUrl: './product-create.css',
})
export class ProductCreateComponent {

  productForm: FormGroup;
  categories: Category[] = [];
  selectedFiles: File[] = [];
  previewImages: string[] = [];
  previewImage: string | ArrayBuffer | null = null;
  successMessage = '';
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private categoryService: CategoryService,
    private cdr: ChangeDetectorRef
  ) {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: [0, [Validators.required, Validators.min(0)]],
      categoryId: [null, Validators.required]
    });

    this.loadCategories();
  }

  loadCategories(): void {
    this.categoryService.getAll().subscribe({
      next: res => this.categories = res,
      error: () => this.errorMessage = 'Impossible de charger les catégories'
    });
  }

  onSubmit(): void {

  if (this.productForm.invalid) {
    this.errorMessage = 'Formulaire invalide';
    return;
  }

  if (this.selectedFiles.length === 0) {
    this.errorMessage = 'Veuillez sélectionner au moins une image';
    return;
  }

  const formValue = this.productForm.value;

  const productRequest = {
    name: formValue.name,
    description: formValue.description,
    price: formValue.price,
    categoryId: formValue.categoryId
  };

  const formData = new FormData();

  formData.append(
    'product',
    new Blob([JSON.stringify(productRequest)], {
      type: 'application/json'
    })
  );

  this.selectedFiles.forEach(file => {
    formData.append('images', file);
  });

  this.productService.create(formData).subscribe({
    next: res => {
      this.successMessage = `Produit "${res.name}" créé avec succès !`;
      this.errorMessage = '';
      this.productForm.reset();
      this.selectedFiles = [];
      this.cdr.markForCheck();
    },
    error: err => {
      console.error(err);
      this.errorMessage = 'Erreur lors de la création du produit';
      this.successMessage = '';
    }
  });

  }

  onFileSelected(event: Event): void {
  const target = event.target as HTMLInputElement;
  if (!target.files) return;

  
  this.selectedFiles = Array.from(target.files);
  this.previewImages = [];

  for (let file of this.selectedFiles) {
    const reader = new FileReader();
    reader.onload = (e: any) => {
      this.cdr.markForCheck();
      this.previewImages.push(e.target.result);
    };
    reader.readAsDataURL(file);
  }
}
}
