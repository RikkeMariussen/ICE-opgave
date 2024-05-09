package PacMan;

import processing.core.PApplet;

public class Cherry {

    private PApplet parent;
    public Cherry(PApplet parent) {
        this.parent = parent;
    }

    private void createCherry(int xPos,int yPos){
        parent.ellipse(xPos*Map.getWallSpacing(),yPos*Map.getWallSpacing(),40,40);
        PacManGame.map[yPos][xPos] = PacManGame.CHERRY;
    }
    public void spawnCherry(){
        parent.fill(245, 66, 167);
        parent.ellipseMode(parent.CENTER);
        createCherry(1,1);
        createCherry(18,1);
        createCherry(1,18);
        createCherry(18,18);
    }
}
