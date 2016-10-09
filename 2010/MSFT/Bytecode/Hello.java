public class Hello
{
    public static void main(String[] args)
    {
        int i = 12;
        float f = i;
     
        try
        {
            int result = i / 0;
        }
        catch (ArithmeticException d)
        {
            d.printStackTrace();
        }
        catch (Exception x)
        {
            x.printStackTrace();
        }
           
        System.out.println("Hello java");
        System.out.println("i = " + i);
        System.out.println("f = " + f);
    }
}