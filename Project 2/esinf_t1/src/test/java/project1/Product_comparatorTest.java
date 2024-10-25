package project1;

import org.junit.jupiter.api.Test;
import project1.*;

import static org.junit.jupiter.api.Assertions.*;

class Product_comparatorTest {

    public Product_comparator product_comparator = new Product_comparator();

    Product p1 = new Product(new Year(2022, 2022), new Value("tonnes", 1000), new Item(515, "Apples"));
    Product p2 = new Product(new Year(2021, 2021), new Value("tonnes", 1000), new Item(515, "Apples"));
    Product p3 = new Product(new Year(2022, 2022), new Value("tonnes", 900), new Item(515, "Apples"));
    Product p4 = new Product(new Year(2022, 2022), new Value("tonnes", 1000), new Item(515, "Apples"));


    //same year and same value
    @Test
    void compare1() {
        assertEquals(0,product_comparator.compare(p1,p4));
    }

    // same year but p3 value < p1 value
    @Test
    void compare2() {
        assertEquals(1, product_comparator.compare(p3,p1));
    }

    // same year but p1 value > p3 value
    @Test
    void compare3() {
        assertEquals(-1, product_comparator.compare(p1,p3));
    }

    // p1 year > p2 year
    @Test
    void compare4() {
        assertEquals(1, product_comparator.compare(p1,p2));
    }

    // p2 year < p1 year
    @Test
    void compare5() {
        assertEquals(-1, product_comparator.compare(p2,p1));
    }
}