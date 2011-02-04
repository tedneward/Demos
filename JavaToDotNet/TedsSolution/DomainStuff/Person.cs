using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DomainStuff
{
    public class Person
    {
        public Person(string fn, string ln, int a)
        {
            FirstName = fn;
            LastName = ln;
            Age = a;
        }
        public Person()
        {
            FirstName = "";
            LastName = "";
            Age = 0; // redundant
        }

        public int Age
        {
            get
            {
                return age;
            }
            set
            {
                age = value;
                if (OnAgeChange != null)
                    OnAgeChange(this, null);
            }
        }
        public string FirstName
        {
            get;
            set;
        }
        public string LastName
        {
            get;
            set;
        }

        public event EventHandler OnAgeChange;

        public override string ToString()
        {
            return String.Format("[Person: {0} {1} ({2} yrs old)]",
                FirstName, LastName, Age);
        }

        public override bool Equals(object obj)
        {
            if (obj == this)
                return true;

            if (obj is Person)
            {
                Person other = obj as Person;
                return other.FirstName.Equals(this.FirstName) &&
                    other.LastName.Equals(this.LastName);
            }

            return false;
        }
        public override int GetHashCode()
        {
            return FirstName.GetHashCode() &
                LastName.GetHashCode() &
                Age;
        }

        private int age;
    }
}
