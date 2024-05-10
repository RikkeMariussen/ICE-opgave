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

    public Bricks(PApplet brick, int brickX, int brickY, int width, int height, int color, int durability) {
        this.brick = brick;
        this.brickX = brickX;
        this.brickY = brickY;
        this.width = width;
        this.height = height;
        this.color = color;
        this.durability = durability;
    }

    public void display() {
        if (durability > 0) {
            brick.fill(color);
            brick.rect(brickX, brickY, width, height);
        }
    }

    public boolean checkCollision(PVector ballPosition, float ballRadius) {
        if (durability > 0) {
            float distX = ballPosition.x - brickX - width / 2;
            float distY = ballPosition.y - brickY - height / 2;

            if (Math.abs(distX) <= width / 2 + ballRadius && Math.abs(distY) <= height / 2 + ballRadius) {
                return true;
            }
        }
        return false;
    }

    public void decreaseDurability() {
        durability--;
    }

    public int getDurability() {
        return durability;
    }

    public void remove() {
        durability = 0;
    }
}
