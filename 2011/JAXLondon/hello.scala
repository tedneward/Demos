val msg = if (args.length > 0) "arguments" else "nothing"
System.out.println("We got " + msg)

for (a <- args)
   println(a)
