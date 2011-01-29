public final class None<T>
    extends Option<T>
{
    public None() { }

    public boolean isSome() { return false; }
    public boolean isNone() { return true; }
    
    public T get() { throw new UnsupportedOperationException(); }
}
