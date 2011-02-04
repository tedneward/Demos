using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Procedural
{
    class Program
    {
        [Flags]
        enum Breath
        {
            MINTY,
            WARM,
            COLD,
            RANCID
        }

        [Flags]
        enum MoreBreath
        {
            MINTY,
            COLD,
            WARM,
            RANCID,
            GARLICKY
        }

        static void Main(string[] args)
        {
            Console.WriteLine("Hello world!");
            Console.WriteLine("{0} is a {1}",
                "Ted", "cool guy");
            Console.WriteLine("{0} is a {1}",
                "Brian");
        }
        static void PrintBreath(int b)
        {
//            if ((b & Breath.COLD) != 0)
//                Console.WriteLine("Brr....");
        }
    }
}
