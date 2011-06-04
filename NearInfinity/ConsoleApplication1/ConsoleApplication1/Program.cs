using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace NearInfinity
{
    namespace DomainModel
    {
        public class Utilities
        {
            public static int SayHi(string msg, ref int outCt)
            {
                Console.WriteLine("SayHi:: " + msg);
                outCt++;
                return 5;
            }
        }
        public class Outputter
        {
            public int OutputIt(string msg, ref int outCt)
            {
                Console.WriteLine("OutputIt:: " + msg);
                outCt++;
                return 20;
            }
        }

        public delegate int OutputProc(string msg, ref int outCt);

        public class Program
        {
            public static void PersonEvtHandler(object sender, EventArgs pea)
            {
                Person p = (Person) sender;
                if (p.FirstName == null)
                    ((Person.PersonEventArgs) pea).Veto = true;
            }
            public static void OldMain(string[] args)
            {
                OutputProc op = new OutputProc(Utilities.SayHi);
                
                Outputter o = new Outputter();
                OutputProc op2 = new OutputProc(o.OutputIt);

                op += op2;
                SayHelloToWhomever(op);

                Person p = new Person("Michael", "Blumberg", 18);
                p.FirstNameModifed += new EventHandler(Program.PersonEvtHandler);
            }

            public static void SayHelloToWhomever(OutputProc op)
            {
                int outputCount = 0;
                int result = op("Hello, whomever!", ref outputCount);
                Console.WriteLine("OutputCount = {0}", outputCount);
                Console.WriteLine("result = {0}", result);
            }
        }








        public enum Breath
        {
            Sweet = 6, Minty = 12, Putrid = 18, Rancid, Garlicky, Deadly
        }

        public class Company
        {
            public Company()
            {
                
            }

            public static string operator+(Company c, Asset a)
            {
                return "Company::Company + Asset";
            }
            public static string operator +(Asset a, Company c)
            {
                return "Company::Asset + Company";
            }
        }
        public class Asset
        {
            public Asset()
            {
                
            }
            public static string operator+(Asset a, Company c)
            {
                return "Asset:: Asset + Company";
            }
            public static string operator +(Company c, Asset a)
            {
                return "Asset:: Company + Asset";
            }
        }

        public class Person
        {
            public Person()
                : this("", "", 1)
            {

            }
            public Person(string f, string l, int a)
            {
                this.m_lname = l;
                FirstName = f;
                this.m_age = a;

                s_personCt++;
            }

            public event EventHandler FirstNameModifed;
            public class PersonEventArgs : EventArgs
            {
                public bool Veto;
            }

            //{
            //    add
            //    {
            //        Console.WriteLine("Foo");
            //        this.FirstNameModifed += value;
            //    }
            //    remove
            //    {
            //        Console.WriteLine("Foo");
            //    }
            //}

            public static Person operator +(Person lhs, Person rhs)
            {
                return new Person(lhs.FirstName+rhs.FirstName, "Together", lhs.Age+rhs.Age);
            }
            public static Person operator +(Person lhs, string rhs)
            {
                return new Person(lhs.FirstName, rhs, lhs.Age);
            }
            public static Person operator +(string lhs, Person rhs)
            {
                return new Person(rhs.FirstName, lhs, rhs.Age);
            }

            public void GetOlder(out int newAge)
            {
                m_age += 1;
                Console.WriteLine("Happy birthday " + this.ToString() + "!");
                newAge = m_age;
            }

            public override string ToString()
            {
                return String.Format("[Person: {0} {1} ({2})", m_fname, m_lname, m_age);
            }

            public static uint PersonCount
            {
                get
                {
                    return s_personCt;
                }
            }

            public string FirstName
            {
                get
                {
                    return m_fname;
                }
                set
                {
                    PersonEventArgs pea = new PersonEventArgs();
                    FirstNameModifed(this, pea);
                    if (pea.Veto)
                        return;
                    m_fname = value;
                }
            }
            public int Age
            {
                get
                {
                    return m_age;
                }
                set
                {
                    if (value < 1)
                        throw new ArgumentException("Canot set age to negative or zero value");
                    m_age = value;
                }
            }

            private string m_fname;
            private string m_lname = "";
            private int m_age = 1;

            private static uint s_personCt;

            public Person Instructor
            {
                get
                {
                    return s_instructor;
                }
            }

            private static Person s_instructor;

            static Person()
            {
                s_instructor = new Person("Ted", "Neward", 38);
            }
        }

        class OldProgram
        {
            static void OldMain()
            {
                Person p1 = new Person("Joe", "Bob", 15);
                Person p2 = new Person("Joe", "Bob", 15);
                Person p3 = p1;
                Person p4 = new Person("Faye", "Waye", 21);

                Company c = new Company();
                Asset a = new Asset();
                //string r = c + a;
                //Console.WriteLine(r);
                
                Person p = new Person("Bob", "Donaway", 29);
                System.Console.WriteLine(p);
                p.FirstName = "Winklethorpe";
                Console.WriteLine(p);

                Console.WriteLine("Persons = {0}", Person.PersonCount);

                int age;
                p.GetOlder(out age);
                Console.WriteLine("{0} is {1}", p, age);
            }



            //private static readonly int age = DateTime.Now.DayOfYear;
            //static void Main(string[] args)
            //{
            //    Breath bobs = Breath.Sweet | Breath.Putrid | Breath.Minty;
            //    Console.WriteLine(bobs);

            //    Console.WriteLine("===============");

            //    String language = "C#";
            //    string message = String.Format("Hello, C#", language);
            //    Console.WriteLine("Hello, {1} and {0} {2}", language, language, 3);

            //    short s2 = 15;
            //    int x = 5; 
            //    uint y = 5;
            //    float f = 4.0f;
            //    double d = 4.0;
            //    const decimal dd = 0m;
            //    Console.WriteLine(dd);
            //    string s = "message";
            //    x.ToString();
            //    string fiveString = 5.ToString();

            //    int[] xarray = new int[] {5, 6, 7};
            //    for (int i = 0; i<xarray.Length; i++)
            //        Console.WriteLine("{0}", xarray[i]);
            //    foreach (int j in xarray)
            //        Console.WriteLine(j);

            //    int[][] xyarray = new int[][] { new int[] { 3, 1}, new int[] { 4, 1, 5, 8} };
            //    int[,] xyarray2 = new int[,]
            //                          {
            //                              {5, 4, 3, 2, 1},
            //                              {5, 4, 3, 2, 1}
            //                          };
            //    int onebytwo = xyarray2[0, 0];

            //    Console.WriteLine("Breath is {0}", Breath.Deadly);
            //}
        }
    }
}
