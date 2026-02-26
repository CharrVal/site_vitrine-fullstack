import { Component, signal } from '@angular/core';
import { NavigationEnd, Router, RouterOutlet } from '@angular/router';
import { Navbar } from "./composants/navbar/navbar";
import { Footer } from './composants/footer/footer';
import { filter } from 'rxjs';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Navbar, Footer],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('vitrine-front');
  showFooter = false;

  constructor(private router: Router) {
    // On écoute les changements de route
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: any) => {
        const url = event.urlAfterRedirects;

        this.showFooter = url === '/accueil'
                       || url === '/productList'
                       || url === '/actualites'
                       || url.startsWith('/productList/');
      });
  }
}
