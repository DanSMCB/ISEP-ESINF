package project2;

import java.nio.file.attribute.AclEntryFlag;

public class Value implements Comparable<Value>{

    private String unit;
    private float value;
    private Flag flag;

    public Value(String unit, float value, Flag flag) {
        this.unit = unit;
        this.value = value;
        this.flag = flag;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }


    public Flag getFlag() {
        return flag;
    }

    public void setFlag(Flag flag) {
        this.flag = flag;
    }

    public int compareTo(Value value) {
        return (int) (this.getValue() - value.getValue());
    }

    @Override
    public String toString() {
        return "Value - " +
                "unit= '" + unit + '\'' +
                ", value= " + value +
                ", flag= " + flag;

    }
}

