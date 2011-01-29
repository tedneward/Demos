public interface SortedList<T> extends List<T>
{
    public Comparator<T> getComparator();
    public void setComparator(Comparator<T> c);
}
