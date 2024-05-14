import processing.core.PApplet;
import processing.core.PVector;

public class Balls extends PApplet {

    protected PVector position;
    protected PVector velocity;
    protected float radius = 10;
    protected Bricks bricks;
    // int numBricks = 4;

    public void settings() {
        size(800, 600); // Set the size of the window
    }

    public void setup() {
        position = new PVector(width / 2, height - 100); // Adjusted the y-coordinate
        velocity = new PVector(0, -5);

        bricks = new Bricks(this, width / 2, 50, 80, 15, color(20, 50, 100), 1); // Create bricks with 3 durability
    }

    public void draw() {
        background(255); // Clear the background

        // Chekker positionen alt efter velocity
        position.add(velocity);

        // Checkker om bolden rammer kanten af skærmen
        if (position.x > width - radius || position.x < radius) {
            velocity.x *= -1; // Ændre retning hvis den rammer siderne af skærmen
        }
        if (position.y < radius) {
            velocity.y *= -1; // Ændre retning hvis den rammer toppen
        }

        // Draw and check collision with bricks
        bricks.display(); // Display bricks
        if (bricks.checkCollision(position, radius)) { // Check collision with ball
            velocity.y *= -1; // Reverse direction if colliding with a brick
            bricks.decreaseDurability(); // Decrease brick durability
            if (bricks.getDurability() == 0) {
                bricks.remove(); // Remove brick if durability is 0
            }
        }

        fill(0, 150, 255);
        ellipse(position.x, position.y, radius * 2, radius * 2);
    }
}
