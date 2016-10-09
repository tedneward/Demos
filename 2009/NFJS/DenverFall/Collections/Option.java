public abstract class Option<T>
{
    public abstract T get();
    
    public abstract boolean isSome();
    public abstract boolean isNone();

    public static <T> boolean isSome(Option<T> opt) { return opt instanceof Some; }
    public static <T> boolean isNone(Option<T> opt) { return opt instanceof None; }
}
