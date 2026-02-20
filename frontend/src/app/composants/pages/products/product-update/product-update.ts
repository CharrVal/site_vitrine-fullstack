import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product';
import { CategoryService } from '../../categories/services/category';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Product } from '../interface/product';
import { Category } from '../../categories/interface/category';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-update',
  imports: [RouterModule, ReactiveFormsModule, CommonModule],
  templateUrl: './product-update.html',
  styleUrl: './product-update.css',
})
export class ProductUpdateComponent implements OnInit {

  productForm!: FormGroup;
  productId!: number;
  product!: Product;
  categories: Category[] = [];
  selectedFiles: File[] = [];
  previewImages: string[] = [];
  deletedImageIds: number[] = [];
  successMessage = '';
  errorMessage = '';

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');

    if (!idParam || isNaN(Number(idParam))) {
      this.router.navigate(['/productList']);
      return;
    }

    this.productId = +idParam;

    this.initForm();
    this.loadCategories();
    this.loadProduct();
  }

  initForm(): void {
    this.productForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: [0, [Validators.required, Validators.min(0)]],
      categoryId: [null, Validators.required]
    });
  }

  loadCategories(): void {
    this.categoryService.getAll().subscribe({
      next: res => {
        this.categories = res;
        this.cdr.markForCheck();
      },
      error: () => {
        this.errorMessage = 'Erreur lors du chargement des catégories';
        this.cdr.markForCheck();
      }
    });
  }

  loadProduct(): void {
    this.productService.getById(this.productId).subscribe({
      next: product => {
        this.product = product;

        this.productForm.patchValue({
          name: product.name,
          description: product.description,
          price: product.price,
          categoryId: product.categoryId
        });

        this.cdr.markForCheck();
      },
      error: err => {
        console.error('Erreur chargement produit', err);
        this.router.navigate(['/productList']);
      }
    });
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;

    if (!input.files) return;

    this.selectedFiles = Array.from(input.files);
    this.previewImages = [];

    for (const file of this.selectedFiles) {
      const reader = new FileReader();

      reader.onload = (e: any) => {
        this.previewImages.push(e.target.result);
        this.cdr.markForCheck();
      };

      reader.readAsDataURL(file);
    }
  }

  onSubmit(): void {
    if (this.productForm.invalid) return;

    const formData = new FormData();

    const dto = {
      name: this.productForm.value.name,
      description: this.productForm.value.description,
      price: this.productForm.value.price,
      categoryId: this.productForm.value.categoryId
    };

    formData.append(
      'dto',
      new Blob([JSON.stringify(dto)], { type: 'application/json' })
    );

    for (const file of this.selectedFiles) {
      formData.append('images', file);
    }

    this.deletedImageIds.forEach(id =>
      formData.append('deletedImageIds', id.toString())
    );

    this.productService.update(this.productId, formData).subscribe({
      next: () => this.router.navigate(['/productList']),
      error: () => this.errorMessage = 'Erreur lors de la mise à jour'
    });
  }

  getImageUrl(img: string): string {
    return this.productService.getImageUrl(img);
  }

  cancel(): void {
    this.router.navigate(['/productList']);
  }

  toggleImageToDelete(imageId: number): void {
    if (this.deletedImageIds.includes(imageId)) {
      this.deletedImageIds = this.deletedImageIds.filter(id => id !== imageId);
    } else {
      this.deletedImageIds.push(imageId);
    }
  }
}