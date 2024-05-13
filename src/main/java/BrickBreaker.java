/* @Override
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
 }*/

import processing.core.PApplet;
import processing.core.PVector;

public class BrickBreaker extends PApplet {

    PVector ballPosition;
    PVector ballVelocity;
    float ballRadius = 10;
    Bricks[] bricks;
    int numBricks = 50;
    int score = 0;
    boolean gameOver = false;

    public void settings() {
        size(800, 800);
    }

    /*public void setup() {
        ballPosition = new PVector(width / 2, height - 50);
        ballVelocity = new PVector(0, -5);

        bricks = new Bricks[numBricks];
        for (int i = 0; i < numBricks; i++) {
            bricks[i] = new Bricks(this, ((i % 10) * 82) + 40, (i / 10) * 30 + 50, 80, 15, color(255), 1);
        }
    }*/
    public void setup() {
        ballPosition = new PVector(width / 2, height - 50);
        ballVelocity = new PVector(0, -5);

        bricks = new Bricks[numBricks];
        for (int i = 0; i < numBricks; i++) {
            int x = ((i % 10) * 82) + 40;
            int y = (i / 10) * 30 + 50;
            int brickColor = color(255);
            int durability = 1;

            // Check if it's the 3rd row
            if (i / 10 == 2) {
                // Check if it's the 3rd or 8th brick
                if (i % 10 == 2 || i % 10 == 7) {
                    brickColor = color(255, 165, 0); // Orange color
                    durability = 3; // Double durability
                }
            }

            bricks[i] = new Bricks(this, x, y, 80, 15, brickColor, durability);
        }
    }

    PlayerPlate playerPlate = new PlayerPlate(this, width / 2, height + 670, 80, 20);

    public void draw() {
        background(0);
        if (!gameOver) {
            drawGame();
        } else {
            drawGameOverScreen();
        }
    }

    public void drawGame() {
        drawText("Score: " + score, width / 2, height - 775);
        playerPlate.display();
        playerPlate.update();

        // Update ball position
        ballPosition.add(ballVelocity);

        // Check if the ball hits the wall
        if (ballPosition.x > width - ballRadius || ballPosition.x < ballRadius) {
            // Reverse x-component of velocity
            ballVelocity.x *= -1;
        }
        if (ballPosition.y < ballRadius) {
            // Reverse y-component of velocity
            ballVelocity.y *= -1;
        }
        if (ballPosition.y > height + ballRadius) {
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
                direction.rotate(random(degrees(-15), degrees(15)));

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
        fill(0, 150, 255);
        ellipse(ballPosition.x, ballPosition.y, ballRadius * 2, ballRadius * 2);
    }


    void drawGameOverScreen() {
        fill(255, 0, 0);
        textSize(50);
        textAlign(CENTER, CENTER);
        text("Game Over", width / 2, height / 2);
        textSize(32);
        text("Press 'R' to restart", width / 2, height / 2 + 50);
        textSize(60);
        text("Your score: " + score, width / 2, height / 2 + -100);
    }

    public void keyPressed() {
        if (key == 'r' || key == 'R') {
            restartGame();
        }
    }

    void restartGame() {
        score = 0;
        ballPosition = new PVector(width / 2, height - 50);
        ballVelocity = new PVector(0, -5);
        for (int i = 0; i < numBricks; i++) {
            bricks[i].reset();
        }
        gameOver = false;
    }

    void drawText(String text, float x, float y) {
        textSize(32);
        textAlign(CENTER, CENTER);
        fill(255);
        text(text, x, y);
    }


    public static void main(String[] args) {
        PApplet.main("BrickBreaker");
    }
}