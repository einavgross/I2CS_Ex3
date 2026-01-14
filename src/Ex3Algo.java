import Shapes.Index2D;
import Shapes.Map;
import Shapes.Map2D;
import Shapes.Pixel2D;
import exe.ex3.game.Game;
import exe.ex3.game.GhostCL;
import exe.ex3.game.PacManAlgo;
import exe.ex3.game.PacmanGame;

import java.awt.*;

/**
 * This is the major algorithmic class for Ex3 - the PacMan game:
 *
 * This code is a very simple example (random-walk algorithm).
 * Your task is to implement (here) your PacMan algorithm.
 */
public class Ex3Algo implements PacManAlgo{
	private int _count;
	public Ex3Algo() {_count=0;}
	@Override
	/**
	 *  Add a short description for the algorithm as a String.
	 */
	public String getInfo() {
		return "check";
	}
	@Override
	/**
	 * This ia the main method - that you should design, implement and test.
	 */
	public int move(PacmanGame game) {
        int code = 0;
        int[][] board = game.getGame(0);
        printBoard(board);
        Map2D map = new Map(board);
        map.setCyclic(game.isCyclic());
        int blue = Game.getIntColor(Color.BLUE, code);
        int pink = Game.getIntColor(Color.PINK, code);
        int black = Game.getIntColor(Color.BLACK, code);
        int green = Game.getIntColor(Color.GREEN, code);
        System.out.println("Blue=" + blue + ", Pink=" + pink + ", Black=" + black + ", Green=" + green);
        Pixel2D pos = pointFromString(game.getPos(code));
        System.out.println("Pacman coordinate: "+ pos);
        GhostCL[] ghosts = game.getGhosts(code);
        printGhosts(ghosts);
		_count++;
        Map2D distMap = map.allDistance(pos, blue);
        Pixel2D dangerGhostPos = null;
        //int minGhostDist = Integer.MAX_VALUE;
        for (int i = 0; i < ghosts.length; i++) {
            Pixel2D ghostPos = pointFromString(ghosts[i].getPos(0));
            int dist = distMap.getPixel(ghostPos);
            if (dist !=-1 && dist < 5) {
                if (ghosts[i].remainTimeAsEatable(0) <= 0) {
                    dangerGhostPos = ghostPos;
                    break;
                }
            }
        }
        if (dangerGhostPos != null) {
            System.out.println("FLEEING!");
            return getEscapeDirection(pos, map, blue, dangerGhostPos);
        }
        return searchForPink(pos, map, distMap, blue, pink);
    }

    private int getEscapeDirection(Pixel2D pos, Map2D map, int blue, Pixel2D ghostPos) {
        int up = Game.UP, left = Game.LEFT, down = Game.DOWN, right = Game.RIGHT;
        int escapeDir =0;
        double maxDist = -1;
        if (!map.isCyclic()) {
            Pixel2D nextUp = new Index2D(pos.getX(), pos.getY() + 1);
            if (map.isInside(nextUp) && map.getPixel(nextUp) != blue) {
                double d = nextUp.distance2D(ghostPos);
                if (d > maxDist) {
                    maxDist = d;
                    escapeDir = up;
                }
            }
            Pixel2D nextDown = new Index2D(pos.getX(), pos.getY() - 1);
            if (map.isInside(nextDown) && map.getPixel(nextDown) != blue) {
                double d = nextDown.distance2D(ghostPos);
                if (d > maxDist) {
                    maxDist = d;
                    escapeDir = down;
                }
            }
            Pixel2D nextLeft = new Index2D(pos.getX() - 1, pos.getY());
            if (map.isInside(nextLeft) && map.getPixel(nextLeft) != blue) {
                double d = nextLeft.distance2D(ghostPos);
                if (d > maxDist) {
                    maxDist = d;
                    escapeDir = left;
                }
            }
            Pixel2D nextRight = new Index2D(pos.getX() + 1, pos.getY());
            if (map.isInside(nextRight) && map.getPixel(nextRight) != blue) {
                double d = nextRight.distance2D(ghostPos);
                if (d > maxDist) {
                    maxDist = d;
                    escapeDir = right;
                }
            }
        }
        else {
            Pixel2D nextUp = new Index2D(pos.getX(), (pos.getY() + (map.getHeight() + 1)) % map.getHeight());
            if (map.isInside(nextUp) && map.getPixel(nextUp) != blue) {
                double d = nextUp.distance2D(ghostPos);
                if (d > maxDist) {
                    maxDist = d;
                    escapeDir = up;
                }
            }
            Pixel2D nextDown = new Index2D(pos.getX(), (pos.getY() - 1) % map.getHeight());
            if (map.isInside(nextDown) && map.getPixel(nextDown) != blue) {
                double d = nextDown.distance2D(ghostPos);
                if (d > maxDist) {
                    maxDist = d;
                    escapeDir = down;
                }
            }
            Pixel2D nextLeft = new Index2D((pos.getX() + (map.getWidth() - 1)) % map.getWidth(), pos.getY());
            if (map.isInside(nextLeft) && map.getPixel(nextLeft) != blue) {
                double d = nextLeft.distance2D(ghostPos);
                if (d > maxDist) {
                    maxDist = d;
                    escapeDir = left;
                }
            }
            Pixel2D nextRight = new Index2D((pos.getX() + 1) % map.getWidth(), pos.getY());
            if (map.isInside(nextRight) && map.getPixel(nextRight) != blue) {
                double d = nextRight.distance2D(ghostPos);
                if (d > maxDist) {
                    maxDist = d;
                    escapeDir = right;
                }
            }
        }
        if (escapeDir == 0) return randomDir();
        else return escapeDir;
    }

    private static void printBoard(int[][] b) {
		for(int y =0;y<b[0].length;y++){
			for(int x =0;x<b.length;x++){
				int v = b[x][y];
				System.out.print(v+"\t");
			}
			System.out.println();
		}
	}
	private static void printGhosts(GhostCL[] gs) {
		for(int i=0;i<gs.length;i++){
			GhostCL g = gs[i];
			System.out.println(i+") status: "+g.getStatus()+",  type: "+g.getType()+",  pos: "+g.getPos(0)+",  time: "+g.remainTimeAsEatable(0));
		}
	}
	private static int randomDir() {
		int[] dirs = {Game.UP, Game.LEFT, Game.DOWN, Game.RIGHT};
		int ind = (int)(Math.random()*dirs.length);
		return dirs[ind];
	}
    private static Pixel2D pointFromString(String pos) {
        String [] p = pos.split(",");
        int x = Integer.parseInt(p[0]);
        int y = Integer.parseInt(p[1]);
        return new Index2D(x, y);
    }

    /**
     * This function computes the closest pink dot to the Pacman
     * @param pos the Pacman's pixel position
     * @param map Map2D object representing the board
     * @param distMap all the  distances from pos
     * @param blue integer representing the walls color
     * @param pink integer representing the color pink
     * @return the pink pixel that its distance to the Pacman is the shortest
     * This function uses Map2D's method of allDistance() and finds the pink dot that its value in distMap is the smallest and has the min amount of available neighbors
     */
    public static Pixel2D closestPink (Pixel2D pos, Map2D map, Map2D distMap, int blue, int pink) {
        Pixel2D target = null;
        int minDistance = Integer.MAX_VALUE;
        int minNeighbors = Integer.MAX_VALUE;
        int[][] board = map.getMap();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == pink) {
                    int distToPink = distMap.getPixel(i, j);
                    if (distToPink != -1) {
                        int neighbors = 0;
                        if (!map.isCyclic()) {
                            Pixel2D nUp = new Index2D(i, j + 1);
                            if (map.isInside(nUp) && map.getPixel(nUp) != blue) {
                                neighbors++;
                            }

                            Pixel2D nDown = new Index2D(i, j - 1);
                            if (map.isInside(nDown) && map.getPixel(nDown) != blue) {
                                neighbors++;
                            }

                            Pixel2D nLeft = new Index2D(i - 1, j);
                            if (map.isInside(nLeft) && map.getPixel(nLeft) != blue) {
                                neighbors++;
                            }

                            Pixel2D nRight = new Index2D(i + 1, j);
                            if (map.isInside(nRight) && map.getPixel(nRight) != blue) {
                                neighbors++;
                            }
                        }
                        if (map.isCyclic()) {
                            Pixel2D nUp = new Index2D(i, (j + (map.getHeight() + 1)) % map.getHeight());
                            if (map.isInside(nUp) && map.getPixel(nUp) != blue) {
                                neighbors++;
                            }

                            Pixel2D nDown = new Index2D(i, (j + (map.getHeight() - 1)) % map.getHeight());
                            if (map.isInside(nDown) && map.getPixel(nDown) != blue) {
                                neighbors++;
                            }

                            Pixel2D nLeft = new Index2D((i + (map.getWidth() - 1)) % map.getWidth(), j);
                            if (map.isInside(nLeft) && map.getPixel(nLeft) != blue) {
                                neighbors++;
                            }

                            Pixel2D nRight = new Index2D((i + 1) % map.getWidth(), j);
                            if (map.isInside(nRight) && map.getPixel(nRight) != blue) {
                                neighbors++;
                            }
                        }
                        if (distToPink < minDistance) {
                            minDistance = distToPink;
                            minNeighbors = neighbors;
                            target = new Index2D(i, j);
                        } else if (distToPink == minDistance) {
                            if (neighbors < minNeighbors) {
                                target = new Index2D(i, j);
                                minNeighbors = neighbors;
                            }
                        }
                    }
                }
            }
        }
        return target;
    }

    /**
     * this function computes the directions to the nearest pink dot, using closestPink() function.
     * @param pos the Pacman's position
     * @param map Map2D object representing the board
     * @param blue integer representing the walls color
     * @param pink integer representing the color pink
     * @return int representing the direction for the pacman,if target is null, return random direction, using randomDir()
     * this function checks if the next step for target is under/above/besides to the pacman, and return the direction to it
     */
    private static int searchForPink(Pixel2D pos, Map2D map,Map2D distMap ,int blue, int pink) {
        Pixel2D target = closestPink(pos,map,distMap,blue,pink);
        int dir=0;
        if (target != null ) {
            dir = getDirection(pos, map, blue, target);
        }
        if (target == null) {
            dir = randomDir();
        }
        return dir;
    }

    private static int getDirection(Pixel2D pos, Map2D map, int blue, Pixel2D target) {
        int up = Game.UP, left = Game.LEFT, down = Game.DOWN, right = Game.RIGHT;
        int dir = 0;
        Pixel2D[] SP = map.shortestPath(pos, target, blue);
        if (SP != null && SP.length > 1) {
            System.out.println("Is map cyclic?" + map.isCyclic());
            if (!map.isCyclic()) {
                Pixel2D nextUp = new Index2D(pos.getX(), pos.getY() + 1);
                if (nextUp.equals(SP[1])) {
                    dir = up;
                }
                Pixel2D nextDown = new Index2D(pos.getX(), pos.getY() - 1);
                if (nextDown.equals(SP[1])) {
                    dir = down;
                }
                Pixel2D nextLeft = new Index2D(pos.getX() - 1, pos.getY());
                if (nextLeft.equals(SP[1])) {
                    dir = left;
                }
                Pixel2D nextRight = new Index2D(pos.getX() + 1, pos.getY());
                if (nextRight.equals(SP[1])) {
                    dir = right;
                }
            }
            if (map.isCyclic()){
                Pixel2D nextUp = new Index2D(pos.getX(), (pos.getY() + (map.getHeight() + 1)) % map.getHeight());
                if (nextUp.equals(SP[1])) {
                    dir = up;
                }
                Pixel2D nextDown = new Index2D(pos.getX(), (pos.getY() - 1) % map.getHeight());
                if (nextDown.equals(SP[1])) {
                    dir = down;
                }
                Pixel2D nextLeft = new Index2D((pos.getX() + (map.getWidth() - 1)) % map.getWidth(), pos.getY());
                if (nextLeft.equals(SP[1])) {
                    dir = left;
                }
                Pixel2D nextRight = new Index2D((pos.getX() + 1) % map.getWidth(), pos.getY());
                if (nextRight.equals(SP[1])) {
                    dir = right;
                }
            }
        }
        return dir;
    }
}