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
        clearGhostPit();
    }
    public static void printMap() { //just for testing array population.
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }
    public static void PopulateMapCherry(){
        map[1][1] = CHERRY;
        map[1][18] = CHERRY;
        map[18][1] = CHERRY;
        map[18][18] = CHERRY;
    }
    public static void clearGhostPit(){
        map[8][9] = EMPTY;
        map[8][10] = EMPTY;
        map[9][8] = EMPTY;
        map[9][9] = EMPTY;
        map[9][10] = EMPTY;
        map[9][11] = EMPTY;
        map[10][8] = EMPTY;
        map[10][9] = EMPTY;
        map[10][10] = EMPTY;
        map[10][11] = EMPTY;
    }
}
