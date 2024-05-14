package PacMan;
import processing.core.PApplet;


public class Map {

    static int wallSpacing = 60;
    private PApplet parent;

    public Map(PApplet parent) {
        this.parent = parent;
    }

public void setupUp(){
    parent.background(0,0,0);
    parent.blendMode(parent.REPLACE);
    parent.strokeWeight(3);
    parent.noStroke();
    parent.fill(135, 206, 250);
    parent.rectMode(parent.CENTER);



}

    public void createMap() {
        setupUp();
        createBorder();
        createUpperWallSkeleton();
        createLowerWallSkeleton();
        createGhostPit();
    }

    private void createBorder() {
        horizontalBlockConstructor(0,0,20);
        horizontalBlockConstructor(0,19,20);
        verticalBlockConstructor(0,1,18);
        verticalBlockConstructor(19,1,18);
    }

    private void createUpperWallSkeleton() {
        horizontalBlockConstructor(2, 2, 2);
        horizontalBlockConstructor(16, 2, 2);

        horizontalBlockConstructor(2, 4, 2);
        horizontalBlockConstructor(16, 4, 2);

        horizontalBlockConstructor(5, 2, 3);
        horizontalBlockConstructor(12, 2, 3);

        verticalBlockConstructor(5, 4, 5);
        verticalBlockConstructor(14, 4, 5);

        horizontalBlockConstructor(7, 4, 6);

        verticalBlockConstructor(9, 1, 2);
        verticalBlockConstructor(10, 1, 2);

        horizontalBlockConstructor(6, 6, 2);
        horizontalBlockConstructor(12, 6, 2);

        verticalBlockConstructor(9, 5, 2);
        verticalBlockConstructor(10, 5, 2);


        horizontalBlockConstructor(1, 6, 3);
        horizontalBlockConstructor(1, 7, 3);
        horizontalBlockConstructor(16, 6, 3);
        horizontalBlockConstructor(16, 7, 3);
    }

    private void createLowerWallSkeleton() {
        horizontalBlockConstructor(2, 17, 2);
        horizontalBlockConstructor(16, 17, 2);

        horizontalBlockConstructor(2, 15, 2);
        horizontalBlockConstructor(16, 15, 2);

        horizontalBlockConstructor(5, 17, 3);
        horizontalBlockConstructor(12, 17, 3);

        verticalBlockConstructor(5, 11, 5);
        verticalBlockConstructor(14, 11, 5);

        horizontalBlockConstructor(7, 15, 6);

        verticalBlockConstructor(9, 17, 2);
        verticalBlockConstructor(10, 17, 2);

        horizontalBlockConstructor(6, 13, 2);
        horizontalBlockConstructor(12, 13, 2);

        verticalBlockConstructor(9, 13, 2);
        verticalBlockConstructor(10, 13, 2);

        horizontalBlockConstructor(1, 12, 3);
        horizontalBlockConstructor(1, 13, 3);
        horizontalBlockConstructor(16, 12, 3);
        horizontalBlockConstructor(16, 13, 3);
    }

    private void createGhostPit() {
        horizontalBlockConstructor(7, 8, 2);
        horizontalBlockConstructor(11, 8, 2);
        horizontalBlockConstructor(7, 11, 6);
        verticalBlockConstructor(7, 9, 2);
        verticalBlockConstructor(12, 8, 3);

        //Middle block on left and right side
        horizontalBlockConstructor(2, 9, 2);
        horizontalBlockConstructor(2, 10, 2);

        horizontalBlockConstructor(16, 9, 2);
        horizontalBlockConstructor(16, 10, 2);

    }

    private void horizontalBlockConstructor(int xPos, int yPos, int width) {
        int currentX = xPos;
        for (int i = 0; i < width; i++) {
            parent.rect(wallSpacing * currentX, wallSpacing * yPos, wallSpacing, wallSpacing);
            PacManGame.map[yPos][currentX] = PacManGame.WALL;
            currentX++;
        }
    }

    private void verticalBlockConstructor(int xPos, int yPos, int height) {
        int currentY = yPos;
        for (int i = 0; i < height; i++) {
            parent.rect(wallSpacing * xPos, wallSpacing * currentY, wallSpacing, wallSpacing);
            PacManGame.map[currentY][xPos] = PacManGame.WALL;
            currentY++;
        }
    }

    public static int getWallSpacing() {
        return wallSpacing;
    }

}

