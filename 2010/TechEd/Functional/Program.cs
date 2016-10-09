using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

class Person
{
    public string FirstName { get; set; }
    public string LastName { get; set; }
    public int Age { get; set; }
}

class Utils
{
    public delegate bool FilterProc<T>(T it);

    public static List<T> Filter<T>(
        List<T> src, FilterProc<T> filter)
    {
        List<T> results = new List<T>();
        foreach (var it in src)
            if (filter(it))
                results.Add(it);
        return results;
    }


    public delegate R MapProc<R,T>(T it);
    public static List<R> Map<R,T>(
        List<T> src, MapProc<R,T> map)
    {
        List<R> results = new List<R>();
        foreach (var it in src)
            results.Add(map(it));
        return results;
    }

    public delegate R FoldProc<R,T>(R accum, T it); 
    public static R Fold<R,T>(R seed, List<T> src, 
        FoldProc<R,T> fold)
    {
        R accum = seed;
        foreach (var it in src)
            accum = fold(accum, it);
        return accum;
    }


    public static void Act<T>(List<T> src, Action<T> act)
    {
        List<T> results = new List<T>();
        foreach (var it in src)
            act(it);
    }
}

class Program
{
    static void Main(string[] args)
    {
        List<Person> people = new List<Person>() {
            new Person() { FirstName = "Sarah", LastName = "Guerrero", Age = 19 },
            new Person() { FirstName = "KJ", LastName ="Haugen", Age = 34 },
            new Person() { FirstName = "Tor", LastName = "Smith", Age = 20 },
            new Person() { FirstName = "Anita", LastName = "RealComplicated", Age=29 },
            new Person() { FirstName = "Ted", LastName = "Neward", Age = 39 },
            new Person() { FirstName = "Michael", LastName = "Neward", Age = 16 },
            new Person() { FirstName = "Matthew", LastName = "Neward", Age = 10 }
        };
        List<Person> drinkers =
            Utils.Filter(people, 
                delegate(Person p) { return p.Age >= 21; });
        Utils.Act(drinkers,
                delegate(Person p) { Console.WriteLine("have a beer, {0}!", p.FirstName); });

        List<string> lastNames = 
            Utils.Map(people,
                delegate(Person p) { return p.LastName; });
        Utils.Act(lastNames,
            (string s) => Console.WriteLine(s));

        int age = 0;
        foreach (Person p in people)
            age = age + p.Age;
        Console.WriteLine("totalages = {0}", age);

        int totalAge = Utils.Fold(0, people, 
            (int acc, Person p) => acc + p.Age );
        Console.WriteLine("totalages = {0}", totalAge);

        string xml = Utils.Fold("<persons>", people,
            (string x, Person p) => x + "<person>" + p.FirstName + "</person>") + "</people>";
        Console.WriteLine(xml);


    }
}
