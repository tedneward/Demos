using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

class Rectangle
{
    public virtual int Height { get; set; }
    public virtual int Width { get; set; }
    public override string ToString()
    {
        return String.Format("{0} x {1}",
            Height, Width);
    }
}

class Square : Rectangle
{
    public override int Height
    {
        get
        {
            return base.Height;
        }
        set
        {
            base.Height = value;
            if (value != Width)
                this.Width = value;
        }
    }
    public override int Width
    {
        get
        {
            return base.Width;
        }
        set
        {
            base.Width = value;
            if (value != Height)
                this.Height = value;
        }
    }
}

namespace Objects
{
    class Program
    {
        static void Main(string[] args)
        {
            Rectangle r = new Square()
            {
                Height = 12,
                Width = 12
            };
            Console.WriteLine(r);
            EditRectangle(r);
            Console.WriteLine(r);
        }
        static void EditRectangle(Rectangle r)
        {
            r.Height = r.Height + 1;
            r.Width = r.Width + 1;
        }
    }
}
