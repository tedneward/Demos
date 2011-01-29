import System.out._

val name = "Bill"

for (a <- args)
    println(a)

println("========")

for (i <- 1 to 3;
     j <- 1 to 3)
    println("i=" + i + "; j=" + j + "; i*j=" + i*j)

println("========")

def even (test : Int) = (test % 2) == 0

for (i <- 1 to 6;                // generator
     if even(i);          // filter
     j <- 1 to 6;                // generator
     if even(j);          // filter
     if (((i*j) % 2) == 0))      // filter
{
    print("i*j (" + i + "," + j + ")")
    println("=" + (i*j))
}
