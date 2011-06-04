using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;

using DomainStuff;

namespace ConsoleApplication1
{
    class Program
    {
        static void Main(string[] args)
        {
            Person michael =
                new Person() { FirstName = "Michael", LastName = "Neward" };
            Console.WriteLine(michael);
            Type t = michael.GetType();
            Console.WriteLine("{0}::{1}", t.Assembly, t.FullName);
            Console.WriteLine(t.Assembly.CodeBase);

            //Assembly ds =
            //    //Assembly.LoadFrom(@"C:\Users\Ted\DomainStuff.dll");
            //    Assembly.Load("DomainStuff, Version=1.0.0.0");
            //Console.WriteLine(ds);
            //Console.WriteLine(ds.CodeBase);
        }
    }
}
