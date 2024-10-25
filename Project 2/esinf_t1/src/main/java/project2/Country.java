package project2;

public class Country implements Comparable<Country>{

        private int areaCode;
        private String area;
        private int codeM49;
        private BST<Item> itemBST;

    public Country(int areaCode, String area, int codeM49, BST<Item> itemBST) {
        this.areaCode = areaCode;
        this.area = area;
        this.codeM49 = codeM49;
        this.itemBST = itemBST;
    }

    public Country(int areaCode, String area, int codeM49) {
        this.areaCode = areaCode;
        this.area = area;
        this.codeM49 = codeM49;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getCodeM49() {
        return codeM49;
    }

    public void setCodeM49(int codeM49) {
        this.codeM49 = codeM49;
    }

    public BST<Item> getItemBST() {
        return itemBST;
    }

    public void setItemBST(BST<Item> itemBST) {
        this.itemBST = itemBST;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        Country otherInvoice = (Country) obj;
        return otherInvoice.getAreaCode() == this.getAreaCode();

    }



    @Override
    public String toString() {
        return "Country{" +
                "areaCode='" + areaCode + '\'' +
                ", area='" + area + '\'' +
                '}';
    }

    public void addToItemBST(Item i) {
        itemBST.insert(i);
    }

    @Override
    public int compareTo(Country o) {return this.getArea().compareTo(o.area);}
}

