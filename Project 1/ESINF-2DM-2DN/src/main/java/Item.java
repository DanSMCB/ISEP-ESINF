import java.util.Objects;

public class Item {

//    Area Code (FAO),Area;   ---> Country
//    Item Code (FAO),Item;    ---> Item
//    Year Code,Year;          ---> Year
//    Unit,Value;              ---> Value

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
//        Item item1 = (Item) o;
//        return itemCode == item1.itemCode && Objects.equals(item, item1.item);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(itemCode, item);
//    }
//
//    @Override
//    public int compareTo(Item o) {
//        return this.itemCode.;
//    }
}
