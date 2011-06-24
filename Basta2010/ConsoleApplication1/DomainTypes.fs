module Demo

open System

type Person(fn : string, ln : string, a : int) =
    member this.FirstName = fn
    member this.LastName = ln
    member this.Age = a
    override this.ToString() =
        String.Format("[Person-FirstName={0} LastName={1} Age={2}]",
            this.FirstName, this.LastName, this.Age)
