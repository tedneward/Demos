package com.nfjs.sched;


public class Show
{
    public Show(String name, String location, String dates)
    {
        this.name = name; this.location = location; this.dates = dates;
    }
    
    public String toString() {
        return name + " (" + location + ") " + dates;
    }

    public final String name;
    public final String location;
    public final String dates;
}