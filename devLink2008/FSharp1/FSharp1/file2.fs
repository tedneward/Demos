
// F# Visual Studio Sample File
//
// This file contains some sample constructs to guide you through the
// primitives of F#.
//
// Contents:
// 

// Turn on the lightweight syntax
#light

// Higher-order functions
// ---------------------------------------------------------------
let sqr = fun x -> x * x

let ipow3 x =
    let sqr x = x * x
    x * sqr x
    
let ipow4 x =
    let sqr x = x * x
    sqr(sqr x)
   
let rec ipown n m =
    if m = 0 then 1 else n * ipown n (m-1)


// Multi-value pattern matching
// ---------------------------------------------------------------
let unbox3 a b c =
    match a, b, c with
    | Some a, Some b, Some c -> Some (a, b, c)
    | _ -> None


