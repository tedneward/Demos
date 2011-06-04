using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DomainStuff
{
    public class Person
    {
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public int Age { get; set; }
        public string EMail { get; set; }

        public static string GetMessage()
        {
            return "Dude! I'm a Person!";
        }
    }
}
