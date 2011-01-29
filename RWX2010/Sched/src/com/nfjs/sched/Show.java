package com.nfjs.sched;

import org.json.JSONObject;

/**
 * Created by Ted at 9:43:17 AM on Dec 3, 2010
 */
public class Show
    implements java.io.Serializable
{
    public Show(String name, JSONObject location, String dates)
    {
        this.name = name;
        this.location = new Location(location);
        this.dates = dates;
    }

    public String toString() {
        return name + " (" + location.city + " " + location.stateCode + ") " + dates;
    }

    public final String name;
    public final Location location;
    public final String dates;
}