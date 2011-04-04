using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ConsoleApplication1
{
    class Person
    {
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public int Age { get; set; }
    }

    class Program
    {
        static void Main(string[] args)
        {
            List<Person> people = new List<Person>()
            {
                new Person() { FirstName="Ted", LastName="Neward", Age=40 },
                new Person() { FirstName="Charlotte", LastName="Neward", Age=39 }
            };

        }
    }
}
