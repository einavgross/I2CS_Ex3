import Shapes.Index2D;
import Shapes.Map2D;
import Shapes.Map;
import Shapes.Pixel2D;
import exe.ex3.game.Game;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class AlgoFuntionsTest {
    @Test
    public void closestPinkTest() {
            Map2D map = new Map(15, 15, 0);
            int blue = 1;
            int pink = 2;
            Pixel2D pacmanPos = new Index2D(10, 9);
            Pixel2D pinkInAlley = new Index2D(10, 12);
            Pixel2D pinkInOpen = new Index2D(13, 9);
            map.setPixel(pinkInAlley.getX(), pinkInAlley.getY(), pink);
            map.setPixel(pinkInOpen.getX(), pinkInOpen.getY(), pink);
            map.setPixel(9, 12, blue);
            map.setPixel(11, 12, blue);
            map.setPixel(10, 13, blue);
            Map2D distMap = map.allDistance(pacmanPos, blue);
            Pixel2D result = Ex3Algo.closestPink(pacmanPos, map, distMap, blue, pink);
            assertNotNull(result);
            assertEquals(pinkInAlley, result);
    }
    @Test
    public void searchForPinkTest(){
            Map2D map = new Map(15, 15, 0);
            int blue = Game.getIntColor(Color.blue,0);
            int pink = Game.getIntColor(Color.pink,0);
            Pixel2D pacmanPos = new Index2D(5, 5);
            //אין נקודות ורודות במפה
            Map2D distMapEmpty = map.allDistance(pacmanPos, blue);
            int randomDir = Ex3Algo.searchForPink(pacmanPos, map, distMapEmpty, blue, pink);
            assertTrue(randomDir > 0 && randomDir <= 4);
            // יש נקודה ורודה מצד ימין
            map.setPixel(7, 5, pink);
            Map2D distMapWithPink = map.allDistance(pacmanPos, blue);
            int directedDir = Ex3Algo.searchForPink(pacmanPos, map, distMapWithPink, blue, pink);
            assertEquals(4, directedDir);
    }
    @Test
    public void getEscapeDirectionTest() {
        Map2D map = new Map(15, 15, 0);
        map.setCyclic(true);
        int blue = 1;
        Pixel2D pos = new Index2D(7, 0);
        Pixel2D ghostPos = new Index2D(7, 1);
        int resultDir = Ex3Algo.getEscapeDirection(pos, map, blue, ghostPos);
        assertEquals(2, resultDir);
        Pixel2D posX = new Index2D(0, 7);
        Pixel2D ghostPosX = new Index2D(1, 7);
        int resultLeft = Ex3Algo.getEscapeDirection(posX, map, blue, ghostPosX);
        assertEquals(2, resultLeft);
    }

    @Test
    public void getDirectionTest (){
            Map2D map = new Map(15, 15, 0);
            int blue = Game.getIntColor(Color.blue,0);
            int up = Game.UP, left = Game.LEFT, down = Game.DOWN, right = Game.RIGHT;
            map.setCyclic(false);
            Pixel2D pos = new Index2D(5, 5);
            Pixel2D targetRight = new Index2D(7, 5);
            int dirRight = Ex3Algo.getDirection(pos, map, blue, targetRight);
            assertEquals(right, dirRight);
            Pixel2D targetDown = new Index2D(5, 3);
            int dirDown = Ex3Algo.getDirection(pos, map, blue, targetDown);
            assertEquals(down, dirDown);
            Pixel2D targetUp = new Index2D(5, 7);
            int dirUp = Ex3Algo.getDirection(pos, map, blue, targetUp);
            assertEquals(up, dirUp);
            Pixel2D targetLeft = new Index2D(3, 5);
                int dirLeft = Ex3Algo.getDirection(pos, map, blue, targetLeft);
                assertEquals(left, dirLeft);
            map.setCyclic(true);
            Pixel2D posLeftEdge = new Index2D(0, 7);
            Pixel2D targetAcrossX = new Index2D(14, 7);
            int dirCyclicL = Ex3Algo.getDirection(posLeftEdge, map, blue, targetAcrossX);
            assertEquals(left, dirCyclicL);
            Pixel2D posTopEdge = new Index2D(7, 14);
            Pixel2D targetAcrossY = new Index2D(7, 0);
            int dirCyclicU = Ex3Algo.getDirection(posTopEdge, map, blue, targetAcrossY);
            assertEquals(up, dirCyclicU);
        }
        @Test
        public void TestBlockedDirection(){
            Map2D map = new Map(15, 15, 0);
            int blue = Game.getIntColor(Color.blue,0);
            map.setCyclic(false);
            Pixel2D blockedPos = new Index2D(5, 5);
            map.setPixel(5, 6, blue);
            map.setPixel(5, 4, blue);
            map.setPixel(4, 5, blue);
            map.setPixel(6, 5, blue);
            Pixel2D unreachableTarget = new Index2D(10, 10);
            int dirBlocked = Ex3Algo.getDirection(blockedPos, map, blue, unreachableTarget);
            assertEquals(0, dirBlocked);
        }
    }
