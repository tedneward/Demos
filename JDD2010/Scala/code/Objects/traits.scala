trait BoundPropertyBean
{
    import java.beans._

    val pcs = new PropertyChangeSupport(this)

    def addPropertyChangeListener(pcl : PropertyChangeListener) =
        pcs.addPropertyChangeListener(pcl)

    def removePropertyChangeListener(pcl : PropertyChangeListener) =
        pcs.removePropertyChangeListener(pcl)

    def firePropertyChange(name : String, oldVal : _, newVal : _) : Unit =
        pcs.firePropertyChange(new PropertyChangeEvent(this, name, oldVal, newVal))
}

class Person(var firstName:String, var lastName:String, var age:Int)
    extends Object
    with BoundPropertyBean
{
    def setFirstName(newvalue:String) =
    {
        val oldvalue = firstName
        firstName = newvalue
        firePropertyChange("firstName", oldvalue, newvalue)
    }

    def setLastName(newvalue:String) =
    {
        val oldvalue = lastName
        lastName = newvalue
        firePropertyChange("lastName", oldvalue, newvalue)
    }

    def setAge(newvalue:Int) =
    {
        val oldvalue = age
        age = newvalue
        firePropertyChange("age", oldvalue, newvalue)
    }

    override def toString = "[Person: firstName=" + firstName +
        " lastName=" + lastName + " age=" + age + "]"
}


object PCL
    extends java.beans.PropertyChangeListener
{
    override def propertyChange(pce:java.beans.PropertyChangeEvent):Unit =
    {
        System.out.println("Bean changed its " + pce.getPropertyName() +
            " from " + pce.getOldValue() +
            " to " + pce.getNewValue())
    }
}
object App extends Application
{
    import java.beans._

    val p = new Person("Jennifer", "Aloi", 29)

    p.addPropertyChangeListener(PCL)

    p.setFirstName("Jenni")
    p.setAge(30)

    System.out.println(p)
}
