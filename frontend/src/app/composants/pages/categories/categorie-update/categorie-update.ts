import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoryService } from '../services/category';
import { ProductService } from '../../products/services/product';
import { CategoryRequest } from '../interface/category-request';
import { Product } from '../../products/interface/product';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-category-update',
  imports: [RouterModule, ReactiveFormsModule, CommonModule],
  templateUrl: './categorie-update.html',
  styleUrl: './categorie-update.css',
})
export class CategoryUpdateComponent implements OnInit {

  categoryForm!: FormGroup;
  products: Product[] = [];
  categoryId!: number;
  successMessage = '';
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private categoryService: CategoryService,
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (!idParam || isNaN(Number(idParam))) {
      this.router.navigate(['/categories']);
      return;
    }

    this.categoryId = +idParam;
    this.initForm();
    this.loadProducts();
    this.loadCategory();
  }

  initForm(): void {
    this.categoryForm = this.fb.group({
      name: ['', Validators.required],
      productIds: [[]]
    });
  }

  loadProducts(): void {
    this.productService.getAll().subscribe(res => this.products = res);
  }

  loadCategory(): void {
    this.categoryService.getById(this.categoryId).subscribe(category => {
      this.categoryForm.patchValue({
        name: category.name,
        productIds: category.products?.map(p => p.id) || []
      });
    });
  }

  onSubmit(): void {
    if (this.categoryForm.invalid) return;

    const formValue = this.categoryForm.value;
    const categoryRequest: CategoryRequest = {
      name: formValue.name,
      productIds: formValue.productIds
    };

    this.categoryService.update(this.categoryId, categoryRequest).subscribe({
      next: () => {
        this.successMessage = 'Catégorie mise à jour avec succès !';
        this.errorMessage = '';
      },
      error: err => {
        console.error(err);
        this.errorMessage = 'Erreur lors de la mise à jour';
        this.successMessage = '';
      }
    });
  }

  cancel(): void {
    this.router.navigate(['/categories']);
  }
}
