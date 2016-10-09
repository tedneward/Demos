import java.util.*;

interface Action<T>
{
    public void f(T candidate);
}

class Utils
{
    public static <T> void apply(List<T> data, Action<? super T> act)
    {
        for (T it : data)
            act.f(it);
    }
    public static final Action<Object> PRINTER =
        new Action<Object>() {
            public void f(Object obj) {
                System.out.println(obj);
            }
        };
    public static final Action<String> STRPRINT =
        new Action<String>() {
            public void f(String s) {
                System.out.println(s.toUpperCase());
            }
        };
}

public class Appl
{
    public static void main(String[] args)
    {
        List<String> names = Arrays.asList(
            "Penny", "Howard", "Leonard", "Sheldon", "Raj"
        );
        Utils.apply(names, Utils.PRINTER);
        Utils.apply(names, Utils.STRPRINT);
    }
}