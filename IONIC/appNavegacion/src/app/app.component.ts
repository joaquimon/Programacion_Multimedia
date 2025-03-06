import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkWithHref, RouterModule } from '@angular/router';
import { IonApp, IonRouterOutlet, IonHeader, IonMenu, IonToolbar, IonItem, IonTitle,
  IonContent, IonList, IonLabel, IonMenuToggle } from '@ionic/angular/standalone';



@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  imports: [IonLabel, IonList, IonContent, IonTitle, IonItem, IonToolbar, IonHeader, IonApp,
    IonRouterOutlet, IonMenu, RouterLink, IonMenuToggle, RouterLinkWithHref,
    RouterModule, CommonModule],
})
export class AppComponent {
  constructor() {}
}
