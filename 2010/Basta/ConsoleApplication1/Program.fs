// Learn more about F# at http://fsharp.net
module Program

open Demo

let swap (a, b) = (b, a)


[<EntryPoint>]
let main(args : string array) =
    let add left right = left + right
    let pow bas exp = System.Math.Pow(bas, exp)
    let square x = pow x 2.0
    
    let add5 right = add 5 right

    System.Console.WriteLine("Howdy!")
    
    let p1 = new Person("Udi", "Dahan", 16)
    System.Console.WriteLine(p1)
    
    let p2 = new Person("Jane", "Dahan", 16)
    System.Console.WriteLine(p2)
    
    BusinessRules.marry(p1, p2)

    let peopleList = [
        p1; p2;
        new Person("Ted", "Neward", 39);
        new Person("Charlotte", "Neward", 38);
        new Person("Michael", "Neward", 16);
        new Person("Matthew", "Neward", 10)]
    
    List.iter 
        (fun (p : Person) -> System.Console.WriteLine("{0} is {1} years old", p.FirstName, p.Age))
        peopleList

    let peopleAges =
        List.map
            (fun (p : Person) -> p.Age)
            peopleList
    List.iter
        (fun (p : int) -> System.Console.WriteLine(p))
        peopleAges
    
    let sumAges =
        List.fold
            (fun (accum : int) (age : int) ->
                accum + age)
            0
            peopleAges
    System.Console.WriteLine("The total ages is {0}", sumAges)

    let csvNames =
        List.fold
            (fun (accum : string) (p : Person) ->
                accum + ", " + p.FirstName)
            "The people in the list are "
            peopleList
    System.Console.WriteLine(csvNames)
    0
