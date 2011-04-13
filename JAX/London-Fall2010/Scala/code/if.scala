if (args.length > 0)
    System.out.println("We got arguments!")
else
    System.out.println("Nope, no arguments!")


val message =
    if (args.length > 0) "We got arguments!"
    else "Nope, no arguments!"
System.out.println(message)

val result =
    if (args.length > 0) true
    else 5
