//http://newardassociates.com/slides/Workshops/Angular.ppt

type Music = "metal" | "punk" | "jazz" | "symphonic";
let m : Music = "metal"; // OK
//m = "rap"; // Error!



class Point {
  x: number;
  y: number;
  constructor(x : number, y : number) {
    this.x = x;
    this.y = y;
  }
  add(other : Point) : Point {
    this.x += other.x;
    this.y += other.y;
    return this;
  }
  get distance () {
    return Math.sqrt(
      (this.x * this.x) + (this.y * this.y)); 
  }
}
let pt = new Point(5,12);
console.log(pt.distance); // 13
