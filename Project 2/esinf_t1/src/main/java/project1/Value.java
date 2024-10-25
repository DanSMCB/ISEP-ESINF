package project1;

public class Value {

//    Area Code (FAO),Area;   ---> progect1.Country
//    progect1.Item Code (FAO),progect1.Item;    ---> progect1.Item
//    progect1.Year Code,progect1.Year;          ---> progect1.Year
//    Unit,progect1.Value;              ---> progect1.Value
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
//        progect1.Value value1 = (progect1.Value) o;
//        return unit == value1.unit && value == value1.value;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(unit, value);
//    }
//
//    @Override
//    public int compareTo(progect1.Value o) {
//        return 0;
//    }
}
