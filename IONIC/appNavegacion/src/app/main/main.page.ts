import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar, IonButton, IonButtons, IonMenuButton, IonGrid, IonRow, IonCol, IonText } from '@ionic/angular/standalone';
import { RouterLink, RouterLinkWithHref, RouterModule } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.page.html',
  styleUrls: ['./main.page.scss'],
  standalone: true,
  imports: [IonText, IonCol, IonRow, IonGrid, IonButtons, IonButton, IonMenuButton, IonContent, IonHeader, IonTitle, IonToolbar,
     CommonModule, FormsModule, RouterLink, RouterLinkWithHref]
})
export class MainPage implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
