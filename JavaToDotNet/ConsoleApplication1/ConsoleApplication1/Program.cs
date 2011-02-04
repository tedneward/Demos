using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ConsoleApplication1
{
    class Utility
    {
        public static void DoSomethingUseful()
        {
            Console.WriteLine("I'm useful!");
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            Utility.DoSomethingUseful();
            Console.WriteLine("Hello, .NET!");
        }
    }
}
