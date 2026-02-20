import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoryService } from '../services/category';
import { ProductService } from '../../products/services/product';
import { Product } from '../../products/interface/product';
import { CategoryRequest } from '../interface/category-request';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-categorie-create',
  imports: [RouterModule, ReactiveFormsModule, CommonModule],
  templateUrl: './categorie-create.html',
  styleUrl: './categorie-create.css',
})
export class CategorieCreateComponent {

  categoryForm: FormGroup;
  products: Product[] = [];
  successMessage = '';
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private categoryService: CategoryService,
    private productService: ProductService
  ) {
    this.categoryForm = this.fb.group({
      name: ['', Validators.required],
      productIds: [[]]
    });

    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getAll().subscribe({
      next: res => this.products = res,
      error: () => this.errorMessage = 'Impossible de charger les produits'
    });
  }

  onSubmit(): void {
    if (this.categoryForm.invalid) return;

    const formValue = this.categoryForm.value;

    const categoryRequest: CategoryRequest = {
      name: formValue.name,
      productIds: formValue.productIds
    };

    this.categoryService.create(categoryRequest).subscribe({
      next: res => {
        this.successMessage = `Catégorie "${res.name}" créée avec succès !`;
        this.errorMessage = '';
        this.categoryForm.reset({ productIds: [] });
      },
      error: err => {
        console.error(err);
        this.errorMessage = 'Erreur lors de la création de la catégorie';
        this.successMessage = '';
      }
    });
  }
}
