package project2;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;


public class kdTreeTest {

    KDNode<Object> node1;
    KDNode<Object> node2;
    KDNode<Object> node3;
    KDNode<Object> node4;
    KDNode<Object> node5;

    Object obj1;
    Object obj2;
    Object obj3;
    Object obj4;
    Object obj5;
    Object obj6;


    @Before
    public void initGuards() {
        obj1 = new Object();
        obj2 = new Object();
        obj3 = new Object();
        obj4 = new Object();
        obj5 = new Object();
        obj6 = new Object();

        node1 = new KDNode<>(obj1, 0, 0);
        node2 = new KDNode<>(obj2, 10, 10);
        node3 = new KDNode<>(obj3, 0, 10);
        node4 = new KDNode<>(obj4, 10, 0);
        node5 = new KDNode<>(obj5, 20, 20);

    }


    @Test
    public void testInsert() {
        kdTree<Object> tree = new kdTree<>();
        tree.insert(obj1, 0, 0);
        tree.insert(obj2, 10, 10);
        tree.insert(obj3, 0, 10);
        tree.insert(obj4, 10, 0);
        tree.insert(obj5, 20, 20);

        Object node = tree.findNearestNeighbour(0, 0);
        assertEquals(obj1, node);

        node = tree.findNearestNeighbour(8, 8);
        assertEquals(obj2, node);

        node = tree.findNearestNeighbour(0, 8);
        assertEquals(obj3, node);

        node = tree.findNearestNeighbour(9, 0);
        assertEquals(obj4, node);

        node = tree.findNearestNeighbour(20, 20);
        assertEquals(obj5, node);

    }


    @Test
    public void testRangeSearch() {
        kdTree<Object> tree = new kdTree<>();

        tree.insert(obj1, -7.109535,177.6493);
        tree.insert(obj3, 26.820553,30.802498);
        tree.insert(obj5, 7.369722,12.354722);
        tree.insert(obj2, 41.20438,74.766098);
        tree.insert(obj3, -9.189967,-75.015152);

        List<Object> result = tree.rangeSearch(-7.109535,177.6493, 26.820553,30.802498);
        assertEquals(0, result.size());

        result = tree.rangeSearch(26.820553,30.802498, 7.369722,12.354722);
        assertEquals(0, result.size());
    }


    @Test
    public void testFindNearestNeighbours() {
        kdTree<Object> tree = new kdTree<>();
        tree.insert(obj3, 5, 4);
        tree.insert(obj2, 2, 6);
        tree.insert(obj1, 13, 3);
        tree.insert(obj5, 8, 7);
        tree.insert(obj4, 3, 1);
        tree.insert(obj6, 10, 2);

        Object result = tree.findNearestNeighbour(9, 4);
        assertEquals(obj6, result);

    }
}
