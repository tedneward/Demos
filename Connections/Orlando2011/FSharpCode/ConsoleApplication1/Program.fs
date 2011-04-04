// Learn more about F# at http://fsharp.net

open System

let message = "Go FSI!"
System.Console.WriteLine(message)

type Person(firstName : string, lastName : string, age : int) =
    member this.FirstName = firstName
    member this.LastName = lastName
    member this.Age = age
    override p.ToString() =
        String.Format("Person: {0}, {1}, {2}", p.FirstName, p.LastName, p.Age)

type Student(fn : string, ln : string, a : int, subject : string) =
    inherit Person(fn, ln, a)
    member s.Subject = subject
    member s.Drink(?amt : int, ?kind : string) =
        let bk = match kind with None -> "Keystone" | Some b -> b
        let ba = match amt with None -> 12 | Some am -> am
        System.Console.WriteLine("Drinking {0} {1}s", ba, bk)
    override s.ToString() =
        String.Format("Student: {0} ({1})", subject, base.ToString())

let t = new Student("Ted", "Neward", 40, "Binge Drinking")
System.Console.WriteLine(t)
t.Drink()
t.Drink(1, "Macallan 18")





