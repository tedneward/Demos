package com.nfjs.sched;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ted at 12:13:24 PM on Dec 3, 2010
 */
public class Location
    implements java.io.Serializable
{
    public Location(JSONObject jsonObj)
    {
        try
        {
            name = jsonObj.getString("metroArea");
            address1 = jsonObj.getString("address1");
            address2 = jsonObj.getString("address2");
            city = jsonObj.getString("city");
            stateCode = jsonObj.getString("stateCode");
            zip = jsonObj.getString("zip");
        }
        catch (JSONException jsonEx)
        {
            throw new IllegalStateException("JSON parse failed: " + jsonEx);
        }
    }

    public final String name;
    public final String address1;
    public final String address2;
    public final String city;
    public final String stateCode;
    public final String zip;
}
