import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  onActivate($event) {
    console.log('Activated:', $event);
  }

  onDeactivate($event) {
    console.log('Deactivated:', $event);
  }
}

