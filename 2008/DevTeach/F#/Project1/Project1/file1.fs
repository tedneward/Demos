
// F# Visual Studio Sample File
//
// This file contains some sample constructs to guide you through the
// primitives of F#.
//
// Contents:
//   - Simple computations
//   - Functions on integers.  
//   - Tuples 
//   - Strings
//   - Lists
//   - Arrays
//   - Functions

// Turn on the lightweight syntax
#light

// open some standard namespaces
open System

// Simple computations
// ---------------------------------------------------------------
// Here are some simple computations.  Note how code can be documented
// with '///' comments.  You can use the extra --html-* command line
// options to generate HTML documentation directly from these comments.

/// A very simple constant integer
let integer1 = 1

/// A second very simple constant integer
let integer2 = 2

/// Add two integers
let integer3 = integer1 + integer2

/// Call a function with a side effect
printfn "res = %d" integer3

// Functions on integers.  
// ---------------------------------------------------------------

/// A function on integers
let twist x = 10 - x

/// The result of a simple computation 
let result = twist (integer3+4)

/// Another function on integers
let increment x = 
	let increment2 x =
		x +2
	(increment2 x) - 1
	
/// Compute the factorial if an integer
let rec factorial n = 
	if n=0 then 1 else n * factorial (n-1)

/// Compute the highest-common-factor of two integers
let rec hcf a b =           // notice: 2 arguments seperated by spaces
    if a=0 then b
    elif a<b then hcf a (b-a)           // notice: 2 arguments seperated by spaces
    else hcf (a-b) b
    // note: function arguments are usually space seperated.
    // note: let rec binds recursive functions.

      
// Tuples.  These combine values into packets.
// ---------------------------------------------------------------

// A simple tuple of integers
let pointA = (1,2,3)

// A simple tuple of an integer, a string and a double-precision floating point number
let dataB = (1,"fred",3.1415)

/// Swap the order of two integers
let swap (a,b) = (b,a)
swap (1, 2)
swap (1, "yves")

// Booleans
// ---------------------------------------------------------------

/// A simple boolean value
let boolean1 = true

/// A second simple boolean value
let boolean2 = false

/// Compute a new boolean using ands-and-ors
let boolean3 = not boolean1 && (boolean2 || false)

// Strings
// ---------------------------------------------------------------

/// A simple string
let stringA  = "Hello"

/// A second simple string
let stringB  = "world"

/// 'Hello world' computed using string concatentation
let stringC  = stringA + " " + stringB

/// 'Hello world' computed using a .NET library function
let stringD = String.Join(" ",[| stringA;stringB |])
  // Trying re-typing the above line to see intellisense in action.
  // Note, ctrl-J on (partial) identifiers re-activates it.

// Output the strings
printfn "stringC = %s, stringD = %s" stringC stringD

/// A string computed using the 'sprintfn' string layout function
let stringE = sprintf "stringC = %s, stringD.Length = %d" stringC stringD.Length


// Functional Lists.
// ---------------------------------------------------------------

/// The empty list
let listA = [ ]           

/// A list with 3 ints
let listB = [ 1;2;3 ]     

/// A list with 3 integers, note :: is the 'cons' operation.
let listC = 1 :: [2;3]    

// Print a result using generic printing
printfn "listC = %A" listC

/// Compute the sum of a list of integers using a recursive function
let rec sumList xs =
    match xs with
    | []    -> 0
    | y::ys -> y + sumList ys

/// Sum of a list
let listD   = sumList [1;2;3]  

/// Generate a list by appending one list to another
let listE = listA @ [1;2;3]    

// Mutable Arrays, a primitive for efficient computations
// ---------------------------------------------------------------

/// Create an array 
let arr = Array.create 4 "hello"
arr.[1] <- "world"
arr.[3] <- "don"

/// Compute the length of the array by using an instance method on the array object
let arrLength = arr.Length        

// Extract a sub-array using slicing notation
let front = arr.[0..2]

// For some other common data structures, see namespaces 
//   System.Collections.Generic
//   Microsoft.FSharp.Collections
//   Microsoft.FSharp.Collections.Set
//   Microsoft.FSharp.Collections.Map


// Functions
// ---------------------------------------------------------------

/// A function that increments its input by 2, as a function definition
let Increment2 x = x + 2              

/// A function that increments its input by 3, as a lambda expression
let Increment3   = fun x -> x + 3     

let integersA = List.map Increment2 [1;2;3]
let integersB = List.map Increment3 [1;2;3]
let integersC = List.map (fun x -> x+4) [1;2;3]

// Pipelines:
let pipe1 = [1;2;3] |> List.map (fun x -> x+4) 
let pipe2 = 
  [1;2;3] 
  |> List.map (fun x -> x+4) 
  |> List.filter (fun x -> x>5) 

// Composition pipelines:
let processor = List.map (fun x -> x+4) >> List.filter (fun x -> x>5) 

// Types - datatypes
// ---------------------------------------------------------------

type Expr = 
  | Num of int
  | Add of Expr * Expr
  | Sub of Expr * Expr
  | Mul of Expr * Expr
  | Div of Expr * Expr
  | Var of string
  
let rec Evaluate (env:Map<string,int>) exp = 
    match exp with
    | Num n -> n
    | Add (x,y) -> Evaluate env x + Evaluate env y
    | Sub (x,y) -> Evaluate env x - Evaluate env y
    | Mul (x,y) -> Evaluate env x * Evaluate env y
    | Div (x,y) -> Evaluate env x / Evaluate env y
    | Var id    -> env.[id]
  
let envA = Map.of_list [ "a",1 ;
                         "b",2 ;
                         "c",3 ]
             
let expT1 = Add(Var "a",Mul(Num 2,Var "b"))
let resT1 = Evaluate envA expT1


// Types - records
// ---------------------------------------------------------------

type Card = { Name  : string;
              Phone : string;
              Ok    : bool }

let cardA = { Name = "Alf" ; Phone = "+44.1223.000.000" ; Ok = false }
let cardB = { cardA with Phone = "+44.1223.123.456"; Ok = true }
let ShowCard c = 
  c.Name + " Phone: " + c.Phone + (if not c.Ok then " (unchecked)" else "")


// Here's a longer construction syntax should you get name conflicts:
let cardC : Card = {  Name  = "Alf" ; Phone = "+44.1223.000.000"; Ok = false }


// Types - classes
// ---------------------------------------------------------------

type Widget() = 
    let mutable state = 0
    /// Poke the Widget
    member x.Poke(n) = state <- state + n
    /// Fetch the value of the Widget
    member x.Peek() = state 
    /// Has the Widget been poked?
    member x.HasBeenPoked = (state <> 0)

let widget1 = Widget()

widget1.Poke(4)
widget1.Peek
widget1.HasBeenPoked


// Types - interfaces, which are like records of functions
// ---------------------------------------------------------------

type IPeekPoke = 
    abstract Peek: unit -> int
    abstract Poke: int -> unit


              
// Types - classes with interface implementations
// ---------------------------------------------------------------

type Widget2(initialState:int) = 
    /// The internal state of the Widget
    let mutable state = initialState

    // Implement the IPeekPoke interface
    interface IPeekPoke with 
        member x.Poke(n) = state <- state + n
        member x.Peek() = state 
        
    /// Has the Widget been poked?
    member x.HasBeenPoked = (state <> 0)

let widget2 = (Widget2(12) :> IPeekPoke)

widget2.Poke(4)
widget2.Peek

              
