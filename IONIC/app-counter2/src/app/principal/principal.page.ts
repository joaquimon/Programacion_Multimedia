import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar, IonFooter, IonButton, IonGrid, IonRow, IonCol, IonText } from '@ionic/angular/standalone';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.page.html',
  styleUrls: ['./principal.page.scss'],
  standalone: true,
  imports: [IonText, IonCol, IonRow, IonGrid, IonButton, IonFooter, IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule]
})
export class PrincipalPage implements OnInit {

  public num: number;
  public MAX: number;
  public MIN: number;

  constructor() {
    this.num = 0;
    this.MAX = 20;
    this.MIN = 0;
   }

  ngOnInit() {
  }

  up(){
    if(this.num < this.MAX){
      this.num++;
    }
  }

  down(){
    if(this.num > this.MIN){
      this.num--;
    }
  }

}
