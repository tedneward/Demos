import { Component, EventEmitter, OnInit, Input, Output } from '@angular/core';

import { JokeService } from '../joke.service';

@Component({
  selector: 'app-joke',
  templateUrl: './joke.component.html',
  styleUrls: ['./joke.component.css']
})
export class JokeComponent implements OnInit {
  @Input()
  text : string = "";

  @Output("clicked")
  onClicked : EventEmitter<string> = new EventEmitter<string>();

  constructor(private jokeService: JokeService) { }

  ngOnInit() {
    this.text = this.jokeService.getName();
  }

}
