public class Product {

//    Area Code (FAO),Area;   ---> Country
//    Item Code (FAO),Item;    ---> Item
//    Year Code,Year;          ---> Year
//    Unit,Value;              ---> Value

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
