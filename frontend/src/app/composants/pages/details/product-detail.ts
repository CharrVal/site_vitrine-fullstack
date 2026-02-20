import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { ProductService } from '../products/services/product';
import { Product } from '../products/interface/product';
import { CommonModule } from '@angular/common';
import { UserLoggedService } from '../connexion-2/services/user-logged-service';

@Component({
  selector: 'app-product-detail',
  imports: [CommonModule, RouterModule],
  templateUrl: './product-detail.html',
  styleUrls: ['./product-detail.css'],
})
export class ProductDetailComponent implements OnInit {

  product: Product | null = null;
  isLoading: boolean = true;
  errorMessage = '';

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private cdr: ChangeDetectorRef,
    public userLoggedService: UserLoggedService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (isNaN(id)) {
      this.errorMessage = 'Produit introuvable';
      return;
    }

    this.productService.getById(id).subscribe({
      next: (res) => {
        this.product = res;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Erreur :', err);
        this.errorMessage = 'Produit introuvable';
      }
    });
  }
  
  getImageUrl(img: string): string {
    return this.productService.getImageUrl(img);
  }
}
