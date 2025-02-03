import { Routes } from '@angular/router';
import { home } from 'ionicons/icons';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'principal',
    pathMatch: 'full',
  },
  {
    path: 'principal',
    loadComponent: () => import('./principal/principal.component').then( m => m.PrincipalComponent),
    children: [

  {
    path: 'home',
    loadComponent: () => import('./principal/home/home.page').then( m => m.HomePage)
  },
  {
    path: 'radio',
    loadComponent: () => import('./principal/radio/radio.page').then( m => m.RadioPage)
  },
  {
    path: 'library',
    loadComponent: () => import('./principal/library/library.page').then( m => m.LibraryPage)
  },
  {
    path: 'search',
    loadComponent: () => import('./principal/search/search.page').then( m => m.SearchPage)
  },
    ]
  }
];
