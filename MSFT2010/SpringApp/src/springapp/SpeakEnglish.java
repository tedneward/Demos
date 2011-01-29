/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springapp;

/**
 *
 * @author Ted
 */
public class SpeakEnglish
    implements ISpeak
{
    private String message;

    public SpeakEnglish()
    {
        System.out.println("ctor!!");
    }

    public void setMessage(String value)
    {
        message = value;
    }

    public String sayHello()
    {
        return message;
    }
}
