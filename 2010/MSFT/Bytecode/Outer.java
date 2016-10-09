public class Outer
{
    private int scottsAge = 39;
    
    public class Inner
    {
        public void happyBirthday() {
            scottsAge += 1;
        }
        public String toString() {
            return "Scott is still " + scottsAge + " years old";
        }
    }
    public Inner getInner() {
        return new Inner();
    }
    
    public String toString() {
        return "Scott is " + scottsAge + " years old";
    }
    

    public static void main(String[] args)
    {
        Outer o = new Outer();
        System.out.println(o.toString());
        
        Outer.Inner i = o.getInner();
        System.out.println(i.toString());
        
        i.happyBirthday();
        System.out.println(o.toString());
        System.out.println(i.toString());        
    }
}






