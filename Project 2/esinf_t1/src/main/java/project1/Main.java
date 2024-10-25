package project1;

import project1.Country;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    static String path = "FAOSTAT_data_en_9-7-2022_SMALL.csv";
    static String fruit = "Apples";
    static int value = 1000;
    static int soma = 10000;
    static String country = "Portugal";


    public static void main(String[] args) throws Exception {
        Platform platform = new Platform();

        System.out.println("Loading file...");
        platform.getCountry(Files.lines(Paths.get(path)).collect(Collectors.toList()));
        System.out.println("File loaded.\n");

        System.out.println("Exercise 2: ");
        List<Country> countryList = platform.country_list(fruit, value);
        for(Country c : countryList){
            System.out.println(c.getAreaCountry());
        }

        System.out.println("\nExercise 3: ");
        System.out.println(platform.numMinPaisesComQuantidadeProdSuperiorQ(soma));

        System.out.println("\nExercise 4: ");
        Map<Country, Integer> res=platform.numMaxAnosConsecutivosCrescimentoFrutoF(fruit);
        for (Country c : res.keySet()){
            System.out.println(c.getAreaCountry());
            System.out.println(res.get(c));
        }



        System.out.println("\nExercise 5: ");
        System.out.println(platform.diferenceMaximaDasQuantidades(country));


    }
}
