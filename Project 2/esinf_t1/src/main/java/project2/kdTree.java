package project2;

import java.awt.geom.Point2D;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class kdTree<T> {
    private final Comparator<KDNode<T>> cmpX = new Comparator<KDNode<T>>(){
        @Override
        public int compare(KDNode<T> p1, KDNode<T> p2) {
            return Double.compare(p1.getX(), p2.getX());
        }
    };
    private final Comparator<KDNode<T>> cmpY = new Comparator<KDNode<T>>(){
        @Override
        public int compare(KDNode<T> p1, KDNode<T> p2) {
            return Double.compare(p1.getY(), p2.getY());
        }
    };


    private KDNode<T> root;

    public kdTree() {

    }

    public KDNode<T> getRoot() {
        return root;
    }

    public T findNearestNeighbour(double x, double y) {
        KDNode<T> auxNode = new KDNode<T> ( null, 0.0, 0.0);
        auxNode.setCoords(new Point2D.Double(-1000.0, -1000.0));
        return findNearestNeighbour(root, x, y, auxNode, true);
    }

    private T findNearestNeighbour(KDNode<T> node, double x, double y, KDNode<T> closestNode, boolean divX) {
        if (node == null)
            return null;
        double d = Point2D.distanceSq(node.coords.x, node.coords.y, x, y);
        double closestDist = Point2D.distanceSq(closestNode.coords.x, closestNode.coords.y, x, y);
        if (closestDist > d) {
            closestNode.setObject(node.getObject());
        }
        double delta = divX ? x - node.coords.x : y - node.coords.y;
        double delta2 = delta * delta;
        KDNode<T> node1 = delta < 0 ? node.getLeft() : node.getRight();
        KDNode<T> node2 = delta < 0 ? node.getRight() : node.getLeft();
        findNearestNeighbour(node1, x, y, closestNode, !divX);

        if (delta2 < closestDist) {
            findNearestNeighbour(node2, x, y, closestNode, !divX);
        }

        return closestNode.getObject();
    }


    public List<T> rangeSearch(double latitude_i, double longitude_i, double latitude_f, double longitude_f) {
        return new Object() {
            final List<T> result = new LinkedList<>();
            final double area_retangular = Math.abs(latitude_f-latitude_i) * Math.abs(longitude_f-longitude_i);

            List<T> rangeSearch(KDNode<T> node, boolean divX) {
                if (node == null)
                    return result;

                double d = Point2D.distanceSq(latitude_i, longitude_i, latitude_f, longitude_f);

                if (area_retangular >= d)
                    result.add(node.getObject());

                double delta = divX ? Math.abs(latitude_f-latitude_i) : Math.abs(longitude_f-longitude_i);
                double delta2 = delta * delta;
                KDNode<T> node1 = delta < 0 ? node.left : node.right;
                KDNode<T> node2 = delta < 0 ? node.right : node.left;
                rangeSearch(node1, !divX);
                if (delta2 < area_retangular) {
                    rangeSearch(node2, !divX);
                }
                return result;
            }
        }.rangeSearch(root, true);
    }

    public void insert(T object, double x, double y) {
        KDNode<T> node = new KDNode<>(object, x, y);
        if (root == null)
            root = node;
        else
            insert(node, root, true);
    }

    private void insert(KDNode<T> node, KDNode<T> currentNode, boolean divX) {
        if (node.coords.equals(currentNode.coords))
            return;
        int cmpResult = (divX ? cmpX : cmpY).compare(node, currentNode);
        if (cmpResult == -1)
            if(currentNode.left == null)
                currentNode.left = node;
            else
                insert(node, currentNode.left, !divX);
        else
        if(currentNode.right == null)
            currentNode.right = node;
        else
            insert(node, currentNode.right, !divX);
    }

}
