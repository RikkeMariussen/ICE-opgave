import processing.core.PApplet;
import processing.core.PVector;

public class BrickBreaker extends AGames {

    private PApplet parent;
    private PVector ballPosition;
    private PVector ballVelocity;
    private float ballBaseSpeed = 5;
    private float ballRadius = 10;
    private Bricks[] bricks;
    private int numBricks = 50;
    private int score = 0;
    private boolean gameOver;
    private int diff;
    private PlayerPlate playerPlate;
    private boolean gameStart = true;

    public BrickBreaker(PApplet parent, int width, int height, int diff) {
        this.parent = parent;
        this.diff = diff;
    }

    public void logic() {
        playerPlate = new PlayerPlate(parent, parent.width / 2, parent.height-75, 80, 20);
        ballPosition = new PVector(parent.width / 2, parent.height - 50);
        ballVelocity = new PVector(0, (float) -((ballBaseSpeed - 1) * diff));
        bricks = new Bricks[numBricks];
        for (int i = 0; i < numBricks; i++) {
            int x = ((i % 10) * 82) + 40;
            int y = (i / 10) * 30 + 50;
            int brickColor = parent.color(255);
            int durability = 1;

            // Check if it's the 3rd row
            if (i / 10 == 2) {
                // Check if it's the 3rd or 8th brick
                if (i % 10 == 2 || i % 10 == 7) {
                    brickColor = parent.color(255, 165, 0);
                    durability = 5;
                }
            }

            bricks[i] = new Bricks(parent, x, y, 80, 15, brickColor, durability);
        }
    }

    @Override
    public void updateGame() {
        if (gameStart) {
            logic();
            gameStart = false;
        }
        parent.background(75);
        if (!gameOver) {
            drawGame();
        } else {
            ((StartMenu) parent).setDeathState("BrickBreaker", score); // dette kan bruges i stedet for youDied metoden
            restartGame();
        }
    }

    public void drawGame() {
        drawText("Score: " + score, parent.width / 2, parent.height -25);
        playerPlate.display();
        playerPlate.update();

        // Update ball position
        ballPosition.add(ballVelocity);

        // Check if the ball hits the wall
        if (ballPosition.x > parent.width - ballRadius || ballPosition.x < ballRadius) {
            // Reverse x-component of velocity
            ballVelocity.x *= -1;
        }
        if (ballPosition.y < ballRadius) {
            // Reverse y-component of velocity
            ballVelocity.y *= -1;
        }
        if (ballPosition.y > parent.height + ballRadius) {
            gameOver = true; // Set gameOver to true if the ball goes below the bottom of the screen
        }
        // Check collision with the player plate
        if (ballPosition.y + ballRadius >= playerPlate.getY() &&
                ballPosition.x >= playerPlate.getX() - playerPlate.getWidth() / 2 &&
                ballPosition.x <= playerPlate.getX() + playerPlate.getWidth() / 2) {

            // Calculate the direction vector from the center of the player plate to the ball
            PVector direction = new PVector(0, -1);
            // Reflect the velocity vector symmetrically with respect to the direction vector
            ballVelocity = PVector.sub(ballVelocity, PVector.mult(PVector.mult(direction, 2 * ballVelocity.dot(direction) / direction.magSq()), 1));
        }

        boolean collisionDetected = false; // Variable to track if collision with any brick detected

        for (int i = 0; i < numBricks; i++) {
            bricks[i].display();
            if (bricks[i].checkCollision(ballPosition, ballRadius)) {
                // Set collision detected to true
                collisionDetected = true;

                // Calculate the direction vector from the center of the brick to the ball
                PVector direction = PVector.sub(ballPosition, new PVector(bricks[i].getBrickX(), bricks[i].getBrickY()));
                direction.rotate(parent.random(parent.degrees(-15), parent.degrees(15)));

                // Reflects ball backwards depending on direction hit
                ballVelocity = PVector.sub(ballVelocity, PVector.mult(PVector.mult(direction, 2 * ballVelocity.dot(direction) / direction.magSq()), 1));

                bricks[i].decreaseDurability();
                if (bricks[i].getDurability() == 0) {
                    bricks[i].remove();
                    score += 10;
                }
                break;
            }
        }
        // Check collision with brick
        if (!collisionDetected) {
            collisionDetected = false;
        }
        parent.fill(0, 150, 255);
        parent.ellipse(ballPosition.x, ballPosition.y, ballRadius * 2, ballRadius * 2);
    }

    @Override
    public String keyPressed() {
            return null;
    }

    public void restartGame() {
        gameStart = true;
        score = 0;
        ballPosition = new PVector(parent.width / 2, parent.height - 50);
        ballVelocity = new PVector(0, -5);
        for (int i = 0; i < numBricks; i++) {
            bricks[i].reset();
        }
        gameOver = false;
    }

    public void drawText(String text, float x, float y) {
        parent.textSize(32);
        parent.textAlign(parent.CENTER, parent.CENTER);
        parent.fill(255);
        parent.text(text, x, y);
    }

    public void displayGame() {
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
    public String howToPlay() {
        return null;
    }

    @Override
    public String youDied() {
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