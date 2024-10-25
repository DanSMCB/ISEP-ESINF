package project1;

import project1.Country;
import project1.Item;

import java.time.LocalDate;
import java.util.*;

public class Platform {
    Map<Country, Set<Product>> sup;

    public Platform() {
        sup = new HashMap<>();
    }

    public void getCountry(List<String> l) throws Exception {
        Set<Product> sp = null;
        Country c = null;
        Year y = null;
        Value v = null;
        Item i = null;
        Product p = null;
        int x = 0;

        for (String s : l) {
            if (x == 0) {
                x++;
                continue;
            }
            if (s.charAt(0) == '\"') {
                String[] lin = s.split("\",\"");
                c = new Country(Integer.parseInt(lin[2]), lin[3]);
                y = new Year(Integer.parseInt(lin[8]), Integer.parseInt(lin[9]));
                v = new Value(lin[10], Integer.parseInt(lin[11]));
                i = new Item(Integer.parseInt(lin[6]), lin[7]);
                p = new Product(y, v, i);
                if (!sup.containsKey(c)) {
                    sp = new HashSet<>();
                    sp.add(p);
                    sup.put(c, sp);
                } else {
                    Set<Product> set = sup.get(c);
                    set.add(p);
                    sup.put(c, set);
                }
            } else if (s.charAt(0) != '\"') {
                String[] lin = s.split(",");
                c = new Country(Integer.parseInt(lin[2]), lin[3]);
                y = new Year(Integer.parseInt(lin[8]), Integer.parseInt(lin[9]));
                v = new Value(lin[10], Integer.parseInt(lin[11]));
                i = new Item(Integer.parseInt(lin[6]), lin[7]);
                p = new Product(y, v, i);
                if (!sup.containsKey(c)) {
                    sp = new HashSet<>();
                    sp.add(p);
                    sup.put(c, sp);
                } else {
                    Set<Product> set = sup.get(c);
                    set.add(p);
                    sup.put(c, set);
                }
            } else {
                throw new Exception("Line not recognized");
            }
        }
    }

    public boolean firstCharacterLetter(String l) {
        if (l.charAt(0) == '"') {
            return false;
        } else if (l.charAt(0) != '"'){
            return true;
        }else{
            return false;
        }
    }


    // Exercício 2
    public List<Country> country_list(String fruit, int value) {
        LocalDate current_date = LocalDate.now();
        List<Product> productList = new ArrayList<>();
        List<Country> countryList = new ArrayList<>();
        for (Country c : sup.keySet()) {
            Set<Product> sp = sup.get(c);
            for (Product p : sp) {
                if (p.getItem().getItem().equals(fruit) && (current_date.getYear() - p.getYear().getYear()) >= 1 && p.getValue().getValue() >= value) {
                    productList.add(p);
                }
            }
        }
        productList.sort(new Product_comparator());
        for (Product p : productList) {
            Country c1 = getKey(sup, p);
            if(!countryList.contains(c1)){
                countryList.add(c1);
            }
        }

        return countryList;
    }

    public Country getKey(Map<Country, Set<Product>> sup, Product p) {
        for (Map.Entry<Country, Set<Product>> entry : sup.entrySet()) {
            if(entry.getValue().contains(p)){
                return entry.getKey();
            }
        }
        return null;
    }

    //Exercício 3
    public int numMinPaisesComQuantidadeProdSuperiorQ(int V) {
        Set<Country> res = new HashSet<>();

        int somaQuantidades = 0;
        int limit = 0;
        int lowestValue = 99999999;
        Country pais = null;

        while (somaQuantidades < V) {
            for (Country c : sup.keySet()) {
                for (Product p : sup.get(c)) {
                    if (p.getValue().getValue() > limit && p.getValue().getValue() < lowestValue) {
                        lowestValue = p.getValue().getValue();
                        pais = c;
                    }
                }
            }
            somaQuantidades = somaQuantidades + lowestValue;
            res.add(pais);
            limit = lowestValue;
            lowestValue = 99999999;
        }

        return res.size() - 1;
    }

    //Exercício 4
    public Map<Country, Integer> numMaxAnosConsecutivosCrescimentoFrutoF(String i) {

        Map<Country, Integer> res = new HashMap<>();
        for (Country c : sup.keySet()) {
            Map<Year, Value> temporaryList = new TreeMap<>();
            for (Product p : sup.get(c)) {
                if (i.equals(p.getItem().getItem())) {
                    Year year = p.getYear();
                    Value value = p.getValue();
                    temporaryList.put(year, value);
                }
            }
            int n = nMaxAnosConsecutivosCrescimentoFrutoFnoPaisX(temporaryList);
            res.put(c, n);
        }
        return res;
    }

    public int nMaxAnosConsecutivosCrescimentoFrutoFnoPaisX(Map<Year, Value> list) {
        int totalAnos = 0;
        int maxTotalAnos = 0;
        Iterator<Map.Entry<Year, Value>> i = list.entrySet().iterator();
        for (Year y : list.keySet()) {
            int valueX = list.get(y).getValue();
            int valueX1 = i.next().getValue().getValue();
            if (valueX1 > valueX) {
                totalAnos++;
            } else {
                maxTotalAnos = totalAnos;
                totalAnos = 0;
            }
        }
        return maxTotalAnos;
    }

    //Exercício 5
    public Map<Item, Object[]> diferenceMaximaDasQuantidades(String country) {
        Map<Item, Object[]> values = new HashMap<>();
        TreeMap<Value, Integer> tree_map = new TreeMap<>(new SortByValue());
        List<Integer> anos = new ArrayList<>();
        ArrayList<Integer> difference = new ArrayList<>();

        for (Country c : sup.keySet()) {
            if (c.getAreaCountry().equalsIgnoreCase(country)) {
                Set<Product> sp = sup.get(c);
                Iterator<Product> it = sp.iterator();

                for (Product p : sup.get(c)) {
                    Item fruit = p.getItem();
                    while (it.hasNext()) {
                        Product aux = it.next();
                        if (fruit == aux.getItem()) {
                            tree_map.put(aux.getValue(), aux.getYear().getYear());
                        }
                    }
                    //diferença absoluta de quantidades do mesmo fruto
                    int differenceMax = tree_map.lastKey().getValue() - tree_map.firstKey().getValue();

                    // Get entry set of the TreeMap using entrySet method
                    Set<Map.Entry<Value, Integer>> entrySet = tree_map.entrySet();

                    // Converte entrySet to ArrayList
                    List<Map.Entry<Value, Integer>> entryList = new ArrayList<>(entrySet);

                    int maxIndex = entryList.size() - 1;
                    int yearMin = entryList.get(0).getValue(); // Get value (<- year) using index
                    int yearMax = entryList.get(maxIndex).getValue(); // Get value (<- year) using index
                    anos.add(yearMin, yearMax);
                    difference.add(differenceMax);
                    Object[] fruitInformation = {anos, difference}; //cria um array com os anos e a diferença da quantidade máxima

                    values.put(fruit, fruitInformation);
                }
            }
        }
        return values;
    }
}


