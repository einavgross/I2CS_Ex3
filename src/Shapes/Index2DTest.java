package Shapes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Index2DTest {
    /**
     * tests getX() function - return the x value of Index2D
     */
    @Test
    public void getXTest() {
        Index2D index2D = new Index2D(-54,43);
        assertEquals(-54, index2D.getX());
    }
    /**
     * tests getY() function - return the h value of Index2D
     */
    @Test
    public void getYTest() {
        Index2D index2D = new Index2D(33,-4);
        assertEquals(-4, index2D.getY());
    }

    /**
     * test the copy Constructor - if p1 and a copy of it are equals
     */
    @Test
    public void testCopyConstructor() {
        Index2D p1 = new Index2D(30, 44);
        Index2D p2 = new Index2D(p1);
        assertEquals(p1, p2);
    }

    /**
     * tests index's distance from (0,0)
     */
    @Test
    public void distanceFromZero1() {
        Index2D ind = new Index2D(0,0);
        Index2D ind2 = new Index2D(0,5);
        double distance = ind.distance2D(ind2);
        assertEquals(5, distance);
    }
    /**
     * tests index's distance from (0,0)
     */
    @Test
    public void distanceFromZero2() {
        Index2D ind = new Index2D(0,0);
        Index2D ind2 = new Index2D(7,0);
        double distance2 = ind.distance2D(ind2);
        assertEquals(7, distance2);
    }
    /**
     * tests if the distance from (0,0) to (0,0) is 0
     */
    @Test
    public void distanceFromZero3() {
        Index2D ind = new Index2D(0,0);
        Index2D ind2 = new Index2D(0,0);
        double distance2 = ind.distance2D(ind2);
        assertEquals(0, distance2);
    }
    /**
     * tests index's distance from itself is 0
     */
    @Test
    public void distanceFromItself() {
        Index2D ind = new Index2D(6,4);
        Index2D ind2 = new Index2D(6,4);
        double distance2 = ind.distance2D(ind2);
        assertEquals(0, distance2);
    }

    /**
     * tests distance of 2 random indexes
     */
    @Test
    public void distanceFromRandom() {
        Index2D ind = new Index2D(3,7);
        Index2D ind2 = new Index2D(9,15);
        double distance = ind.distance2D(ind2);
        assertEquals(10, distance);
    }
    /**
     * tests distance of 2 random indexes
     */
    @Test
    public void distanceFromRandom2() {
        Index2D ind = new Index2D(2,1);
        Index2D ind2 = new Index2D(7,13);
        double distance = ind.distance2D(ind2);
        assertEquals(13, distance);
    }
    /**
     * tests distance of 2 random indexes
     */
    @Test
    public void distanceFromRandom3() {
        Index2D ind = new Index2D(-3,5);
        Index2D ind2 = new Index2D(4,-2);
        double distance = ind.distance2D(ind2);
        assertEquals(9.899,distance,0.1);
    }
    /**
     * tests distance of 2 random indexes
     */
    @Test
    public void distanceFromRandom4() {
        Index2D ind = new Index2D(-15,22);
        Index2D ind2 = new Index2D(30,-10);
        double distance = ind.distance2D(ind2);
        assertEquals(55.22,distance,0.1);
    }
    /**
     * tests toString function
     */
    @Test
    public void testToString() {
        Index2D ind = new Index2D(-9,4);
        String str = ind.toString();
        assertEquals("-9,4", str);
    }
    /**
     * tests equals function - if 2 identical indexes return true
     */
    @Test
    public void testEquals() {
        Index2D ind = new Index2D(-7,2);
        Index2D ind2 = new Index2D(-7,2);
        assertTrue(ind.equals(ind2));
    }
    /**
     * tests equals function - if 2 different indexes return false
     */
    @Test
    public void testEquals2() {
        Index2D ind = new Index2D(0,0);
        Index2D ind2 = new Index2D(5,5);
        assertFalse(ind.equals(ind2));
    }
}
