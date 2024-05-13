import processing.core.PApplet;

public class PointObject {

    private PApplet parent;
    private int x;
    private int y;
    private int w;
    private int h;
    private float speed;
    private float minSpeed;
    private float maxSpeed;
    private int color;
    protected int score;

    public PointObject(PApplet parent, int x, int y, int w, int h, float minSpeed, float maxSpeed, int color) {
        this.parent = parent;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.color = color;
        this.speed = parent.random(minSpeed, maxSpeed);

    }


    //Methods for displaying the point objects
    public void displayRoundObject() {
        parent.fill(color);
        parent.ellipse(x, y, w, h);
    }


    //Methods for catching the point objects
    public void catchRoundObject(){
        speed = 0;
        score++;
    }


    //Setter
    public void setSpeed(float speed) {
        this.speed += speed;
    }


    //Getter
    public float getSpeed() {
        return speed;
    }
    public boolean isCaught(int snakeX, int snakeY) {
        float distance = PApplet.dist(snakeX, snakeY, x, y);
        return distance < w / 2;  // Assuming the snake's head is about the same width as the apple
    }

    public void resetPosition() {
        this.x = (int)(parent.random(parent.width));
        this.y = (int)(parent.random(parent.height));
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }
}