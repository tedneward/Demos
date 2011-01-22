// Learn more about F# at http://fsharp.net

open System

let message = "Howdy, London!"
System.Console.WriteLine(message)

for i = 1 to 10 do
    System.Console.WriteLine("i = {0}", i)
    if i % 2 = 0 then
        System.Console.WriteLine("i^2 = {0}", i * i)

type Person(firstName, lastName, age) =
    member p.FirstName = firstName
    member this.LastName = lastName
    member Me.Age = age
    override p.ToString() = 
        String.Format("[Person: {0} {1} {2}", p.FirstName, lastName, p.Age)

type Student(firstName, lastName, age, subject) =
    inherit Person(firstName, lastName, age)
    member s.Subject = subject
    member s.DrinkBeer(?kind : string, ?amount : int) =
        let bk = match kind with None -> "Keystone" | Some name -> name
        let amnt = match amount with None -> 6 | Some amt -> amt
        Console.WriteLine("I'm drinking {0} {1}s", amnt, bk)

let t = new Student("Ted", "Neward", 39, "Girls")
Console.WriteLine("t's firstName is {0}", t.FirstName)
Console.WriteLine("t = {0}", t)
let t2 = new Person(t.FirstName, t.LastName, t.Age + 1)
Console.WriteLine("t's age is now {0}", t2.Age)
t.DrinkBeer(amount=12, kind="Samuel Adams")
