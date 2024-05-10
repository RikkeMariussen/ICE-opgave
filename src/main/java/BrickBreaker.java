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

    public void settings() {
        size(800, 800);
    }

    public void setup() {
        ballPosition = new PVector(width / 2, height - 50);
        ballVelocity = new PVector(0, -5);

        bricks = new Bricks[numBricks];
        for (int i = 0; i < numBricks; i++) {
            bricks[i] = new Bricks(this, ((i % 10) * 80), (i / 10) * 30 + 50, 80, 15, color(255), 1);
        }
    }

    PlayerPlate playerPlate = new PlayerPlate(this, width / 2, height + 600, 80, 20);

    public void draw() {
        background(0);
        playerPlate.display();
        playerPlate.update();
        // Update ball position
        ballPosition.add(ballVelocity);

        // Check if the ball hits the wall
        if (ballPosition.x > width - ballRadius || ballPosition.x < ballRadius) {
            // Randomize x-component of velocity
            ballVelocity.x = random(-5, 5);
        }
        if (ballPosition.y < ballRadius) {
            // Randomize y-component of velocity
            ballVelocity.y = random(1, 5); // Ensure the ball goes upwards
        }
        if (ballPosition.y + ballRadius >= playerPlate.getY() && ballPosition.x >= playerPlate.getX()
                - playerPlate.getWidth() / 2 && ballPosition.x <= playerPlate.getX() + playerPlate.getWidth() / 2) {
            // Reverse direction if colliding with the player plate
            ballVelocity.y *= -1;
        }

        // Display and check collision for each brick
        for (int i = 0; i < numBricks; i++) {
            bricks[i].display();
            if (bricks[i].checkCollision(ballPosition, ballRadius)) {
                // Randomize direction upon collision with brick
                ballVelocity.x = random(-5, 5);
                ballVelocity.y = random(-5, 5);
                bricks[i].decreaseDurability(); // Decrease brick durability
                if (bricks[i].getDurability() == 0) {
                    bricks[i].remove(); // Remove brick if durability is 0
                }
            }
        }

        // Draw the ball
        fill(0, 150, 255);
        ellipse(ballPosition.x, ballPosition.y, ballRadius * 2, ballRadius * 2);
    }


    public static void main(String[] args) {
        PApplet.main("BrickBreaker");
    }
}
