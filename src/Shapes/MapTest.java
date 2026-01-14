package Shapes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * this is a tests class for the 2D map class
 */
public class MapTest {
    /**
     * \
     * tests constructor from 3 values
     */
    @Test
    public void testInitFrom3V() {
        Map2D m = new Map(3, 7, 1);
        assertEquals(3, m.getWidth());
        assertEquals(7, m.getHeight());
        assertEquals(1, m.getPixel(2, 6));
        assertEquals(1, m.getPixel(1, 4));
    }

    /**
     * tests constructor from a 2D array
     */
    @Test
    public void testInitFromArray() {
        int[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Map2D m2 = new Map(m);
        assertEquals(m.length, m2.getWidth());
        assertEquals(m[0].length, m2.getHeight());
        assertEquals(5, m2.getPixel(1, 1));
        assertEquals(3, m2.getPixel(0, 2));
        assertEquals(m[1][0], m2.getPixel(1, 0));
    }

    /**
     * tests getMap function - if returns the right 2D array
     */
    @Test
    public void getMapTest() {
        int[][] m1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        Map2D m2 = new Map(m1);
        assertArrayEquals(m1, m2.getMap());
    }

    /**
     * tests getMap function - when setting the Map from values
     */
    @Test
    public void getMapTest4() {
        Map2D m1 = new Map(2, 2, 2);
        int[][] m2 = {{2, 2}, {2, 2}};
        assertArrayEquals(m2, m1.getMap());
    }

    /**
     * tests getMap function for a single object with zeros
     */
    @Test
    public void getMapTest2() {
        int[][] m1 = {{0, 0, 0}};
        Map2D m2 = new Map(m1);
        assertArrayEquals(m1, m2.getMap());
    }

    /**
     * test getMap for a big array
     */
    @Test
    public void getMapTestBig() {
        int[][] m1 = new int[1000][1000];
        Map2D m2 = new Map(m1);
        assertArrayEquals(m1, m2.getMap());
    }

    /**
     * a simple test for getWidth and getHeight functions
     */
    @Test
    public void getMapWidAndHeightTest() {
        Map2D m1 = new Map(1);
        assertEquals(1, m1.getWidth());
        assertEquals(1, m1.getHeight());
    }

    /**
     * tests getWidth and getHeight functions with big numbers
     */
    @Test
    public void getWidthAndHeightTestBig() {
        Map2D m1 = new Map(1000, 1, 0);
        assertEquals(1000, m1.getWidth());
        assertEquals(1, m1.getHeight());
        Map2D m2 = new Map(1, 1000, 0);
        assertEquals(1, m2.getWidth());
        assertEquals(1000, m2.getHeight());
        Map2D m3 = new Map(2000, 2000, 0);
        assertEquals(2000, m3.getWidth());
        assertEquals(2000, m3.getHeight());
    }

    /**
     * tests getWidth and getHeight functions after changing the values
     */
    @Test
    public void testUpdate() {
        Map m = new Map(10, 10, 0);
        assertEquals(10, m.getWidth());
        m.init(5, 8, 0);
        assertEquals(5, m.getWidth());
        assertEquals(8, m.getHeight());
    }

    /**
     * tests if getPixel functions are identical(with x,y and with Pixel2D)
     */
    @Test
    public void testGetPixelMethods() {
        Map m = new Map(10, 10, 5);
        Pixel2D p = new Index2D(5, 5);
        assertEquals(m.getPixel(5, 5), m.getPixel(p));
    }

    /**
     * tests setPixel function - set with x,y,v -  checks if the pixel have changed and the others remained the same
     */
    @Test
    public void testSetGetPixelFromXYV() {
        Map m = new Map(8, 8, 3);
        m.setPixel(2, 3, 100);
        assertEquals(100, m.getPixel(2, 3));
        assertEquals(3, m.getPixel(0, 5));
    }

    /**
     * tests setPixel function - set with x,y,v -  checks if the pixel have changed and the others remained the same
     */
    @Test
    public void testSetGetPixelFromPV() {
        Map2D m = new Map(45);
        Pixel2D p = new Index2D(30, 30);
        Pixel2D p2 = new Index2D(20, 20);
        m.setPixel(p, 8);
        assertEquals(8, m.getPixel(p));
        assertEquals(0, m.getPixel(p2));

    }

    /**
     * tests isInside function, checks if returns true for pixels inside, and false if outside.
     */
    @Test
    public void testIsInside() {
        Map m = new Map(8, 8, 3);
        Pixel2D p = new Index2D(5, 7);
        Pixel2D p2 = new Index2D(2, 0);
        Pixel2D p3 = new Index2D(-5, 0);
        Pixel2D p4 = new Index2D(3, 20);
        assertTrue(m.isInside(p));
        assertTrue(m.isInside(p2));
        assertFalse(m.isInside(p3));
        assertFalse(m.isInside(p4));
    }
    /**
     * test fill function - checks if all the pixels have changed to new color
     */
    @Test
    public void testFillSimple() {
        Map2D map = new Map(7, 5, 0);
        Pixel2D start = new Index2D(0, 1);
        int pixelsColored = map.fill(start, 1);
        assertEquals(35, pixelsColored);
        assertEquals(1, map.getPixel(0, 0));
    }

    /**
     * test the function with obstacles - checks if change the color of the right pixels (and not the obstacles)
     */
    @Test
    public void testFillWithObstacle() {
        Map2D map = new Map(4);
        map.setPixel(1, 1, 2);
        map.setPixel(2, 1, 2);
        map.setPixel(3, 1, 2);
        map.setPixel(1, 2, 2);
        Pixel2D start = new Index2D(0, 0);
        int pixelsColored = map.fill(start, 3);
        assertEquals(12, pixelsColored);
        assertEquals(2, map.getPixel(1, 2));
    }

    /**
     * test if the function coloring the right pixels and not coloring the wrong wronged (empty or obstacles)
     */
    @Test
    public void testFillWithObstacle2() {
        Map2D map = new Map(3);
        map.setPixel(0, 1, 2);
        map.setPixel(1, 1, 2);
        map.setPixel(2, 1, 2);
        Pixel2D start = new Index2D(2, 0);
        int pixelsColored = map.fill(start, 3);
        assertEquals(6, pixelsColored);
        assertEquals(3, map.getPixel(2, 2));
    }

    /**
     * tests fill function with a cycle map - checks if the pixels at the edges have changed except the middle
     */
    @Test
    public void testFillWithObstacleCycle() {
        Map2D map = new Map(3);
        map.setPixel(1, 0, 2);
        map.setPixel(0, 1, 2);
        map.setPixel(2, 1, 2);
        map.setPixel(1, 2, 2);
        Pixel2D start = new Index2D(0, 0);
        int pixelsColored = map.fill(start, 3);
        assertEquals(4, pixelsColored);
        assertEquals(3, map.getPixel(2, 0));
        assertEquals(3, map.getPixel(0, 2));
        assertEquals(3, map.getPixel(2, 2));
        assertEquals(0, map.getPixel(1, 1));
    }

    /**
     * test that the function does not run for the same color (0 points has changed)
     */
    @Test
    public void testFillSameColor() {
        Map2D map = new Map(3, 3, 0);
        int pixelsColored = map.fill(new Index2D(1, 1), 0);
        assertEquals(0, pixelsColored);
    }
    /**
     * tests allDistances function - checks that the map was created properly and the pixels contains the right distance
     */
    @Test
    public void testAllDistancesSimple() {
        Map2D map = new Map(5, 8, 0);
        Map2D res = map.allDistance(new Index2D(0, 0), 1);
        assertEquals(0, res.getPixel(0, 0));
        assertEquals(1, res.getPixel(4, 0));
        assertEquals(1, res.getPixel(0, 7));
        assertEquals(2, res.getPixel(4, 7));
        try {
            res.getPixel(7, 4);
            fail();
        }
        catch (Exception e) {
        }
    }
    /**
     * tests allDistances function with obstacles - checks if changed the distance of the right pixels (and not the obstacles)
     * this test also checks if the distance got through the obstacle and went behind them
     */
    @Test
    public void testObstacleAvoidanceLargeNonSquare() {
        Map2D map = new Map(10, 4, 0);
        map.setPixel(3, 0, 1);
        map.setPixel(3, 1, 1);
        map.setPixel(3, 2, 1);
        Map2D res = map.allDistance(new Index2D(0, 0), 1);
        assertEquals(-1, res.getPixel(3, 0));
        assertEquals(-1, res.getPixel(3, 2));
        assertEquals(4, res.getPixel(3, 3));
        assertEquals(5, res.getPixel(5, 0));
    }

    /**
     * tests allDistances function with a cyclic map - checks that the pixels at the edges contains the right distance
     */
    @Test
    public void testAllDistancesCyclic() {
        Map2D map = new Map(10, 10, 0);
        Map2D res = map.allDistance(new Index2D(0, 0), 1);
        assertEquals(1, res.getPixel(9, 0));
        assertEquals(1, res.getPixel(0, 9));
    }

    /**
     * test shortestPath function - checks that the path length is correct and p1 and p2 are the first and last in the array
     * this test also checks that the points are in the array are adjacent neighbors
     */
    @Test
    public void testShortestPathSimple() {
        Map2D map = new Map(10, 5, 0);
        map.setCyclic(false);
        Pixel2D p1 = new Index2D(0, 0);
        Pixel2D p2 = new Index2D(4, 3);
        Pixel2D[] path = map.shortestPath(p1, p2, 1);
        assertEquals(8, path.length);
        assertEquals(p1, path[0]);
        assertEquals(p2, path[path.length - 1]);
        for (int i = 0; i < path.length - 1; i++) {
            int dx = Math.abs(path[i].getX() - path[i+1].getX());
            int dy = Math.abs(path[i].getY() - path[i+1].getY());
            assertEquals(1, (dx + dy));
        }
    }

    /**
     * test shortestPath function with obstacles - checks that the path pass the obstacle
     */
    @Test
    public void testPathWithObstacle() {
        Map2D map = new Map(10, 5, 0);
        Pixel2D p1 = new Index2D(0, 0);
        Pixel2D p2 = new Index2D(4, 0);
        for (int y = 0; y < 4; y++) {
            map.setPixel(2, y, 1);
        }
        Pixel2D[] path = map.shortestPath(p1, p2, 1);
        assertNotNull(path);
        for (int i = 0;i<path.length;i++) {
            assertNotEquals(1, map.getPixel(path[i]));
        }
        boolean passedThroughOpening = false;
        for (int i = 0;i<path.length;i++) {
            if (path[i].getX() == 2 && path[i].getY() == 4) passedThroughOpening = true;
        }
        assertTrue(passedThroughOpening);
    }

    /**
     * test shortPath funciton for a cyclic map
     */
    @Test
    public void testCyclicShortestPath() {
        Map2D map = new Map(10, 5, 0);
        Pixel2D p1 = new Index2D(0, 2);
        Pixel2D p2 = new Index2D(9, 2);
        Pixel2D[] path = map.shortestPath(p1, p2, 1);
        assertNotNull(path);
        assertEquals(2, path.length);
        assertEquals(p1, path[0]);
        assertEquals(p2, path[1]);
    }
    @Test
    public void testShortestPathWhenNoPath() {
        Map2D map = new Map(5, 5, 0);
        map.setCyclic(false);
        Pixel2D p1 = new Index2D(0, 0);
        Pixel2D p2 = new Index2D(4, 4);
        map.setPixel(0, 1, 1);
        map.setPixel(1, 0, 1);
        map.setPixel(1, 1, 1);
        assertNull(map.shortestPath(p1, p2, 1));
    }

    /**
     * test that shortestPath returns null when p2 is an obstacle
     */
    @Test
    public void testShortestPathObstacleP2() {
        Map2D map = new Map(5, 5, 0);
        Pixel2D p1 = new Index2D(0, 0);
        Pixel2D p2 = new Index2D(2, 2);
        map.setPixel(2, 2, 1);
        assertNull(map.shortestPath(p1, p2, 1));
    }

}