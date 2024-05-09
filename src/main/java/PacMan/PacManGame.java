package PacMan;

import java.util.Arrays;

public class PacManGame {
    public static int EMPTY =  0;
    public static int WALL =   1;
    public static int CHEESE = 2;
    public static int CHERRY = 3;
    public static int PLAYER = 4;
    public static int[][] map;


    public static void populateMap(){
        map = new int[20][20];
        for (int[] ints : map) {
            Arrays.fill(ints, CHEESE);
        }
    }
    public static void printMap() { //just for testing array population.
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
}
