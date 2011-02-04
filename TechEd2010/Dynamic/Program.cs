using System;
using System.Dynamic;
using System.Reflection;
using System.Collections.Generic;
using System.Linq;
using System.Text;

class CallMe : System.Dynamic.DynamicObject
{
    public override bool TryInvokeMember(
        InvokeMemberBinder binder, 
        object[] args, out object result)
    {
        Console.WriteLine("You called " + binder.Name);
        result = null;
        return true;
    }
}


class Person
{
    public string FirstName { get; set; }
}

namespace Dynamic
{
    class Program
    {
        static void Main(string[] args)
        {
            dynamic p = new Person() { FirstName = "Sarah" };
            Console.WriteLine(p.FirstName);
            //FindProperties(p);

            dynamic c = new CallMe();
            c.Spot();
            c.SpotRun();
            c.RunSpotRun();
            c.Dick();
            c.Dick("call", "Jane");

            dynamic e = new ExpandoObject();
            e.JokeName = "StretchArmstrong";
            e.JokeEfficacy = 0;
            e.HowDidTheJokeGoOver = (Action)
                (() => Console.WriteLine("It went over like a fart in church"));
            e.HowDidTheJokeGoOver();
        }

        static void FindProperties(object o)
        {
            Type t = o.GetType();
            PropertyInfo pi = t.GetProperty("FirstName");
            Console.WriteLine(pi.GetValue(o, null));
        }
    }
}
