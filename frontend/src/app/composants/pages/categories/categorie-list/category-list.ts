import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../services/category';
import { Category } from '../interface/category';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-categorie-list',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './category-list.html',
  styleUrl: './category-list.css',
})
export class CategorieListComponent implements OnInit {

  categories: Category[] = [];
  errorMessage = '';

  constructor(
    private categoryService: CategoryService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.loadCategories();
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

  deleteCategory(id: number): void {
    if (!confirm('Supprimer cette catégorie ?')) return;

    this.categoryService.delete(id).subscribe({
      next: () => this.loadCategories(),
      error: () => alert('Impossible de supprimer une catégorie contenant des produits')
    });
  }
}