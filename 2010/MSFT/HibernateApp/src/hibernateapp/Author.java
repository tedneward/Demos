/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernateapp;

/**
 *
 * @author Ted
 */
public class Author implements java.io.Serializable
{
    Author() { }
    public Author(String fn, String ln) { firstName = fn; lastName = ln; }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    public void setId(Long value) { id = value; }
    public void setFirstName(String value) { firstName = value; }
    public void setLastName(String value) { lastName = value; }

    public @Override String toString()
    {
        return "Author{firstName=" + firstName + " lastName=" + lastName + "}";
    }

    private Long id;
    private String firstName;
    private String lastName;
}
