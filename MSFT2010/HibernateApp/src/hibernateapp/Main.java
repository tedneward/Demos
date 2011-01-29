
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateapp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Ted
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        SessionFactory sessFact =
                new Configuration().configure(new java.io.File("C:/Projects/Demos/MSFT2010/HibernateApp/application.cfg.xml")).buildSessionFactory();
        for (int i = 0; i < args.length; i++)
        {
            if (args[i].equals("add"))
            {
                String thing = args[++i];

                if (thing.equals("author"))
                {
                    String firstName = args[++i];
                    String lastName = args[++i];
                    Author newAuthor = new Author(firstName, lastName);

                    System.out.println("Adding " + newAuthor);

                    Session session = sessFact.openSession();
                    Transaction tx = null;
                    try
                    {
                        tx = session.beginTransaction();

                        session.save(newAuthor);
                        
                        tx.commit();
                        tx = null;
                    }
                    catch (HibernateException hex)
                    {
                        if (tx != null) tx.rollback();
                        throw new RuntimeException("ACK! Couldn't save", hex);
                    }
                    finally
                    {
                        session.close();
                    }
                }
                else
                {
                    throw new IllegalArgumentException("Unrecognized syntax: " + thing);
                }
            }
            else if (args[i].equals("list"))
            {
                String thing = args[++i];

                if (thing.equals("author"))
                {
                    Session session = sessFact.openSession();
                    Transaction tx = null;
                    try
                    {
                        tx = session.beginTransaction();

                        // Get all the authors in the database and list them
                        java.util.List<Author> authorList =
                                session.createQuery("from Author").list();
                        for (Author a : authorList)
                            System.out.println(a);

                        tx.commit();
                        tx = null;
                    }
                    catch (HibernateException hex)
                    {
                        if (tx != null) tx.rollback();
                        throw new RuntimeException("ACK! Couldn't save", hex);
                    }
                    finally
                    {
                        session.close();
                    }
                }
            }
            else
            {
                throw new IllegalArgumentException("WTF?!?!?");
            }
        }
    }
}


