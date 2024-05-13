import processing.core.PApplet;

import java.util.ArrayList;

public class BallDropGame extends PApplet {
/*
    private PApplet parent;
    GUI gui;
    String name;
    int numberOfPointObject;
    int numberOfBombs;
    int pointObjectInterval;
    int bombsInterval;
    int activePointObject;
    int score;
    int difficulty = 1;
    int difficultyBombs = 6;
    int easy;
    int medium;
    int hard;
    int activeBalls;
    int numberOfBalls = 3;
    int ballsInterval;
    boolean gameOverBallDrop;

    //Objects used for the game:
    PlayerPlate playerPlate = new PlayerPlate(this, width / 2, height + 600, 80, 20);
    Bombs bombs = new Bombs(this);
    PointObject ball;
    ArrayList<PointObject> dropBalls = new ArrayList<>();
    Timer ballsTimer = new Timer(ballsInterval); //Calls the method/class

    public void settings() {
        size(800, 800);
        for (int i = 0; i < setNumberOfBalls(); i++) {
            ball = new PointObject(this, (int) random(width), 100, 20, 20, 3, 7, color(105, 105, 105));
            dropBalls.add(ball);
            // dropBalls.get(i);
        }
    }

    public void displayScore() {
        fill(0);
        textSize(25);
        textAlign(RIGHT);
        text("Score: " + score, width - 50, height - 50);
    }

    public void setup() {
        background(255, 255, 237);
        score = 0;
        setNumberOfBombs();
        ballsTimer.startTimer();

    }

    public int setNumberOfBalls() {
        return numberOfBalls * difficulty;
    }

    public void setNumberOfBombs() {
            bombs.update();
    }

    public void draw() {
        background(255, 255, 237);
        if (!gameOverBallDrop) {
            gameDraw();
        } else {
            gameOverDraw();
        }

    }

    public void gameOverDraw() {
        fill(255, 0, 0);
        textSize(50);
        textAlign(CENTER, CENTER);
        text("Game Over", width / 2, height / 2);
        textSize(32);
        text("Press 'R' to restart", width / 2, height / 2 + 50);
        textSize(60);
        text("Your score: " + score, width / 2, height / 2 + -100);
    }

    public void gameDraw() {
        playerPlate.update();
        playerPlate.display();
        displayScore();

        //Timer management:
        if (ballsTimer.complete()) {
            ballsTimer.startTimer();
        }

        for (int i = 0; i < dropBalls.size(); i++) {
            dropBalls.get(i).displayRoundObject();
            dropBalls.get(i).updateRoundObject();

            dropBalls.get(i).setY((int) dropBalls.get(i).getSpeed() + dropBalls.get(i).getY());

            //check collisions
            if (intersect(playerPlate, dropBalls.get(i))) {
                dropBalls.get(i).catchRoundObject();
                score++; //Counts the score with +1 when the user catches a ball.
            }

            if (dropBalls.get(i).getY() > height) {
                gameOverBallDrop = true;
            } else {
                gameOverBallDrop = false;
            }
        }
    }


    void setBallSpeed(PointObject ball) {
        //Makes the balls go across the screen
        ball.setSpeed(ball.getSpeed());

        //todo: Right now it is not incorporated that if the person does not catch the ball (see if statement), they need to lose a life
        if (y > height + h/2) {
            y = -h/2;
        }
    }

    boolean intersect(PlayerPlate playerPlate, PointObject pointObject) {
        float distance = dist(playerPlate.getX(), playerPlate.getY(), pointObject.getX(), pointObject.getY());
        if (distance < (playerPlate.getWidth() / 2 + pointObject.getW() / 2)) {
            return true;
        } else {
            return false;
        }
    }

    public void keyPressed() {
        if (key == 'r' || key == 'R') {
            restartGame();
        }
    }

    public void restartGame() {
        gameOverBallDrop = false;
        score = 0;

    }

    public static void main(String[] args) {
        //   StartMenu startMenu = new StartMenu();
        //   startMenu.runDialog();
        PApplet.main("BallDropGame");
    }*/
}