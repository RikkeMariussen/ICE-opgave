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
        spawnCherry1();
        spawnCherry2();
        spawnCherry3();
        spawnCherry4();
    }
    public void spawnCherry1(){
        if(PacManGame.map[1][1] == PacManGame.CHERRY){
            createCherry(1,1);
        }
    }
    public void spawnCherry2(){
        if(PacManGame.map[18][1] == PacManGame.CHERRY){
            createCherry(1,18);
        }
    }
    public void spawnCherry3(){
        if(PacManGame.map[1][18] == PacManGame.CHERRY){
            createCherry(18,1);
        }
    }
    public void spawnCherry4(){
        if(PacManGame.map[18][18] == PacManGame.CHERRY){
            createCherry(18,18);
        }
    }
}
