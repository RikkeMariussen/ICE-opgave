package PacMan;

import processing.core.PApplet;

public class Score {
    private PApplet parent;
    private static int currentAmountOfCheese;
    private int score;

    public Score(PApplet parent, int score) {
        this.parent = parent;
        this.score = score;
    }

public void setCurrentAmountOfCheese(){
    for (int i = 0; i < PacManGame.map.length; i++) {
        for (int j = 0; j < PacManGame.map[i].length; j++) {
            // Check if the value is 0
            if (PacManGame.map[i][j] == 0 || PacManGame.map[i][j] == 4) {
                currentAmountOfCheese++;
            }
        }
    }
}

    public void checkScore(){
        int newCheeseAmount = 1;
        for (int i = 0; i < PacManGame.map.length; i++) {
            for (int j = 0; j < PacManGame.map[i].length; j++) {
                // Check if the value is 0
                if (PacManGame.map[i][j] == 0) {
                    newCheeseAmount++;
                }
                if(newCheeseAmount>currentAmountOfCheese){
                    currentAmountOfCheese=newCheeseAmount;
                    setScore(getScore()+10);

                }
            }
        }
    }

    public void drawScore(){
        parent.fill(0);
        parent.textSize(16);
        parent.text("Score: " + getScore(),30,12);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
