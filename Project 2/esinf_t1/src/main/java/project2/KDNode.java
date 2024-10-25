package project2;

import java.awt.geom.Point2D;


public class KDNode<T> {

    protected Point2D.Double coords;

    protected KDNode<T> left;

    protected KDNode<T> right;

    public KDNode<T> getLeft() {
        return left;
    }

    public KDNode<T> getRight() {
        return right;
    }

    public void setRight(KDNode<T> right) {
        this.right = right;
    }

    public void setLeft(KDNode<T> left) {
        this.left = left;
    }

    protected T object;

    public KDNode(T object, double x, double y) {
        this.coords = new Point2D.Double(x,y);
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public void setCoords(Point2D.Double coords) {
        this.coords = coords;
    }

    public Point2D.Double getCoords() {
        return coords;
    }

    public Double getX() {
        return coords.x;
    }

    public Double getY() {
        return coords.y;
    }


    @Override
    public String toString() {
        return "Node{" +
                "coords=" + coords +
                ", left=" + left +
                ", right=" + right +
                '}';
    }


}


