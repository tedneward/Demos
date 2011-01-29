public interface Operation<R, T>
{
	public R apply(T item);
}