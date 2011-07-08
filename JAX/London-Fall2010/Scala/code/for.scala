import System.out._

for (a <- args)
    println(a)

println("========")

for (i <- 1 to 3;
     j <- 1 to 3)
    println("i=" + i + "; j=" + j + "; i*j=" + i*j);

println("========")

for (i <- 1 to 6;                // generator
     if ((i % 2) == 0);          // filter
     j <- 1 to 6;                // generator
     if ((j % 2) == 0);          // filter
     if (((i*j) % 2) == 0))      // filter
{
    print("i*j (" + i + "," + j + ")")
    println("=" + (i*j))
}
     