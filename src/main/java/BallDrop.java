import processing.core.PApplet;

import java.util.ArrayList;

public class BallDrop extends AGames {

    private PApplet parent;
    FileIO io;
    GUI gui;
    String name;
    int score;
    int difficulty = 1;
    int difficultyBombs = 6;
    int numberOfBalls = 3;
    int ballsInterval;
    boolean gameOverBallDrop;
    boolean startGame = true;
    int diff;

    //Objects used for the game:
    PlayerPlate playerPlate;
    Bombs bombs;
    PointObject ball;
    ArrayList<PointObject> dropBalls = new ArrayList<>();
    Timer ballsTimer = new Timer(ballsInterval); //Calls the method/class

    public BallDrop(PApplet parent, int width, int height, int diff) {
        this.parent = parent;
        this.diff = diff;
    }

    public void gameSettings() {
        if (startGame) {
            for (int i = 0; i < setNumberOfBalls(); i++) {
                ball = new PointObject(parent, (int) parent.random(parent.width), parent.height - 800, 20, 20, 2 * diff, 4 * diff, parent.color(255, 105, 105));
                dropBalls.add(ball);
            }
        }
    }

    public void displayScore() {
        parent.fill(0);
        parent.textSize(25);
        parent.textAlign(parent.RIGHT);
        parent.text("Score: " + score, parent.width - 50, parent.height - 50);
    }

    public void displayGame() {
        parent.background(255, 255, 237);
        // score = 0;
        setNumberOfBombs();
        ballsTimer.startTimer();

    }

    public int setNumberOfBalls() {
        return numberOfBalls * difficulty;
    }

    public void setNumberOfBombs() {
        //bombs.update();
    }

    public void updateGame() {
        bombs = new Bombs(parent);
        playerPlate = new PlayerPlate(parent, parent.width / 2, parent.height - 75, 80, 20);
        if (!gameOverBallDrop) {
            gameDraw();
        } else {
            gameOverDraw();
        }
        if (startGame) {
            startGame = false;
        }
    }

    @Override
    public String youDied() {
        return null;
    }

    public void gameOverDraw() {
        parent.fill(255, 0, 0);
        parent.textSize(50);
        parent.textAlign(parent.CENTER, parent.CENTER);
        parent.text("Game Over", parent.width / 2, parent.height / 2);
        parent.textSize(32);
        parent.text("Press 'R' to restart \n or ENTER to go back to the menu", parent.width / 2, parent.height / 2 + 75);
        parent.textSize(60);
        parent.text("Your score: " + score, parent.width / 2, parent.height / 2 + -100);
        keyPressed();

    }

    public void gameDraw() {
        playerPlate.update();
        playerPlate.display();
        displayScore();
        gameSettings();

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
                score++; //Counts the score with +1 when the user catches a ball.
                dropBalls.get(i).setY(-10); //Gives the ball a new Y coordinate when it is caught.
                dropBalls.get(i).setX((int) parent.random(0, parent.width)); //Gives the ball a new X coordinate when it is caught, so it does not run in a loop.
                if (dropBalls.get(i).getSpeed() < 6) { //This if/else statement changes the speed of the ball so it is not constant
                    dropBalls.get(i).setSpeed(dropBalls.get(i).getSpeed() + 1);
                } else {
                    dropBalls.get(i).setSpeed(dropBalls.get(i).getSpeed() - 1);
                }

            }
            if (dropBalls.get(i).getY() > parent.height) {
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
       /* if (y > height + h/2) {
            y = -h/2;
        }*/
    }

    boolean intersect(PlayerPlate playerPlate, PointObject pointObject) {
        float distance = parent.dist(playerPlate.getX(), playerPlate.getY(), pointObject.getX(), pointObject.getY());
        if (distance < (playerPlate.getWidth() / 2 + pointObject.getW() / 2)) {
            return true;
        } else {
            return false;
        }
    }


    public void restartGame() {
        startGame = true;
        gameOverBallDrop = false;
        score = 0;
        dropBalls.clear();
        gameSettings();
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String playGame() {

        return null;
    }

    @Override
    public String setHighScore() {
        return null;
    }

    @Override
    public String getHighScore() {
        return null;
    }

    @Override
    public String keyPressed() {

        if ((parent.key == 'r' || parent.key == 'R') && parent.keyPressed) {
            restartGame();
        } else if (parent.keyCode == parent.ENTER) {
            parent.keyCode = parent.RETURN;
            this.gameOverBallDrop = false;
            this.startGame = true;
            restartGame();
            StartMenu.endCurrentGame();
        }
        return null;
    }

    @Override
    public String howToPlay() {
        return null;
    }

    @Override
    public String difficulties() {
        return null;
    }

    @Override
    public String lives() {
        return null;
    }
}
