// Learn more about F# at http://fsharp.net
open System

let ints = [5; 4; 3; 2; 1; ]
let countList = List.length

let add l r = l + r
let add5 r = (fun l r -> l + r) 5

let message = "Howdy Dallas"
System.Console.WriteLine(message.Substring(6))

let x : int16 = 5s

for i = 1 to 10 do
    Console.WriteLine(i)

let swap (a, b) = (b, a)

type Person(firstName, lastName, age) =
    member per.FirstName = firstName
    member this.LastName = lastName
    override p.ToString() =
        System.String.Format("{0} {1} ({2} years old)", firstName, lastName, age)

let s = new Person("Scott", "Davis", 102)
Console.WriteLine(s.FirstName)

let pl = [ new Person("Scott", "Davis", 65); new Person("Ted", "Neward", 40);
            new Person("Tim", "Rayburn", 65); new Person("Craig", "Walls", 35) ]
let sumAges = List.map (fun (p : Person) -> p.Age) |> List.collect (+)
Console.WriteLine(sumAges)
