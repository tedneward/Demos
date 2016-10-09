/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thirstyhead;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author Ted
 */

@Path("/helloworld")
public class BookResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of BookResource */
    public BookResource() {
    }

    /**
     * Retrieves representation of an instance of com.thirstyhead.BookResource
     * @return an instance of java.lang.String
     */
    @GET
    public String getXml()
    {
        return "Hello world";
    }

    /**
     * PUT method for updating or creating an instance of BookResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    public void putXml(String content)
    {
    }
}
