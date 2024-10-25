import java.util.Objects;

public class Year implements Comparable<Year> {
//    Area Code (FAO),Area;   ---> Country
//    Item Code (FAO),Item;    ---> Item
//    Year Code,Year;          ---> Year
//    Unit,Value;              ---> Value

    private int yearCode;
    private int year;

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

    public int compareTo(Year year) {
        return this.getYear() - year.getYear();
    }




//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Year year1 = (Year) o;
//        return yearCode == year1.yearCode && year == year1.year;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(yearCode, year);
//    }
//
//    @Override
//    public int compareTo(Year o) {
//        return this.year;
//    }
}
