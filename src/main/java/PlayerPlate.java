import processing.core.PApplet;

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
        parent.fill(color);
        parent.rect(x, y, w, h); //Rect for avoiding
    }

}
