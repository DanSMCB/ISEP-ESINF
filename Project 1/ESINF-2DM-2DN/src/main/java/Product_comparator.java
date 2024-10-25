import java.util.Comparator;

public class Product_comparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        if(p1.getYear().compareTo(p2.getYear()) == 0){
            if(p1.getValue().compareTo(p2.getValue()) > 0) return -1;
            else if(p1.getValue().compareTo(p2.getValue()) < 0) return 1;
            else return 0;
        }else if(p1.getYear().compareTo(p2.getYear()) > 0) return 1;
        else return -1;
    }
}