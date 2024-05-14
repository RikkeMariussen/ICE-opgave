import processing.core.PApplet;

import java.util.ArrayList;

public class BallDrop extends AGames {

    private PApplet parent;
    private FileIO io;
    private TextUI ui;
    private GUI gui;
    private String name;
    private int score;
    private int difficulty = 1;
    private int numberOfBalls = 3;
    private int ballsInterval;
    private boolean gameOver;
    private boolean startGame = true;
    private int diff;
    private String type = "";

    //Objects used for the game:
    private PlayerPlate playerPlate;
    private Bombs bombs;
    private PointObject ball;
    private ArrayList<PointObject> dropBalls = new ArrayList<>();
    private Timer ballsTimer = new Timer(ballsInterval); //Calls the method/class

    public BallDrop(PApplet parent, int width, int height, int diff) {
        this.parent = parent;
        this.diff = diff;
        this.gui = new GUI();
        this.ui = new TextUI();
    }

    public void gameSettings() {
        if (startGame) {
            for (int i = 0; i < setNumberOfBalls(); i++) {
                ball = new PointObject(parent, (int) parent.random(parent.width), parent.height - 800, 20, 20, 1.5f * diff, 3f * diff, parent.color(255, 105, 105));
                dropBalls.add(ball);
            }
        }
    }

    public void displayGame() {
        parent.background(255, 255, 237);
        ballsTimer.startTimer();

    }

    public void updateGame() {
        bombs = new Bombs(parent);
        playerPlate = new PlayerPlate(parent, parent.width / 2, parent.height - 75, 80, 20);
        if (!gameOver) {
            gameDraw();
        } else {
            //setHighScore();
            youDied();
        }
        if (startGame) {
            startGame = false;
        }
    }

    @Override
    public String youDied() {
        parent.fill(255, 0, 0);
        parent.textSize(50);
        parent.textAlign(parent.CENTER, parent.CENTER);
        parent.text("Game Over", parent.width / 2, parent.height / 2);
        parent.textSize(32);
        parent.text("Press 'R' to restart \n or ENTER to go back to the menu", parent.width / 2, parent.height / 2 + 75);
        parent.textSize(60);
        parent.text("Your score: " + score, parent.width / 2, parent.height / 2 + -100);
        keyPressed();
        return null;
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
                if (dropBalls.get(i).getSpeed() >= (5 * diff)) { //This if/else statement changes the speed of the ball so it is not constant
                    dropBalls.get(i).setSpeed(-(parent.random(1, 3.5f) * diff));
                } else if (dropBalls.get(i).getSpeed() < (5 * diff)) {
                    dropBalls.get(i).setSpeed((parent.random(0.5f, 1f)));
                }

            }
            if ((dropBalls.get(0).getY() > parent.height) && (dropBalls.get(1).getY() > parent.height) && (dropBalls.get(2).getY() > parent.height)) {
                gameOver = true;
            } else {
                gameOver = false;
            }
        }
    }

    public boolean intersect(PlayerPlate playerPlate, PointObject pointObject) {
        float distance = parent.dist(playerPlate.getX(), playerPlate.getY(), pointObject.getX(), pointObject.getY());
        if (distance < (playerPlate.getWidth() / 2 + pointObject.getW() / 2)) {
            return true;
        } else {
            return false;
        }
    }

    public void restartGame() {
        startGame = true;
        gameOver = false;
        score = 0;
        dropBalls.clear();
        gameSettings();
    }

    public void displayScore() {
        parent.fill(0);
        parent.textSize(25);
        parent.textAlign(parent.RIGHT);
        parent.text("Score: " + score, parent.width - 50, parent.height - 50);
    }
    public void typeName() {
        parent.fill(0);
        parent.textSize(25);
        parent.textAlign(parent.RIGHT);
        parent.text("Type your name here:", parent.width - 50, parent.height - 50);
        parent.text(type,parent.width - 75, parent.height - 75);
        type += parent.key;
    }


    @Override
    public String keyPressed() {
        if ((parent.key == 'r' || parent.key == 'R') && parent.keyPressed) {
            restartGame();
        } else if (parent.keyCode == parent.ENTER) {
            parent.keyCode = parent.RETURN;
            this.gameOver = false;
            this.startGame = true;
            restartGame();
            StartMenu.endCurrentGame();
        } else if((parent.key == 'h' || parent.key == 'H') && parent.keyPressed){
            typeName();
           // type += parent.key;
        }

        return null;
    }

    @Override
    public String playGame() {

        return null;
    }

    //Not used yet, but can be used to demonstrate the way the game is played
    @Override
    public String howToPlay() {
        return null;
    }

    @Override
    public String difficulties() {
        return null;
    }

    //Not used yet, but can be used to display the amount of balls dropped
    @Override
    public String lives() {
        return null;
    }

    //Getter

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getHighScore() {
        return null;
    }

    //Setter

    //Not used yet, but can be used to implement bombs in the game
    public void setNumberOfBombs() {
        //bombs.update();
    }

    public int setNumberOfBalls() {
        return numberOfBalls * difficulty;
    }

    @Override
    public String setHighScore() {
        parent.fill(0);
        parent.textSize(25);
        parent.textAlign(parent.CENTER);
        parent.text("Type your name to add it to the high score list:", (float) parent.width / 2, (float) parent.height / 2);
        gui.highScoreMsg(parent,parent.height,parent.width);
        ui.promptText(null);
        if (parent.keyCode == parent.ENTER) {
            parent.keyCode = parent.RETURN;
            youDied();
        }

        return null;
    }
}
