// Learn more about F# at http://fsharp.net

open System

type Person(firstName, lastName, age) =
    member p.FirstName = firstName
    member p.LastName = lastName
    member p.Age = age
    
    override p.ToString() =
        System.String.Format("[Person: {0} {1} {2}",
            p.FirstName, p.LastName, p.Age)
    
let rusty = new Person("Rusty", "Speidel", 18)
System.Console.WriteLine(rusty)

let attendees = [
    new Person("Rusty", "Speidel", 18)
    new Person("Ted", "Neward", 39)
    new Person("Ariya", "Burana", 21)
    new Person("Michael", "Neward", 16)
    ]

let attendeeAges = 
    List.fold 
        (fun accum (p : Person) -> accum + p.Age) 
        0 attendees

let attendeeXML =
    List.fold
        (fun (xml : string) (p: Person) -> xml + "<person>" + p.FirstName + "</person>")
        "<people>" attendees
    + "</people>"

let hello (p : Person) : string =
    match (p.FirstName, p.LastName, p.Age) with
    | ("Rusty", _, age) -> "Hiya, Rusty! You're " + age.ToString() + " years old?"
    | (_, _, age) when (age > 21 && age < 45) -> "Hey, have a beer!"
    | (firstName, _, _) -> "Hello " + firstName + " how are you?"
    | _ -> "I dunno"
    
System.Console.WriteLine(hello(rusty))

let message : string = "Howdy!"
System.Console.WriteLine(message)

let x = 5
System.Console.WriteLine(x)

for i = 1 to 10 do 
    System.Console.WriteLine(i)
    System.Console.WriteLine(i*i)
    System.Console.WriteLine(i*i*i)
    
System.Console.WriteLine("Back outside the loop")


