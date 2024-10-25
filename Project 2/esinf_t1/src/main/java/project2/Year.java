package project2;

public class Year implements Comparable<Year> {

    private int yearCode;
    private int year;
    private Value value;

    public Year(int yearCode, int year, Value value) {
        this.yearCode = yearCode;
        this.year = year;
        this.value=value;
    }

    public Year(int yearCode, int year) {
        this.yearCode = yearCode;
        this.year = year;
    }

    public int getYearCode() {
        return yearCode;
    }

    public int getYear() {
        return year;
    }

    public void setYearCode(int yearCode) {
        this.yearCode = yearCode;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Value getValue(){return value;}

    public void setValue(Value value){this.value=value;}

    public int compareTo(Year year) {
        return this.getYear() - year.getYear();
    }
}
