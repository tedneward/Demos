/*
This is your exercises workbook. Simply fill in the various
function definitions, and run the whole script with
"scala exercises.scala". It will exercise the various
functions you write, passing in some test data, and
determine if you have returned the appropriate results.
 */

/* 
EXERCISE ONE:
-------------
Write a Fibonacci function. Remember, the definition of
Fibonacci sequences is n(x) = n(x-1) + n(x-2), where
n(0) = 1 and
n(1) = 1
*/

def fibonacci(in : Int) : Int =
	0


/*
EXERCISE TWO:
-------------
Write a Fibonacci function that returns a List[Int] containing
the values of the Fibonacci sequence for each element in the
List. In other words,
fibonacci(0) => List(1)
fibonacci(1) => List(1, 1)
 */

def fibonacciList(in : Int) : List[Int] =
	List()


/*
EXERCISE THREE:
---------------
Write a function that extracts the class name (String, run date
(Date), and the number of students (Int) from the tuple passed
in. In other words,
printCourse(("Scala", new Date(), 20))
should print
"The course 'Scala' ran on '(today's date)' with 20 students"
 */

import java.util.Date

def printCourse(in : Tuple3[String, Date, Int]) =
	""


// =========== You need not look beyond this line ====================
// =========== But you are welcome to do so if you ===================
// =========== want to see how my little exercise ====================
// =========== script exercises your work. ===========================

import System.out._

List(
	"Exercise one: fibonacci(0) == 1" -> 
		( () => fibonacci(0) == 1 ),
	"Exercise one: fibonacci(1) == 1" -> 
		( () => fibonacci(1) == 1 ),
	"Exercise one: fibonacci(2) == 2" -> 
		( () => fibonacci(2) == 2 ),
	"Exercise one: fibonacci(5) == 8" ->
		( () => fibonacci(5) == 8 ),
	"Exercise two: fibonacciList(0) == List(1)" -> 
		( () => fibonacciList(0) == List(1) ),
	"Exercise two: fibonacciList(1) == List(1, 1)" -> 
		( () => fibonacciList(1) == List(1, 1) ),
	"Exercise two: fibonacciList(2) == List(1, 1, 2)" -> 
		( () => fibonacciList(2) == List(1, 1, 2) ),
	"Exercise two: fibonacciList(5) == List(1, 1, 2, 3, 5, 8)" -> 
		( () => fibonacciList(5) == List(1, 1, 2, 3, 5, 8) ),
	"Exercise two: fibonacciList(5) == List(1, 1, 2, 3, 5, 8)" -> 
		( () => fibonacciList(5) == List(1, 1, 2, 3, 5, 8) ),
	"Exercise three: printCourse(\"Scala\", new Date(110, 0, 1), 20) " -> 
		( () => (printCourse(("Scala", new Date(110, 0, 1), 20)).equals(
			"The course 'Scala' ran on 'Fri Jan 01 00:00:00 PST 2010'" +
			" with 20 students")) )
	).foreach( (k) => println("Resuts of " + k._1 + " : " + k._2() ) )
