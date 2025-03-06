import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar, IonButtons, IonMenuButton, IonList, IonRow, IonCol, IonListHeader, IonLabel, IonItem } from '@ionic/angular/standalone';
import { Producto } from '../interfaces/producto';
import { ProductoService } from '../servicios/producto.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.page.html',
  styleUrls: ['./product-list.page.scss'],
  standalone: true,
  imports: [IonItem, IonLabel, IonListHeader, IonRow, IonCol ,IonList, IonButtons, IonContent, IonHeader, IonTitle, IonToolbar, CommonModule, FormsModule, IonMenuButton]
})
export class ProductListPage implements OnInit {

  Productos: Producto[] = [];

  constructor(private productoService:ProductoService) { }

  ngOnInit() {
    this.getProductos()
  }

  getProductos(){
    this.productoService.mostrarProductos().subscribe(result =>{
      this.Productos = result;
    })
  }

}
