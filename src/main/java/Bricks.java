import processing.core.PApplet;
import processing.core.PVector;

public class Bricks {
    private PApplet brick;
    private int size;
    private int brickX;
    private int brickY;
    private int width;
    private int height;
    private int color;
    private int durability;
    private int initialDurability;


    public Bricks(PApplet brick, int brickX, int brickY, int width, int height, int color, int durability) {
        this.brick = brick;
        this.brickX = brickX;
        this.brickY = brickY;
        this.width = width;
        this.height = height;
        this.color = color;
        this.durability = durability;
        initialDurability = durability;
    }

    public void display() {
        if (durability > 0) {
            brick.fill(color);
            brick.rect(brickX, brickY, width, height);
        }
    }

    public boolean checkCollision(PVector ballPosition, float ballRadius) {
        if (durability > 0) {
            // Calculate the closest point on the brick's perimeter to the ball's center
            float closestX = clamp(ballPosition.x, brickX - width / 2, brickX + width / 2);
            float closestY = clamp(ballPosition.y, brickY - height / 2, brickY + height / 2);

            // Calculate the distance between the closest point and the ball's center
            float distX = ballPosition.x - closestX;
            float distY = ballPosition.y - closestY;

            // Check if the distance is within the ball's radius
            return distX * distX + distY * distY <= ballRadius * ballRadius;
        }
        return false;
    }

    // Helper function to clamp a value within a range
    private float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(value, max));
    }


    public void decreaseDurability() {
        durability +=-1;
    }

    public int getDurability() {
        return durability;
    }

    public void remove() {
        durability = 0;
    }
    public int getBrickX(){
        return brickX;
    }
    public int getBrickY(){
        return brickY;
    }
    public int getColor() {
        return color;
    }
    public void reset() {
        durability = initialDurability;
    }
}
