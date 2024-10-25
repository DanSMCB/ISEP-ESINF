package project2.Main;

import project2.*;

import java.util.List;
import java.util.Map;

public class Main {

    static String flagPath = "project2_files\\Production_Crops_Livestock_E_Flags.csv";
    static String coordinatesPath = "project2_files\\Production_Crops_Livestock_E_AreaCoordinates_shuffled_v2.csv";
    static String shufflePath = "project2_files\\Production_Crops_Livestock_World_shuffle_small.csv";
    static int n=5;
    static Country country = new Country(59,"Egypt",818,null);
    static Item item = new Item(1141,"'21114","Meat of rabbits and hares, fresh or chilled",null);
    static Element element = new Element(5510,"Production",null);

    public static void main(String[] args) {
        Platform platform = new Platform();
        CSVReader csvReader = new CSVReader(shufflePath);
        List<Map<String, String>> listShuffle = csvReader.read();

        List<String> flagsList = csvReader.fileReader(flagPath);

        platform.fillCountryBST(listShuffle, flagsList);

        Value v1;
        v1=platform.searchByAreaCode(41, 720,5312, 1963);//line 11
        System.out.println("\n" + v1.toString());
        v1=platform.searchByArea("Egypt","Broad beans and horse beans, dry","Yield", 2006 );//line 10
        System.out.println(v1.toString());
        v1=platform.searchByArea("Saint Lucia","Sugar cane","Yield",1965);
        System.out.println(v1.toString());
        v1=platform.searchByAreaCode(189,156,5419,1965);
        System.out.println(v1.toString());

        System.out.println("\nSecond functionality:\n");
        Map<String, Float> res_2 = platform.mean_value(country, 1965,2019);
        if(res_2==null){
            System.out.println("There is no information regarding the typed country.");
        }else{
            for(String s : res_2.keySet()){
                System.out.println(s);
            }
        }


        System.out.println("\nThird functionality:\n");
        Map<Country, Float> res_3 = platform.topN_areas(n, item,element);
        for(Country c : res_3.keySet()){
            System.out.println(c.getArea() + " with value: " + res_3.get(c));
        }

        //constru��o kd-Tree
        List<String> coordinatesList = csvReader.fileReader(coordinatesPath);
        platform.CoordsCountry(coordinatesList);

        System.out.println("\nFifth functionality:\n");
        double acumulado = platform.acumulado_valores_area_retangular(222, 5312, 1962, -7.109535,177.64933, 26.820553,30.802498);
        System.out.printf("Accumulated production values for a rectangular geographic area: %.2f\n", acumulado);


        System.out.println("\nFourth functionality:\n");
        Item sugarCane = new Item(146, "01802", "Sugar cane");
        Element production = new Element(5510, "Production");
        Year ano = new Year(2011, 2011);
        Object[] areaInfo = platform.area_mais_proxima(45.1, 15.2, sugarCane, production, ano);
        platform.toString_area_mais_proxima(areaInfo);
    }
}
