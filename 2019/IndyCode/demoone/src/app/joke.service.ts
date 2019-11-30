import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JokeService {

  constructor() { }

  getName() : string {
    return "Fred";
  }
}
