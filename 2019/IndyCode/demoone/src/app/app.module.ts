import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { JokeService } from './joke.service';
import { JokeComponent } from './joke/joke.component';

@NgModule({
  declarations: [
    AppComponent,
    JokeComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [
    JokeService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
