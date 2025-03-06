import { Component, OnInit } from '@angular/core';
import { IonContent, IonHeader, IonIcon, IonTab, IonTabBar, IonTabButton, IonTabs, IonTitle, IonToolbar,} from '@ionic/angular/standalone';
import { addIcons } from 'ionicons';
import { library, playCircle, radio, search } from 'ionicons/icons';

@Component({
  selector: 'app-principal',
  imports: [IonContent, IonHeader, IonIcon, IonTab, IonTabBar, IonTabButton, IonTabs, IonTitle, IonToolbar],
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.scss'],
})
export class PrincipalComponent  implements OnInit {

  constructor() {
    addIcons({library, playCircle, radio, search})
   }

  ngOnInit() {}

}
