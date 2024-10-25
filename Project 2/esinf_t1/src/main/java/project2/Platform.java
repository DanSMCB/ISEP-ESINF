package project2;

import project1.Product;
import java.util.*;
import java.util.stream.Collectors;

public class Platform {
    Map<Country, Set<Product>> sup;
    BST<Country> countryBST = new BST<>();
    kdTree<AreaCoords> treeCoordinates = new kdTree<>();

    List<Flag> flagsWithDesc;

    public Platform() {
        sup = new HashMap<>();
    }

    public BST<Country> getCountryBST(){return countryBST;}

    public void fillCountryBST (List<Map<String,String>> shuffleMapList, List<String> flags) {
        for (Map<String, String> map : shuffleMapList) {
            Flag flag = null;

            BST<Item> itemBST = new BST<>();
            BST<Element> elementBST = new BST<>();
            BST<Year> yearBST = new BST<>();

            for (String f : flags) {
                String[] flagLin = f.split(",");
                if (flagLin[0].equals(map.get("Flag"))) {
                    flag = new Flag(flagLin[0], flagLin[1]);
                }
            }
            Value v;
            if(map.get("Value").equals("")){
                v = new Value(map.get("Unit"), 0, flag);
            }else {
                v = new Value(map.get("Unit"), Float.parseFloat(map.get("Value")), flag);
            }
            Year y = new Year(Integer.parseInt(map.get("Year Code")), Integer.parseInt(map.get("Year")), v);
            //yearCode,Year,Unit,Value,Flag
            Element e = new Element(Integer.parseInt(map.get("Element Code")), map.get("Element"), yearBST);
            //System.out.println(e);//elementCode, Element , yearBST
            Item i = new Item(Integer.parseInt(map.get("Item Code")), map.get("Item Code (CPC)"), map.get("Item"), elementBST); //itemCode, itemCodeCPC, item, ElementBST

            itemBST.insert(i);
            elementBST.insert(e);
            yearBST.insert(y);

            Country c = new Country(Integer.parseInt(map.get("Area Code")), map.get("Area"), Integer.parseInt(map.get("Area Code (M49)").substring(1)), itemBST); //Area Code, Area Code (M49),Area,  itemBST
            var target = countryBST.find(countryBST.root(), c);

            if (target != null) {
                // encontramos country
                Country country = target.getElement();
                if (country.getItemBST().find(itemBST.root(), i) == null) {
                    country.addToItemBST(i);
                }
                //if(country.getItemBST().find(itemBST.root, i) == null){
                if (country.getItemBST().find(itemBST.root, i).getElement().getElementBST().find(elementBST.root, e) == null) {
                    country.getItemBST().find(itemBST.root, i).getElement().addToElementBST(e);
                }
                if (country.getItemBST().find(itemBST.root, i).getElement().getElementBST().find(elementBST.root, e).getElement().getYearBST().find(yearBST.root, y) == null) {
                    country.getItemBST().find(itemBST.root, i).getElement().getElementBST().find(elementBST.root, e).getElement().addToYearBST(y);
                }
            } else {
                countryBST.insert(c);
            }
        }
    }


    public void CoordsCountry (List<String> Coordinates) {
        int x = 0;
        for (String s : Coordinates) {
            if (x == 0) {
                x++;
                continue;
            }

            String[] lin1 = s.split(",");
            double longitude = Double.parseDouble(lin1[2]);
            double latitude = Double.parseDouble(lin1[1]);
            String area = lin1[3];
            String countryCoords = lin1[0];

            AreaCoords obg = new AreaCoords(countryCoords, area, latitude, longitude);
            treeCoordinates.insert(obg, latitude, longitude);

        }
    }


    public Value searchByAreaCode(int areaCode, int itemCode, int elementCode, int year){
        return searchByAreaCode(areaCode,itemCode,elementCode,year, this.countryBST.root());
    }

    public Value searchByAreaCode(int areaCode, int itemCode, int elementCode, int year, BST.Node<Country> node){
        if(node==null){
            return null;
        }
        if(node.getElement().getAreaCode()==areaCode) return searchByItemCode(itemCode,elementCode,year, node.getElement().getItemBST());
        else if(areaCode>node.getElement().getAreaCode()) return searchByAreaCode(areaCode,itemCode,elementCode,year,node.getRight());
        return searchByAreaCode(areaCode,itemCode,elementCode,year,node.getLeft());
    }

    public Value searchByItemCode(int itemCode, int elementCode, int year, BST<Item> itemBST){
        return searchByItemCode(itemCode,elementCode,year, itemBST.root());
    }

    public Value searchByItemCode(int itemCode, int elementCode, int year, BST.Node<Item> node){
        if(node == null){
            return null;
        }
        if(node.getElement().getItemCode()==itemCode) return searchByElementCode(elementCode,year, node.getElement().getElementBST());
        else if(itemCode>node.getElement().getItemCode()) return searchByItemCode(itemCode,elementCode,year,node.getRight());
        return searchByItemCode(itemCode,elementCode,year,node.getLeft());
    }

    public Value searchByElementCode(int elementCode, int year, BST<Element> elementBST){
        return searchByElementCode(elementCode,year, elementBST.root());
    }

    public Value searchByElementCode(int elementCode, int year, BST.Node<Element> node){
        if(node == null){
            return null;
        }
        if(node.getElement().getElementCode()==elementCode) return searchByYear(year, node.getElement().getYearBST());
        else if(elementCode>node.getElement().getElementCode()) return searchByElementCode(elementCode,year,node.getRight());
        return searchByElementCode(elementCode,year,node.getLeft());
    }
    public Value searchByYear(int year, BST<Year> YearBST){
        return searchByYear(year, YearBST.root());
    }
    public Value searchByYear (int year, BST.Node<Year> node){
        if(node == null){
            return null;
        }
        if(node.getElement().getYear()==year) return node.getElement().getValue();
        else if(year>node.getElement().getYear()) return searchByYear(year,node.getRight());
        return searchByYear(year,node.getLeft());
    }

    public Value searchByArea(String area, String item, String element, int year){
        return searchByArea(area,item,element,year, this.countryBST.root());
    }

    public Value searchByArea(String area, String item, String element, int year, BST.Node<Country> node){
        if(node == null){
            return null;
        }
        if(node.getElement().getArea().equals(area)) return searchByItem(item,element,year, node.getElement().getItemBST());
        else if(area.compareTo(node.getElement().getArea())>0) return searchByArea(area,item,element,year,node.getRight());
        return searchByArea(area,item,element,year,node.getLeft());
    }

    public Value searchByItem(String item, String element, int year, BST<Item> itemBST){
        return searchByItem(item,element,year, itemBST.root());
    }

    public Value searchByItem(String item, String element, int year, BST.Node<Item> node){
        if(node == null){
            return null;
        }
        if(node.getElement().getItem().equals(item)) return searchByElement(element,year, node.getElement().getElementBST());
        else if(item.compareTo(node.getElement().getItem())>0) return searchByItem(item,element,year,node.getRight());
        return searchByItem(item,element,year,node.getLeft());
    }

    public Value searchByElement(String element, int year, BST<Element> elementBST){
        return searchByElement(element,year, elementBST.root());
    }

    public Value searchByElement(String element, int year, BST.Node<Element> node){
        if(node == null){
            return null;
        }
        if(node.getElement().getElement().equals(element)) return searchByYear(year, node.getElement().getYearBST());
        else if(element.compareTo(node.getElement().getElement())>0) return searchByElement(element,year,node.getRight());
        return searchByElement(element,year,node.getLeft());
    }

    public Map<String, Float> mean_value(Country country, int year_min, int year_max){
        float mean, sum;
        int n;
        Map<String, Float> res = new HashMap<>();
        if(countryBST.find(countryBST.root,country)!=null){
            country = countryBST.find(countryBST.root,country).getElement();
            for (Item item : country.getItemBST().inOrder()) {
                for (Element element : item.getElementBST().inOrder()) {
                    n=0;
                    sum=0;
                    for (int i=year_min;i<=year_max;i++) {
                        if(element.getYearBST().find(element.getYearBST().root, new Year(i,i,null))!=null){
                            sum+=(element.getYearBST().find(element.getYearBST().root, new Year(i,i,null))).getElement().getValue().getValue();
                            n++;
                        }
                    }
                    mean=sum/n;
                    String s = year_min + ".." + year_max + ", " + item.getItem() + ", " + element.getElement() + ", " + mean;
                    res.put(s, mean);
                }
            }
            return res.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2)->e1, LinkedHashMap::new));
        }else return null;
    }

    public Map<Country, Float> topN_areas(int n, Item item, Element element){
        Map<Country, Float> areas= new HashMap<>();
        for(Country c : countryBST.inOrder()){
            if(c.getItemBST().find(c.getItemBST().root,item)!=null){
                Item item1 = c.getItemBST().find(c.getItemBST().root,item).getElement();
                if(item1.getElementBST().find(item1.getElementBST().root,element)!=null){
                    Element element1 = item1.getElementBST().find(item1.getElementBST().root,element).getElement();
                    areas.put(c,element1.getYearBST().biggestElement().getValue().getValue());
                }
            }
        }
        if(areas.isEmpty()){
            return null;
        }else return areas.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(n).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2)->e1, LinkedHashMap::new));
    }

    //ex5
    public double acumulado_valores_area_retangular(int itemCode, int elementCode, int yearCode, double latitude_i, double longitude_i, double latitude_f, double longitude_f){
        List<AreaCoords> listaCoordenadas = treeCoordinates.rangeSearch(latitude_i, longitude_i, latitude_f, longitude_f);
        double sum=0;
        for(Country c : countryBST.inOrder()){
            for (int i=0; i< listaCoordenadas.size()-1; i++){
                if (c.getArea().equals(listaCoordenadas.get(i).getAreaCoords())){
                    for (Item item : c.getItemBST().inOrder()) {
                        if (item.getItemCode() == itemCode) {
                            for (Element element : item.getElementBST().inOrder()) {
                                if (element.getElementCode() == elementCode) {
                                    for (Year year : element.getYearBST().inOrder()) {
                                        if (year.getYearCode() == yearCode) {
                                            sum += year.getValue().getValue();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return sum;
    }

    //ex4
    public Object[] area_mais_proxima (double latitude, double longitude, Item item, Element element, Year year){
        Object[] areaInfo = new Object[5];
        AreaCoords vizinho = treeCoordinates.findNearestNeighbour(latitude, longitude);
        if (vizinho!=null) {
            boolean checkVizinho = false;
            for (Country c : countryBST.inOrder()) {
                if (c.getArea().equals(vizinho.getAreaCoords())) {
                    if (c.getItemBST().find(c.getItemBST().root, item) != null) {
                        Item item1 = c.getItemBST().find(c.getItemBST().root, item).getElement();
                        if (item1.getElementBST().find(item1.getElementBST().root, element) != null) {
                            Element element1 = item1.getElementBST().find(item1.getElementBST().root, element).getElement();
                            if (element1.getYearBST().find(element1.getYearBST().root, year) != null) {
                                checkVizinho = true;
                            }
                        }
                    }
                }
            }
            if (checkVizinho) {
                areaInfo = new Object[]{vizinho.getLatitude(), vizinho.getLongitude(), item, element, year};
            } else {
                area_mais_proxima(vizinho.getLatitude(), vizinho.getLongitude(), item, element, year);
            }
        }else{
            areaInfo = null;
        }
        return areaInfo;
    }

    public void toString_area_mais_proxima (Object[] areaInfo) {
        if (areaInfo == null){
            System.out.println("N�o h� informa��o");
        } else {
            System.out.printf("{latitude: %.4f, longitude: %.4f, Item: %s, Element: %s, Year: %d}", (Double) areaInfo[0], (Double) areaInfo[1], areaInfo[2], areaInfo[3], (int) areaInfo[4]);
        }

    }



    //----------------Para os unit tests--------------------------------------------------------
    public void insertCountry(Country country){
        countryBST.insert(country);
    }
    //----------------------------------------------------------------------------------------------
}


