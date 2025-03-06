import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'home',
    loadComponent: () => import('./home/home.page').then((m) => m.HomePage),
  },
  {
    path: '',
    redirectTo: 'principal',
    pathMatch: 'full',
  },
  {
    path: 'principal',
    loadComponent: () => import('./principal/principal.page').then( m => m.PrincipalPage)
  },
];
