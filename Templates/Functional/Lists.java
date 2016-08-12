import java.util.*;

public class Lists
{
  
  
  
  /*
  public static <T> void map(Function1<Void, T> mapFn, List<T> list)
  {
    for (T t : list)
    	mapFn.apply(t);
  }
  public static <R,T> List<R> transform(Function1<R, T> transFn, List<T> list)
  {
    List<R> results = new ArrayList<R>(list.size());
    for (T t : list)
    	results.add(transFn.apply(t));
    return results;
  }
  public static <R,T> R fold(R seed, List<T> src, Function2<R,R,T> fold)
  {
    R accum = seed;
    for (T it : src)
      accum = fold.apply(accum, it);
    return accum;
  }
  public static <T> List<T> filter(List<T> src, Function1<Boolean, T> criteria)
  {
    List<T> results = new ArrayList<T>();
    for (T it : src)
      if (criteria.apply(it))
        results.add(it);
    return results;
  }
  public static <T> Tuple2<Boolean,T> find(List<T> src, Function1<Boolean,T> criteria)
  {
    for (T it : src)
      if (criteria.apply(it))
        return new Tuple2<Boolean,T>(true, it);
    return new Tuple2<Boolean,T>(false, null);
  }
  */
}
