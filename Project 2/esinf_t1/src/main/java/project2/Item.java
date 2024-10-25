package project2;

import java.util.Objects;

public class Item implements Comparable<Item> {

    private int itemCode;
    private String itemCPC;
    private String item;
    private BST<Element> elementBST = new BST<Element>();

    public Item(int itemCode, String itemCPC, String item, BST<Element> elementBST) {
        this.itemCode = itemCode;
        this.itemCPC = itemCPC;
        this.item = item;
        this.elementBST = elementBST;
    }

    public Item(int itemCode, String itemCPC, String item) {
        this.itemCode = itemCode;
        this.itemCPC = itemCPC;
        this.item = item;
    }

    public int getItemCode() {
        return itemCode;
    }

    public String getItem() {
        return item;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemCPC() {
        return itemCPC;
    }

    public void setItemCPC(String itemCPC) {
        this.itemCPC = itemCPC;
    }

    public BST<Element> getElementBST() {
        return elementBST;
    }

    public void setElementBST(BST<Element> elementBST) {
        this.elementBST = elementBST;
    }

    @Override
    public String toString() {
        return "\nItem{" +
                "itemCode=" + itemCode +
                ", item='" + item + '\'' +
                '}';
    }

    @Override
    public int compareTo(Item o) {return this.getItemCode() - o.itemCode;}

    public void addToElementBST(Element e) {
        elementBST.insert(e);
    }

}
