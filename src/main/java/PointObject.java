import processing.core.PApplet;

public class PointObject extends PApplet {

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
        this.speed = random(minSpeed, maxSpeed);

    }


    //Methods for displaying the point objects

    public void displayRoundObject() {
        parent.strokeWeight(0.5f);
        parent.fill(color);
        parent.ellipse(x, y, w, h);
    }

    public void displaySquareObject(){
        parent.fill(color);
        parent.stroke(0);
        parent.strokeWeight(0);
        parent.rect(x, y, w, h);
    }

    public void updateRoundObject() {
        y += speed;
    }

        //Setter

    public void setSpeed(float speed) {
        this.speed += speed;
    }

    //Getter

    public float getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}
