package PacMan;
import processing.core.PApplet;

public class Cheese {
    private PApplet parent;

    public Cheese(PApplet parent) {
        this.parent = parent;
    }
    public void spawnCheese(){
        for (int i = 0; i < PacManGame.map.length; i++) {
            for (int j = 0; j < PacManGame.map[i].length; j++) {
                // Check if the value is 2
                if (PacManGame.map[i][j] == 2) {
                    // If value is 2, save the indexes and call createCheese function
                    createCheese(i, j);
                }
            }
        }
    }
    private void createCheese(int yPos, int xPos){
        parent.fill(252, 241, 194);
        parent.ellipseMode(parent.CENTER);
        parent.ellipse(xPos*Map.getWallSpacing(),yPos*Map.getWallSpacing(),10,10);
    }
}
