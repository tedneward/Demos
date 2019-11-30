import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  // templateUrl: './app.component.html',
  template: `<!--The content below is only a placeholder and can be replaced.-->
  <div style="text-align:center">
    <h1>
      Welcome to {{ title }}!
    </h1>
    <h2>Here is the subtitle {{ subtitle }}</h2>
  </div>`,
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'My Fabulous Angular App';
  subtitle = 'From a demo at a Meetup';

  ngOnInit() {
    console.log("ngInit invoked");
  }
}
