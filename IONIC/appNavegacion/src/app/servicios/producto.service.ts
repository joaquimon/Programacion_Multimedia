import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Producto } from '../interfaces/producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  //url = "https://peticiones.online/api/products"

  url = "localhost:"

  resultadoUrl:any

  constructor(private httpClient:HttpClient) { }

  mostrarProductos(){
    return this.httpClient.get(this.url);
  }
}
