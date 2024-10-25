package project2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class PlatformTest {

    private final Platform instance = new Platform();

    @BeforeEach
    void setUp() {
        BST<Year> yearBST = new BST<>();
        BST<Year> yearBST1 = new BST<>();
        BST<Element> elementBST = new BST<>();
        BST<Element> elementBST1 = new BST<>();
        BST<Item> itemBST = new BST<>();
        BST<Item> itemBST1 = new BST<>();
        BST<Country> countryBST = new BST<>();

        Flag f1 = new Flag("E", "Estimated value");
        Flag f2 = new Flag("A", "Official figure");

        Value v1 = new Value("hg/ha", 180088.000000F,f1);
        Value v2 = new Value("No/An",35.000000F,f1);
        Value v3 = new Value("tonnes",181000.000000F,f2);
        Value v4 = new Value("tonnes",200000.000000F,f2);

        Year y1 = new Year(1995, 1995, v1);
        Year y2 = new Year(1998, 1998, v2);
        Year y3 = new Year(2000, 2000, v3);
        Year y4 = new Year(2000, 2000, v4);

        yearBST.insert(y1);
        yearBST.insert(y2);
        yearBST.insert(y3);
        yearBST1.insert(y4);

        Element e1 = new Element(5419,"Yield",yearBST);
        Element e2 = new Element(5510,"Production",yearBST);
        Element e3 = new Element(5312,"Area harvested",yearBST1);
        Element e4 = new Element(5419,"Yield",yearBST1);

        elementBST.insert(e1);
        elementBST.insert(e2);
        elementBST.insert(e3);
        elementBST1.insert(e4);


        Item i1 = new Item(515,"'01341","Apples",elementBST);
        Item i2 = new Item(1091,"'0232","Eggs from other birds in shell, fresh, n.e.c.",elementBST);
        Item i3 = new Item(75,"'0117","Oats",elementBST);
        Item i4 = new Item(515,"'01341","Apples",elementBST1);

        itemBST.insert(i1);
        itemBST.insert(i2);
        itemBST.insert(i3);
        itemBST1.insert(i4);

        Country c1 = new Country(67,"Finland",246,itemBST);
        Country c2 = new Country(198,"Slovenia",705,itemBST);
        Country c3 = new Country(27,"Bulgaria",100,itemBST1);

        countryBST.insert(c1);
        countryBST.insert(c2);
        countryBST.insert(c3);

        instance.insertCountry(c1);
        instance.insertCountry(c2);
        instance.insertCountry(c3);
    }

    @Test
    void fillCountryBST() {
        String flagPath = "project2_files/Production_Crops_Livestock_E_Flags.csv";
        String shufflePath = "project2_files/Production_Crops_Livestock_World_shuffle_small.csv";
        Platform platform = new Platform();
        CSVReader csvReader = new CSVReader(shufflePath);
        List<Map<String, String>> listShuffle = csvReader.read();
        List<String> flagsList = csvReader.fileReader(flagPath);
        platform.fillCountryBST(listShuffle,flagsList);

        //Area Code,Area Code (M49),Area,Item Code,Item Code (CPC),Item,Element Code,Element,Year Code,Year,Unit,Value,Flag
        //"189","'662","Saint Lucia","156","'01802","Sugar cane","5419","Yield","1965","1965","hg/ha","800000.000000","E"

        BST<Year> yearBST = new BST<>();
        BST<Element> elementBST = new BST<>();
        BST<Item> itemBST = new BST<>();
        BST<Country> countryBST = new BST<>();
        Country country = new Country(189,"Saint Lucia",662,itemBST);
        Item item = new Item(156,"01802","Sugar cane",elementBST);
        Element element = new Element(5419,"Yield",yearBST);
        Flag flag = new Flag("E","Estimated value");
        Value value = new Value("hg/ha",800000.000000F,flag);
        Year year = new Year(1965,1965,value);
        countryBST.insert(country);
        elementBST.insert(element);
        itemBST.insert(item);
        yearBST.insert(year);
        assertEquals(countryBST.root().getElement(),platform.countryBST.root().getElement());
    }

    @Test
    void mean_value() {
        assertNull(instance.mean_value(new Country(0,"",0,null),1998,2000));

        Map<String, Float> res = instance.mean_value(new Country(67,"Finland",246,null),1998,2000);
        Iterator<Map.Entry<String, Float>> iterator = res.entrySet().iterator();

        Float f1=iterator.next().getValue();
        assertEquals(200000.0f,f1,0.0f);
        iterator.next();
        iterator.next();
        f1=iterator.next().getValue();
        assertEquals(90517.5f,f1,0.0f);

        for(String s : res.keySet()){
            System.out.println(s);
        }
    }

    @Test
    void topN_areas() {
        assertNull(instance.topN_areas(3, new Item(0,"","",null), new Element(0,"",null)));

        Map<Country, Float> res = instance.topN_areas(3, new Item(515,"'01341","Apples",null), new Element(5419,"Yield",null));
        Iterator<Map.Entry<Country, Float>> iterator = res.entrySet().iterator();

        Float f1=iterator.next().getValue();
        assertEquals(200000.0f,f1,0.0f);
        f1=iterator.next().getValue();
        assertEquals(181000.0f,f1,0.0f);
        f1=iterator.next().getValue();
        assertEquals(181000.0f,f1,0.0f);

        for(Country c : res.keySet()){
            System.out.println(c.getArea() + " with value: " + res.get(c));
        }
    }

    @Test
    void area_mais_proxima() {
        System.out.println("EX4.area_mais_proxima");

        Object[] areaInfo = instance.area_mais_proxima(0, 0, null, null, null);
        Assertions.assertEquals(null, areaInfo);
    }

    @Test
    void acumulado_valores_area_retangular() {
        System.out.println("EX5.acumulado_valores_area_retangular");
        double res = instance.acumulado_valores_area_retangular(515, 5419, 1965, 45.943161, 24.96676, -7.109535, 177.64933);
        Assertions.assertEquals(0.0, res);
    }



}