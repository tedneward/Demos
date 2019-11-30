let isDone: boolean | null | undefined = null

class Person {
  constructor(private firstName: String) {

  }
}

let p = new Person("Ted")

type Music = "metal" | "punk"

let music : Music | number = "punk"

console.log("Howdy");
