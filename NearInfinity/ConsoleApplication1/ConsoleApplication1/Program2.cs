using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Linq;

/// (X) Implicitly typed local variables
/// (X) Implicitly typed arrays
/// (X) Object initializers
/// (X) Anonymous types
/// (X) Lambda expressions
/// (X) Extension methods
/// (X) Query expressions
/// (X) Expression trees
namespace TedNeward.XMLUtils
{
    static class XMLUtils
    {
        public static string ToXML(this CS3Demos.Person o, bool includeNS)
        {
            return "<this is " + o + " xml />";
        }
        public static string ToString(this CS3Demos.Person o)
        {
            return "Whee!!";
        }
    }
}

namespace CS3Demos
{
    using ConsoleApplication1;
    using TedNeward.XMLUtils;

    class Person
    {
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public int Age { get; set; }

        public string ToXML(bool ignoreMe)
        {
            return "<foo />";
        }
    }

    class Program
    {
        static IArtist GetTheArtist()
        {
            return new CowboyArtist();
        }

        static void Main()
        {
            var people = new[]
                         {
                             new Person() { FirstName="Ted", LastName="Neward", Age=38},
                             new Person() { FirstName="Michael", LastName="Neward", Age=16},
                             new Person() { FirstName="Brian", LastName="Randell", Age=39}
                         };
            var drinkers = people
                .Where((p) => p.LastName == "Neward")
                .Where(p => p.Age > 20);
            foreach (Person pi in drinkers)
                Console.WriteLine("old guy is {0}", pi.Age);

            Console.WriteLine("=====================");

            var results = 
                from p3 in people
                where p3.LastName == "Neward"
                select new { p3.FirstName, p3.LastName, p3.Age }
            ;
            Console.WriteLine("=====================");


            var br = new Person() {FirstName = "Brian", LastName = "Randell"};
            Console.WriteLine(br.ToString());
            Console.WriteLine(br.ToXML(true));
            br.ToXML(true);

            var x = "Brian";
            Console.WriteLine(x);

            var xa = new []{1, 2, 3, 4};

            var a = GetTheArtist();
            a.Draw();

            var d = new Dictionary<string, string>()
              { { "Brian" , "VB"} };


            var pm = (Func<string>)
                delegate() 
                { 
                    Console.WriteLine("Hi");
                    return "";
                };
            //var p2 =
            //    new
            //        {
            //            FirstName = "Brian",
            //            LastName = "Randell",
            //            Age = 39,
            //            PrintMe = (Func<string>)
            //                delegate()
            //                {
            //                    Console.WriteLine("Hi");
            //                    return "";
            //                },
            //            PrintMe2 = (Func<string, string>)(msg => msg)
            //        };
            //p.PrintMe();

            //Func<string, int, string> f = ( (msg, age) => msg);
            //Func<string> f2 = (() => "Howdy");
            //var p2 =
            //    new { Age = 39, FirstName = "Brian", LastName = "Randell" };
            //Console.WriteLine(p);
            //Console.WriteLine(p.Equals(p2));
            //Type t1 = p.GetType();
            //Type t2 = p2.GetType();
            //Console.WriteLine(t1.Equals(t2));
            //Console.WriteLine(t1 == t2);
        }
    }
}


/// Demoing
/// (X) partial classes
/// (X) static classes
/// (X) anonymous methods
/// (X) nullable types
/// (X) iterators/generators
/// (*) generics
namespace CS2Demos
{
    using ConsoleApplication1;
    using CA = ConsoleApplication1.CowboyArtist;

    static class Utils
    {
        public static string GetClass<T>(T classname)
        {
            return typeof (T).ToString();
        }
        public static string GetClass<T>()
        {
            return typeof(T).ToString();
        }

        public static int Step = 5;
        public static void Nifty()
        {
            Console.WriteLine("Nifty!");
        }
        public static IEnumerable<int> GetCharacterDiceRolls()
        {
            Random r = new Random();
            for (int i = 0; i < 5; i++ )
                yield return r.Next(15) + 3;
        }
        public static IEnumerable<int> GetFibonacci(int max)
        {
            yield return 1;
            yield return 1;
            yield return 2;
            yield return 3;
            yield return 5;
        }
        public static IEnumerable<int> GetSequence()
        {
            int potential = 0;
            //for (int i = 0; i < 100; i++)
            //    yield return i * Step;
            while (true)
            {
                int result = potential++;
                if (result == 4)
                    break;
                if (result % 2 == 0)
                    yield return result;
                Console.WriteLine("Whee!!!!");
            }
        }
        public static IEnumerator<string> Readlines(string file)
        {
            // open a file
            // create a generator that yield returns a line
            // of text each time through
            return null;
        }

        public delegate string ProduceString(string pfx);
        public static void PrintMessage(ProduceString ps)
        {
            Console.WriteLine(ps("PM:: "));
        }

        public delegate string ProduceStringNP();
        public static void PrintMessage(ProduceStringNP ps)
        {
            Console.WriteLine(ps());
        }

        public static List<T> filter<T>(List<T> list, Predicate<T> pred)
        {
            List<T> results = new List<T>();

            foreach (T item in list)
                if (pred(item))
                    results.Add(item);

            return results;
        }
    }

    class Foo<T>
    {
        Foo(T value) {
            val = value; }

        public override string ToString()
        {
            return typeof (T).ToString() + " " + val;
        }

        private T val;
    }

    class Program
    {
        public delegate void TedsAction<T>(T obj);

        static void OldMain()
        {
            var jsObject =
                new Dictionary<string, object>();
            jsObject["print"] = (Action<string>)
                (msg => Console.WriteLine("print: " + msg));
            ((Action<string>)jsObject["print"])("Hello");

            List<string> people = new List<string>();
            people.AddRange(new [] {"Scott", "Ted", "Neal"});
            Console.WriteLine(people[1]);

            Console.WriteLine(Utils.GetClass(5));
            Console.WriteLine(Utils.GetClass<Int64>());

            List<string> coolPeople = 
                Utils.filter(people, 
                    delegate(string s)
                        {
                            return s != "Scott";
                        });
            foreach (string s in coolPeople)
                Console.WriteLine("{0} is cool! Yay!", s);

            string message = "Howdy";
            Utils.PrintMessage(delegate() {
                                    return message;
                               });

            Utils.PrintMessage(delegate()
            {
                return message;
            });
            Utils.Nifty();

            CA c = new CA();
            IEnumerator<int> i1 = Utils.GetSequence().GetEnumerator();
            System.Console.WriteLine(i1.Current);
            i1.MoveNext();
            System.Console.WriteLine(i1.Current);
            Utils.Step = 100;
            i1.MoveNext();
            System.Console.WriteLine(i1.Current);
            i1.MoveNext();
            System.Console.WriteLine(i1.Current);
        }
    }
}

















namespace ConsoleApplication1
{
    interface ICowboy
    {
        void Draw();
        void Shoot();
    }
    interface IArtist
    {
        void Draw();
        void Paint();
    }

    public class CowboyArtist : ICowboy, IArtist
    {
        public virtual void Shoot()
        {
            Console.WriteLine("Bang bang");
        }
        public virtual void Paint()
        {
            Console.WriteLine("Paints out");
        }

        void ICowboy.Draw()
        {
            Console.WriteLine("Gun's out");
        }
        void IArtist.Draw()
        {
            Console.WriteLine("Sketch sketch");
        }
        //public virtual void Draw()
        //{
        //    Console.WriteLine("Gun's out");
        //}
    }
    class RodeoArtist : CowboyArtist, IArtist
    {
        //public override void Draw()
        //{
        //    Console.WriteLine("Rodeo sketch");
        //}
        void IArtist.Draw()
        {
            ((IArtist)((CowboyArtist) this)).Draw();
            Console.WriteLine("Rodeo sketch");
        }
    }

    public class Program4
    {
        static void OldMain()
        {
            ICowboy c = new CowboyArtist();
            c.Draw();
            c.Shoot();

            IArtist a = new CowboyArtist();
            a.Draw();
            a.Paint();

            CowboyArtist ca = new CowboyArtist();
            //ca.Draw();

            IArtist a2 = new RodeoArtist();
            a2.Draw();
        }
    }



















    interface IBoo
    {
        void Boo();
        string Message { get; }
    }

    class B : IBoo
    {
        public virtual string Sealed
        {
            get { return "B::Sealed";  }
        }

        public string Message { get { return "boo!"; } }
        public void Boo()
        {
            Console.WriteLine("Boo says {0}", Message);
        }

        public virtual String Title
        { 
            get
            {
                return "B";
            }
        }

        public virtual void DoIt()
        {
            Console.WriteLine("B::DoIt");
        }
        public void DoItNV()
        {
            Console.WriteLine("B::DoItNV");
        }
    }
    class D : B, IBoo
    {
        public sealed override string Sealed
        {
            get { return "D::Sealed"; }
        }

        public string Message { get { return "D::boo!"; } }
        public void Boo()
        {
            Console.WriteLine("Boo says {0}", Message);
        }
        public override string Title
        {
            get
            {
                return "D";
            }
        }
        public virtual void DoIt()
        {
            Console.WriteLine("D::DoIt");
        }
        public new void DoItNV()
        {
            Console.WriteLine("D::DoItNV");
        }
    }
    class E : D
    {
        public string Sealed
        {
            get { return "E::Sealed"; }
        }
        public override void DoIt()
        {
            Console.WriteLine("E::DoIt");
        }
    }
    class Program3
    {
        static void OldMain()
        {
            B b = new D();
            b.DoIt();

            Console.WriteLine("=================");

            B b2 = new B();
            b2.DoItNV();
            D d2 = new D();
            d2.DoItNV();
            B b3 = new D();
            b3.DoItNV();
            b3 = new E();
            b3.DoItNV();  // B
            d2 = new E();
            d2.DoItNV();  // E

            Console.WriteLine("=================");

            B b4 = new B();
            b4.Boo();
            D d3 = new D();
            d3.Boo();       // D
            B b5 = new D();
            b5.Boo();       // B
        }
    }






    class Person : Object
    {
        public Person(string f, string l, int a)
        {
            this.firstName = f;
            this.lastName = l;
            this.age = a;
        }

        public string ToString()
        {
            return String.Format("{0} {1} ({2} years old", firstName, lastName, age);
        }

        private string firstName;
        private string lastName;
        private int age;
    }
    
    
    
    
    
    
    
    
    
    
    class Program2
    {
        private delegate string ConProc(string p);

        static void ACB(IAsyncResult iar)
        {
            System.Threading.Thread.CurrentThread.IsBackground = false;

            Console.WriteLine("ACB::Thread id = {0}",
                System.Threading.Thread.CurrentThread.ManagedThreadId);

            ConProc p = iar.AsyncState as ConProc;

            string result = p.EndInvoke(iar);
            Console.WriteLine("ACB::Thread id = {0}, result = {1}",
                              System.Threading.Thread.CurrentThread.ManagedThreadId,
                              result);
        }

        static void OldMain()
        {
            Console.WriteLine("Main::Thread id = {0}", 
                System.Threading.Thread.CurrentThread.ManagedThreadId);

            ConProc p = new ConProc(DoSomething);
            
            AsyncCallback ac = new AsyncCallback(ACB);
            IAsyncResult iar = p.BeginInvoke("Parameter", ac, p);
        }

        static string DoSomething(string parm)
        {
            Console.WriteLine("DoSomething::Thread id = {0}", 
                System.Threading.Thread.CurrentThread.ManagedThreadId);
            return parm + "Result";
        }
    }
}
