public final class Some<T>
    extends Option<T>
{
    public Some(T val) { this.value = val; }

    public boolean isSome() { return true; }
    public boolean isNone() { return false; }
    
    public T get() { return value; }
    
    private final T value;
}
