using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

class Utils
{
    public static void Dump<T>(T obj)
    {
        Type t = typeof(T);
        // ....
    }
}

class Dumper<T> where T : new()
{
    private static T theInstance = new T();

    public Dumper(T val)
    {
        this.val = val;
    }

    public override string ToString()
    {
        return val.ToString();
    }

    private T val;
}

/* class Dumper<int>
{
    public Dumper(int val)
    {
    }

    public override string ToString()
    {
        return "5";
    }
} */

namespace Meta
{
    class Program
    {
        static void Main(string[] args)
        {
        }
    }
}
