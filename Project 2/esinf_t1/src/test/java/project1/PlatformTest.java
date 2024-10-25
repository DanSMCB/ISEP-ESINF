package project1;

import org.junit.jupiter.api.Test;
import project1.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PlatformTest {
    private Platform instance;

    public PlatformTest() throws IOException, Exception {
        instance = new Platform();
        List<String> l = Files.lines(Paths.get("FAOSTAT_data_en_9-7-2022_SMALL.csv")).collect(Collectors.toList());
        instance.getCountry(l);
    }

    @Test
    void getCountrySmall() throws IOException,Exception {
        instance = new Platform();
        List<String> l = Files.lines(Paths.get("FAOSTAT_data_en_9-7-2022_SMALL.csv")).collect(Collectors.toList());
        instance.getCountry(l);
    }

    @Test
    void getCountryBig() throws IOException,Exception {
        instance = new Platform();
        List<String> l = Files.lines(Paths.get("FAOSTAT_data_en_9-7-2022_BIG.csv")).collect(Collectors.toList());
        instance.getCountry(l);
    }

    @Test
    void sortProductList(){
        Product p1 = new Product(new Year(2022, 2022), new Value("tonnes", 1000), new Item(515, "a"));
        Product p2 = new Product(new Year(2021, 2021), new Value("tonnes", 1000), new Item(515, "b"));
        Product p3 = new Product(new Year(2022, 2022), new Value("tonnes", 900), new Item(515, "c"));
        Product p4 = new Product(new Year(2022, 2022), new Value("tonnes", 500), new Item(515, "d"));
        List<Product> productList = new ArrayList<>();
        productList.add(p1);
        productList.add(p2);
        productList.add(p3);
        productList.add(p4);

        productList.sort(new Product_comparator());

        assertEquals("b", productList.get(0).getItem().getItem());
        assertEquals("a", productList.get(1).getItem().getItem());
        assertEquals("c", productList.get(2).getItem().getItem());
        assertEquals("d", productList.get(3).getItem().getItem());
    }

    @Test
    void getKey(){
        Product p1 = new Product(new Year(2017, 2017), new Value("tonnes", 1000), new Item(515, "Apples"));
        Product p2 = new Product(new Year(2018, 2018), new Value("tonnes", 1000), new Item(515, "Apples"));
        Product p3 = new Product(new Year(2019, 2019), new Value("tonnes", 900), new Item(515, "Apples"));
        Product p4 = new Product(new Year(2020, 2020), new Value("tonnes", 1000), new Item(515, "Apples"));
        Set<Product> sp1 = new HashSet<>();
        sp1.add(p1);
        Set<Product> sp2 = new HashSet<>();
        sp2.add(p2);
        Set<Product> sp3 = new HashSet<>();
        sp3.add(p3);
        Set<Product> sp4 = new HashSet<>();
        sp4.add(p4);


        Country c1 = new Country(174, "Portugal");
        Country c2 = new Country(203, "Spain");
        Country c3 = new Country(2, "Afghanistan");
        Country c4 = new Country(3, "Albania");


        Map<Country, Set<Product>> sup = new HashMap<>();
        sup.put(c2, sp1);
        sup.put(c1, sp2);
        sup.put(c3, sp3);
        sup.put(c4, sp4);

        assertEquals(c1, instance.getKey(sup, p2));
    }

//    @Test
//    public void country_listTest(){
//        Product p1 = new Product(new Year(2017, 2017), new Value("tonnes", 1000), new Item(515, "Apples"));
//        Product p2 = new Product(new Year(2018, 2018), new Value("tonnes", 1000), new Item(515, "Apples"));
//        Product p3 = new Product(new Year(2019, 2019), new Value("tonnes", 900), new Item(515, "Apples"));
//        Product p4 = new Product(new Year(2020, 2020), new Value("tonnes", 1000), new Item(515, "Apples"));
//        Set<Product> sp1 = new HashSet<>();
//        sp1.add(p1);
//        Set<Product> sp2 = new HashSet<>();
//        sp2.add(p2);
//        Set<Product> sp3 = new HashSet<>();
//        sp3.add(p3);
//        Set<Product> sp4 = new HashSet<>();
//        sp4.add(p4);
//
//
//        Country c1 = new Country(174, "Portugal");
//        Country c2 = new Country(203, "Spain");
//        Country c3 = new Country(2, "Afghanistan");
//        Country c4 = new Country(3, "Albania");
//
//
//        Map<Country, Set<Product>> sup = new HashMap<>();
//        sup.put(c2, sp1);
//        sup.put(c1, sp2);
//        sup.put(c3, sp3);
//        sup.put(c4, sp4);
//
////        assertEquals(countryList, instance.country_list("Apples", 1000));
//        assertEquals(c2, instance.country_list(sup, "Apples", 1000).get(0));
//    }

    @Test
    public void numeroMaxAnosConsecutivosCrescimentoFrutoF1() throws IOException {
        System.out.println("EX4.numeroMaxAnosConsecutivosCrescimentoFrutoF");
        Map <Country, Integer> result = new HashMap<>();
        result.put(new Country(174, "Portugal"), 2);
        result.put(new Country(203, "Spain"), 2);

        Map <Country, Integer> expResult = instance.numMaxAnosConsecutivosCrescimentoFrutoF("Apples");
        assertEquals(result, expResult);
    }

    @Test
    public void numeroMaxAnosConsecutivosCrescimentoFrutoF2() throws IOException {
        System.out.println("EX4.numeroMaxAnosConsecutivosCrescimentoFrutoF");
        Map <Country, Integer> result = new HashMap<>();
        result.put(new Country(174, "Portugal"), 4);
        result.put(new Country(203, "Spain"), 4);

        Map <Country, Integer> expResult = instance.numMaxAnosConsecutivosCrescimentoFrutoF("Bananas");
        assertEquals(result, expResult);
    }

    @Test
    public void numMinPaisesComQuantidadeProdSuperiorQ1() {
        System.out.println("EX3.numMinPaisesComQuantidadeProdSuperiorQ");
        int value = 50000;
        int result = instance.numMinPaisesComQuantidadeProdSuperiorQ(value);
        int expResult = 1;
        assertEquals(expResult, result);
    }

    @Test
    public void numMinPaisesComQuantidadeProdSuperiorQ2() {
        System.out.println("EX3.numMinPaisesComQuantidadeProdSuperiorQ");
        int value = 1000000000;
        int result = instance.numMinPaisesComQuantidadeProdSuperiorQ(value);
        int expResult = 2;
        assertEquals(expResult, result);
    }

    @Test
    public void diferenceMaximaDasQuantidades1() {
        System.out.println("EX5.diferenceMaximaDasQuantidades");
        Map<Item, Object[]> result = new HashMap<>();
        Object[] obj1 = {2010, 2019, 157808};
        Object[] obj2 = {2000, 2009, 10547};
        result.put(new Item(515, "Apples"), obj1);
        result.put(new Item(486, "Bananas"), obj2);

        Map<Item, Object[]> expResult = instance.diferenceMaximaDasQuantidades("Portugal");
        assertEquals(expResult, result);

    }

    @Test
    public void diferenceMaximaDasQuantidades2() {
        System.out.println("EX5.diferenceMaximaDasQuantidades");
        Map<Item, Object[]> result = new HashMap<>();
        Object[] obj1 = {2001, 2012, 436186};
        Object[] obj2 = {2001, 2005, 76775};
        result.put(new Item(515, "Apples"), obj1);
        result.put(new Item(486, "Bananas"), obj2);

        Map<Item, Object[]> expResult = instance.diferenceMaximaDasQuantidades("Spain");
        assertEquals(expResult, result);
    }



}