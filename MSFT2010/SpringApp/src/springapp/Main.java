/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package springapp;

import org.springframework.context.*;
import org.springframework.context.support.*;

/**
 *
 * @author Ted
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ApplicationContext appContext =
                new FileSystemXmlApplicationContext(
                    "C:/Projects/Demos/MSFT2010/SpringApp/app.xml");
        ISpeak speaker = (ISpeak) appContext.getBean("speaker");

        System.out.println(speaker.sayHello());

        ISpeak speaker2 = (ISpeak) appContext.getBean("speaker");

        System.out.println(speaker2.sayHello());

        ISpeak john = (ISpeak) appContext.getBean("john");

        System.out.println(john.sayHello());
    }

}
