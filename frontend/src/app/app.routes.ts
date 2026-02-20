import { Routes } from '@angular/router';

import { ProductCreateComponent } from './composants/pages/products/product-create/product-create';
import { ProductListComponent } from './composants/pages/products/product-list/product-list';
import { ProductUpdateComponent } from './composants/pages/products/product-update/product-update';
import { Accueil } from './composants/pages/accueil/accueil';
import { CategorieCreateComponent } from './composants/pages/categories/categorie-create/categorie-create';
import { CategorieListComponent } from './composants/pages/categories/categorie-list/category-list';
import { Connexion } from './composants/pages/connexion-2/connexion/connexion';
import { authGuard } from './composants/guards/auth-guard';
import { PanneauControl } from './composants/pages/panneau-control/panneau-control';

export const routes: Routes = [
  { path: 'accueil', component: Accueil },
  { path: 'productList', component: ProductListComponent },
  { path: 'controlPanel', component: PanneauControl, canActivate: [authGuard] },
  { path: 'productCreate', component: ProductCreateComponent, canActivate: [authGuard]},
  { path: 'productList/productUpdate/:id', component: ProductUpdateComponent, canActivate: [authGuard] },
  { path: 'productList/:id', loadComponent: () => import('./composants/pages/details/product-detail').then(m => m.ProductDetailComponent) },
  { path: 'categoriesCreate',  component: CategorieCreateComponent, canActivate: [authGuard] },
  { path: 'categoriesList', component: CategorieListComponent, canActivate: [authGuard] },
  { path: 'connexion', component: Connexion},
  { path: '**', redirectTo: 'accueil' }
];
