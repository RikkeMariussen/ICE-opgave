import processing.core.PApplet;
import processing.core.PConstants;

public class PlayerPlate{
    private PApplet parent;
    private int size;
    private int x;
    private int y;
    private int w;
    private int h;
    private int color;

    public PlayerPlate(PApplet parent, int x, int y, int w, int h) {
        this.parent = parent;
        this.size = size;
        this.x = x;
        this.y = y;
        this.w = 80;
        this.h = 20;
        this.color = parent.color(175);
        //, int size, int x, int y, int w, int h, int color
    }
    public void update() {
        x = parent.mouseX;
    }

    public void display() {
        parent.strokeWeight(1);
        parent.fill(color);
        parent.rectMode(PConstants.CENTER);
        parent.rect(x, y, w, h); //Rect for avoiding
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

}