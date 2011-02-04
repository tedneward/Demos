using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using DomainStuff;

namespace PersonApp
{
    class Program
    {
        static void Aged(object sender, EventArgs args)
        {
            if (sender is Person)
            {
                var p = sender as Person;
                if (p.Age >= 40)
                    Console.WriteLine("You old!");
            }
        }
        static void Main(string[] args)
        {
            var ted = new Person("Ted", "Neward", 38);
            ted.OnAgeChange += Aged;

            Console.WriteLine("ted is {0}", ted);

            ted.Age = ted.Age + 1;
            Console.WriteLine("Ted is now {0}", ted.Age);

            ted.Age = ted.Age + 1;
            Console.WriteLine("Ted is now {0}", ted.Age);
        }
    }
}
