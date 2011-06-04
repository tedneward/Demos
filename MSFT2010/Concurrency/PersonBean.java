public interface PersonBeanEventListener {
    public void firstNameChanged(String oldName, String newName);
}


public class PersonBean {
    
    
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String value) {
        for (PersonBeanEventListener l : firstNameListeners)
            l.firstNameChanged(firstName, value);
        firstName = value;
    }
    public void registerFirstNameListener(PersonBeanEventListener pbel) {
        firstNameListeners.add(pbel);
    }
    
    
    private String firstName;
    private List<PersonBeanEventListener> firstNameListeners() =
        new CopyOnWriteArrayList<PersonBeanEventListener>();
}