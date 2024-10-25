package project1;

import java.util.Objects;

public class Item {

//    Area Code (FAO),Area;   ---> progect1.Country
//    progect1.Item Code (FAO),progect1.Item;    ---> progect1.Item
//    progect1.Year Code,progect1.Year;          ---> progect1.Year
//    Unit,progect1.Value;              ---> progect1.Value

    private int itemCode;
    private String item;

    public Item(int itemCode, String item) {
        this.itemCode = itemCode;
        this.item = item;
    }

    public int getItemCode() {
        return itemCode;
    }

    public String getItem() {
        return item;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public void setItem(String item) {
        this.item = item;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        progect1.Item item1 = (progect1.Item) o;
//        return itemCode == item1.itemCode && Objects.equals(item, item1.item);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(itemCode, item);
//    }
//
//    @Override
//    public int compareTo(progect1.Item o) {
//        return this.itemCode.;
//    }
}
