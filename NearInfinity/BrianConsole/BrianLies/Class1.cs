using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace BrianLies
{
    public class Class1
    {
        public static void DoMe()
        {
            Console.WriteLine(
                System.Reflection.Assembly.GetExecutingAssembly().CodeBase);
        }
    }
}
