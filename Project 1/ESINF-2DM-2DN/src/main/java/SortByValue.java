import java.util.Comparator;

public class SortByValue implements Comparator<Value> {
    // Used for sorting in ascending order of quantity
    public int compare(Value a, Value b)
    {
        return a.getValue() - b.getValue();
    }
}
