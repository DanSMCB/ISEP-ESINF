package project2;

public class Element implements Comparable <Element>{
    private int elementCode;
    private String element;
    private BST<Year> yearBST = new BST<Year>();

    public Element(int elementCode, String element, BST<Year> yearBST) {
        this.elementCode = elementCode;
        this.element = element;
        this.yearBST = yearBST;
    }

    public Element(int elementCode, String element) {
        this.elementCode = elementCode;
        this.element = element;
    }

    public int getElementCode() {
        return elementCode;
    }

    public void setElementCode(int elementCode) {
        this.elementCode = elementCode;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public BST<Year> getYearBST() {
        return yearBST;
    }

    public void setYearBST(BST<Year> yearBST) {
        this.yearBST = yearBST;
    }

    @Override
    public String toString() {
        return "Element{" +
                "elementCode='" + elementCode + '\'' +
                ", element='" + element + '\'' +
                ", yearBST=" + yearBST +
                '}';
    }

    @Override
    public int compareTo(Element o) {
        return this.elementCode - o.elementCode;
    }

    public void addToYearBST(Year y) {
        yearBST.insert(y);
    }
}
