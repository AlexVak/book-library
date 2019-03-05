import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  private loggedIn = false;

  constructor() { }

  ngOnInit() {
  }

  toggleDisplay() {
    this.loggedIn = !this.loggedIn;
  }
}
