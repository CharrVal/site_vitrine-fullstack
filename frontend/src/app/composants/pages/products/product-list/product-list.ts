import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product';
import { Router, RouterModule } from '@angular/router';
import { Product } from '../interface/product'
import { CommonModule } from '@angular/common';
import { Category } from '../../categories/interface/category';
import { CategoryService } from '../../categories/services/category';
import { UserLoggedService } from '../../connexion-2/services/user-logged-service';
import { FlashMessageService } from '../services/flash-message-service';

@Component({
  selector: 'app-product-list',
  imports: [RouterModule, CommonModule],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css',
})
export class ProductListComponent implements OnInit{

  products: Product[] = [];
  categories: Category[] = [];
  selectedCategoryId?: number;
  successMessage: string = '';
  errorMessage: string = '';
  
  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private cdr: ChangeDetectorRef,
    public userLoggedService: UserLoggedService,
    private flashMessageService: FlashMessageService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadProducts();
    this.loadCategories();
    this.flashMessageService.success$.subscribe(msg => this.successMessage = msg);
    this.flashMessageService.error$.subscribe(msg => this.errorMessage = msg);
  }
  
  loadProducts(): void {
    this.productService.getAll().subscribe({
      next: res => {
        this.products = res;
        this.cdr.markForCheck();
      },
      error: () => {
        this.errorMessage = 'Erreur lors du chargement des produits';
        this.cdr.markForCheck();
      }
    });
  }


  getImageUrl(img: string): string {
    return this.productService.getImageUrl(img);
  }

  deleteProduct(id: number): void {
    if (!confirm('⚠️ Voulez-vous vraiment supprimer ce produit ?')) return;

    this.productService.delete(id).subscribe({
      next: () => {
        this.flashMessageService.showSuccess('Produit supprimé avec succès !', 3000);
        this.router.navigate(['/productList']);
      },
      error: (err) => {
        console.error(err);
        this.flashMessageService.showError('Erreur lors de la suppression du produit', 5000);
        this.router.navigate(['/productList']);
      }
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

  filterByCategory(id?: number): void {
    this.selectedCategoryId = id;

    if (!id) {
      this.loadProducts();
    } else {
      this.productService.getByCategory(id)
        .subscribe(res => {
          this.products = res;
          this.cdr.markForCheck(); // <-- déclenche l'affichage immédiatement
        });
    }
  }
}
