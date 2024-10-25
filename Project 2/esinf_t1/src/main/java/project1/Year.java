package project1;

public class Year implements Comparable<Year> {
//    Area Code (FAO),Area;   ---> progect1.Country
//    progect1.Item Code (FAO),progect1.Item;    ---> progect1.Item
//    progect1.Year Code,progect1.Year;          ---> progect1.Year
//    Unit,progect1.Value;              ---> progect1.Value

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
//        progect1.Year year1 = (progect1.Year) o;
//        return yearCode == year1.yearCode && year == year1.year;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(yearCode, year);
//    }
//
//    @Override
//    public int compareTo(progect1.Year o) {
//        return this.year;
//    }
}
