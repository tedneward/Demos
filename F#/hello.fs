open System

type Money(amt : int, curr : string) =
  member this.Amount = amt
  member this.Currency = curr

  static member (+) (lhs : Money, rhs: Money) =
      Money(lhs.Amount + rhs.Amount, lhs.Currency)


type Person(fn : string, ln: string, a: int) =
  member this.FirstName = fn
  member this.LastName =ln
  member this.Age = a

  override  this.ToString() =
      String.Format("[Person {0} {1} {2}",
          this.FirstName, this.LastName, this.Age)

printfn("Hello world")

let msg = "Hello world"

let x = 20
let y = 15
let distance = 
  x*x + y*y

Console.WriteLine(distance)

let kristy = Person("Kristy", "Overton", 29)
Console.WriteLine(kristy)

let amt1 = Money(25, "USD")
let amt2 = Money(50, "USD")
let amt3 = amt1 + amt2
Console.WriteLine("amt3 = {0} {1}", amt3.Amount, amt3.Currency)

