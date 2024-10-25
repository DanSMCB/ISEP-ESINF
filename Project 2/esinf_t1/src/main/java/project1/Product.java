package project1;

import project1.Item;

public class Product {

//    Area Code (FAO),Area;   ---> progect1.Country
//    progect1.Item Code (FAO),progect1.Item;    ---> progect1.Item
//    progect1.Year Code,progect1.Year;          ---> progect1.Year
//    Unit,progect1.Value;              ---> progect1.Value

    private Year year;
    private Value value;
    private Item item;

    public Product(Year year, Value value, Item item) {
        this.year = year;
        this.value = value;
        this.item = item;
    }

    public Year getYear() {
        return year;
    }

    public Value getValue() {
        return value;
    }

    public Item getItem() {
        return item;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
