import java.util.Comparator;
import java.util.Objects;

public class Value {

//    Area Code (FAO),Area;   ---> Country
//    Item Code (FAO),Item;    ---> Item
//    Year Code,Year;          ---> Year
//    Unit,Value;              ---> Value
    private String unit;
    private int value;

    public Value(String unit, int value) {
        this.unit = unit;
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int compareTo(Value value) {
        return this.getValue() - value.getValue();
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Value value1 = (Value) o;
//        return unit == value1.unit && value == value1.value;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(unit, value);
//    }
//
//    @Override
//    public int compareTo(Value o) {
//        return 0;
//    }
}
